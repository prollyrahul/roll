package com.roll.servlet;

import com.roll.dao.UserDAO;
import com.roll.model.User;
import com.roll.validation.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet{
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        User sessionUser = (User) session.getAttribute("user");
        UserDAO userDAO = new UserDAO();

        try{
            User user = userDAO.getUserById(sessionUser.getId());

            if(user == null){
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        }catch(SQLException e){
            getServletContext().log("Failed to user profile", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load profile.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Aunthenticate Session
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        User sessionUser = (User)session.getAttribute("user");
        UserDAO userDAO = new UserDAO();

        try{
            User existingUser = userDAO.getUserById(sessionUser.getId());

            if(existingUser == null){
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            request.setAttribute("user", existingUser);

            String action = request.getParameter("action");

            if("updateProfile".equals(action)){
                handleProfileUpdapte(request, response, session, userDAO, existingUser);
            }
            else if("changePassword".equals(action)){
                handlePasswordChange(request, response, session, userDAO, existingUser);
            }
            else if("deleteAccount".equals(action)){
                handleAccountDelete(request, response, session, userDAO, existingUser);
            }else{
                showError(request, response, "Invalid Request");
            }
        }catch(SQLException e){
            getServletContext().log("Profile Operation Failed", e);

            request.setAttribute("user", sessionUser);
            showError(request, response, "A Database error occurred. Please try again");
        }
    }

    private void handleProfileUpdapte(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserDAO userDAO, User existingUser)
            throws SQLException, ServletException, IOException{
        
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String currentPassword = request.getParameter("currentPassword");

        //Verify the current password

        if(!isCurrentPasswordValid(currentPassword, existingUser.getPassword())){
            showError(request, response, "Incorrect Password");
            return;
        }
        if(!Validation.isValidName(name)){
            showError(request, response, "Invalid Name!");
            return;
        }
        if(!Validation.isValidUsername(username)){
            showError(request, response, "Invalid Username!");
            return;
        }
        if(!Validation.isValidEmail(email)){
            showError(request, response, "Invalid Email!");
            return;
        }
        
        int result = userDAO.updateProfile(existingUser.getId(), name, username, email);

        if(result == 1){
            showError(request, response, "Username Already Exists.");
            return;
        }
        if(result == 2){
            showError(request, response, "Email Already Exists.");
            return;
        }
        if(result != 0){
            showError(request, response, "Unable to update profile");
            return;
        }

        refreshSessionUser(userDAO, session, existingUser.getId());

        response.sendRedirect(request.getContextPath() + "/home");
    }

    private void handlePasswordChange(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserDAO userDAO, User existingUser)
        throws SQLException, ServletException, IOException{
        
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if(!isCurrentPasswordValid(currentPassword, existingUser.getPassword())){
            showError(request, response, "Current Password is Incorrect!");
            return;
        }
        if(!newPassword.equals(confirmPassword)){
            showError(request, response, "New password do not match!");
            return;
        }

        String passwordHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        boolean updated = userDAO.updatePassword(existingUser.getId(), passwordHash);

        if(!updated){
            showError(request, response, "failed to change password!");
            return;
        }

        refreshSessionUser(userDAO, session, existingUser.getId());

        response.sendRedirect(request.getContextPath() + "/home");
    }

    private void handleAccountDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserDAO userDAO, User existingUser)
        throws SQLException, ServletException, IOException{
        
        String currentPassword = request.getParameter("deletePassword");
        String confirmation = request.getParameter("confirmDelete");

        if(!"Yes".equals(confirmation)){
            showError(request, response, "Please cofirm that you want to delete your account.");
            return;
        }

        if(!isCurrentPasswordValid(currentPassword, existingUser.getPassword())){
            showError(request, response, "Current Password is incorrect!");
            return;
        }

        userDAO.deleteUser(existingUser.getId());

        session.invalidate();

        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    private boolean isCurrentPasswordValid(
        String currentPassword, String storedPasswordhash
    ){
        return currentPassword != null 
                && !currentPassword.isBlank() 
                && BCrypt.checkpw(currentPassword, storedPasswordhash);
    }

    private void refreshSessionUser(UserDAO userDAO, HttpSession session, int userId) 
        throws SQLException{
        
        User updatedUser = userDAO.getUserById(userId);

        if(updatedUser == null){
            session.invalidate();
            return;
        }

        session.setAttribute("user", updatedUser);
    }

    private void showError(HttpServletRequest request, HttpServletResponse response, String message) 
        throws ServletException, IOException{
        request.setAttribute("error", message);

        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
}
