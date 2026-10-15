package com.roll;

import com.roll.model.User;
import com.roll.dao.UserDAO;
import com.roll.validation.Validation;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.List;

public class Main{
    static Scanner scanner =  new Scanner(System.in);

    public static void main(String[] args)throws Exception{
        
        System.out.print("1. Create a user profileS.\n2. Fetch all users.\n3. Fetch user by ID.\n4. Delete an user.\nChose from given options: ");
        int req = scanner.nextInt();
        scanner.nextLine();
        if(req == 1){
            createUser();
        }
        else if(req == 2){
            getUser();
        }
        else if(req == 3){
            getUserById();
        }
        else if(req == 4){
            deleteUser();
        }
        else {
            System.out.println("invalid input");
        }

        scanner.close();
    }

    public static void createUser() throws SQLException{

        System.out.print("Name: ");
        String name = scanner.nextLine();
        while(!Validation.isValidName(name)){
            System.out.println("Invalid Name!\nName should not contain Any Number or Specail Character");
            System.out.print("Try again: ");
            name = scanner.nextLine();
        }

        System.out.print("Username: ");
        String username = scanner.nextLine();
        while(!Validation.isValidUsername(username)){
            System.out.println("Invalid Username!\nI. An username cannot began with number or special character.\nII. An username cannot end with period(.) or hyphen(-).\nIII. Accepted special characters are period(.), hyphen(-) and underscore(_).");
            System.out.print("Try again: ");
            username = scanner.nextLine();
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();
        while(!Validation.isValidEmail(email)){
            System.out.println("Invalid Email!");
            System.out.print("Try again: ");
            email = scanner.nextLine();
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();
        while(!Validation.isValidPassword(password)){
            System.out.println("Weak Password!\nA must contain following:\nI. One uppercase letter.\nII. One lowercase letter.\nIII. One number.\nIV. One special character.\nV.It must be between 8 to 25 characters.");
            System.out.print("Try again: ");
            password = scanner.nextLine();
        }

        UserDAO userDAO = new UserDAO();

        while(true){
            int result = userDAO.createUser(name, username, email, password);

            if(result == 0) {
                System.out.println("Successgully Signed up!");
                break;
            }
            else if(result== 1){
                System.out.println("An user with the same username already exists!");
                System.out.println("Do you want to try aagain?\npress 1 to try choose another username or press 2 to cancel sign-up\nEnter your choice here: ");
                int  choice = scanner.nextInt();
                scanner.nextLine();

                if(choice == 2){
                    System.out.println("Sign-up cancelled!");
                    break;
                }
                else if(choice == 1){
                    System.out.println("Enter Username: ");
                    username = scanner.nextLine();

                    while(!Validation.isValidUsername(username)){
                        System.out.println("Invalid Username!\nI. An username cannot began with number or special character.\nII. An username cannot end with period(.) or hyphen(-).\nIII. Accepted special characters are period(.), hyphen(-) and underscore(_).");
                        System.out.print("Try again: ");
                        username = scanner.nextLine();
                    }
                }

                while(choice !=1 && choice !=2){
                    System.out.println("wrong input!");
                    System.out.print("Select from 1 or 2: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                }

            }
            else if(result == 2){
                System.out.println("Email already aqquipied!");
                System.out.print("Do you want o try another email address?\nPress 1 to try another email address or press 2 to cancel sign-up\nEnter your choice here: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if(choice == 2){
                    System.out.println("Sign-up cancelled");
                    break;
                }
                else if(choice == 1){
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();

                    while(!Validation.isValidEmail(email)){
                        System.out.println("Invalid Email!");
                        System.out.print("Try again: ");
                        email = scanner.nextLine();
                    }
                }
                while(choice !=1 && choice !=2){
                    System.out.println("wrong input!");
                    System.out.print("Select from 1 or 2: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                }
            }
        }
    }

    public static void getUser()throws SQLException{

        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getUser();
        for(User user : users){
            System.out.println(user.getName() + " : " + user.getUsername());
        }
    }

    public static void getUserById() throws SQLException{

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();

        UserDAO userDAO = new UserDAO();

        User user = userDAO.getUserById(id);
        System.out.println(user.getName() + " : " + user.getUsername());
    }

    public static void deleteUser() throws SQLException{
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();

        UserDAO userDAO =  new UserDAO();
        userDAO.deleteUser(id);
    }

}