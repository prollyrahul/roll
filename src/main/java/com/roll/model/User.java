package com.roll.model;

public class User{
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;

    public User(int id, String name, String username, String email, String password){

        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

}
