package com.roll.servlet;

import com.roll.dao.UserDAO;
import com.roll.validation.Validation;
import com.roll.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if(!Validation.isValidName(name)){
            request.setAttribute("error", "Invalid Name");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }
        if(!Validation.isValidUsername(username)){
            request.setAttribute("error", "Invalid Username");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }
        if(!Validation.isValidEmail(email)){
            request.setAttribute("error", "Invalid Email");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }
        if(!Validation.isValidPassword(password)){
            request.setAttribute("error", "Invalid Password");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();

        try{
            int result = userDAO.createUser(
                name,
                username,
                email,
                password
            );

            if(result == 0){
                User user = userDAO.getUserByUsername(username);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            else if(result == 1){
                request.setAttribute("error", "Username Already Exist!");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            }
            else if(result == 2){
                request.setAttribute("error", "Email Already exist!");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            request.setAttribute("error", "Database error!");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }
}
