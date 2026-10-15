package com.roll.validation;

public class Validation{

    public static boolean isValidName(String name){
        if(name == null || name.isBlank()){
            return false;
        }
        if(name.length()<2 || name.length()>100){
            return false;
        }

        return name.matches("[a-zA-Z ]+( [a-zA-Z]+)*");
    }

    public static boolean isValidUsername(String username){
        if(username == null || username.isBlank()){
            return false;
        }
        if(username.length() < 3 || username.length() > 20){
            return false;
        }

        return username.matches("[a-zA-Z][a-zA-Z0-9_.-]*[a-zA-Z0-9_]");
    }

    public static  boolean isValidEmail(String email){
        if(email == null || email.isBlank()){
            return false;
        }

        return email.matches("[a-zA-Z0-9,_%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    public static boolean isValidPassword(String password){
        if(password == null || password.length() < 8 || password.length() > 30){
            return false;
        }

        boolean hasUppercae = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");

        return hasUppercae && hasLowercase && hasNumber && hasSpecial;
    }
}
