package com.roll.servlet;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 

import com.roll.model.User;
import com.roll.dao.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void  doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try{
        UserDAO userDAO = new UserDAO();

        User user = userDAO.getUserByUsername(username);

        if(user == null || !BCrypt.checkpw(password, user.getPassword())){
            response.getWriter().println("login failed!");
            request.setAttribute("error", "Invalid username or password!");

            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        response.sendRedirect(request.getContextPath() + "/home");


        }catch(SQLException e){
            e.printStackTrace();
            request.setAttribute("error", "A database error occurred. Please try again!");

            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

}
