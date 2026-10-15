package com.roll.dao;

import com.roll.database.DatabaseConnection;
import com.roll.model.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;


public class UserDAO{

    public int createUser(String name, String username, String email, String password) throws SQLException{

        String sql = """
                INSERT INTO users (name, username, email, password) VALUES (?,?,?,?)
                """;
        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
            ){
        
            statement.setString(1, name);
            statement.setString(2, username);
            statement.setString(3, email);

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            statement.setString(4, hashedPassword);

            try{
                statement.executeUpdate();
                //if user is successfully created, it will return 0 (zero)
                return 0;
            }
            catch(SQLException e){
                if(e.getErrorCode() == 1062){

                    //if the user selected username already exists in the database, it will return 1 (one) 
                    if(e.getMessage().contains("unique_username")){
                        return 1;
                    }
                    //if the user selected email already exists in the databse, it will return 2 (two)
                    if(e.getMessage().contains("unique_email")){
                        return 2;
                    }
                }
                //any other SQLException
                throw e;
            }
        }
    }
    //return all the registered users in database
    public List<User> getUser() throws SQLException{

        List<User> users = new ArrayList<>();
        String sql = """
                SELECT * FROM users
                """;
        try(
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
        ){
            while(result.next()){
                User user = new User(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("password")
                );

                users.add(user);
            }
            return users;
        }
    }

    //data of a user with their ID 
    public User getUserById(int id) throws SQLException{

        String sql = """
            SELECT * FROM users WHERE id = ?
            """;
        
        try(
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){

            statement.setInt(1, id);

            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    return new User(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("password")
                    );
                }
            }
        }
        return null;
    }

    public int deleteUser(int id)throws SQLException{
        String sql = """
            DELETE FROM users WHERE id=?
            """;
            
        try(
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){

            statement.setInt(1, id);

            int rowEffected = statement.executeUpdate();
            //if user is deleted, returns 4
            if(rowEffected != 0){
                return 4;
            }
            //if not, it returns 5
            else{
                return 5;
            }
        }
    }

    public User getUserByUsername(String username) throws SQLException{
        String sql ="""
            SELECT * FROM users WHERE username = ? 
            """;
        try(
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, username);

            try(
                ResultSet result = statement.executeQuery();
            ){
                if(result.next()){
                    return new User(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("password")
                    );
                }

                return null;
            }
        }
    }

    public int updateProfile(int id, String name, String username, String email) throws SQLException{
        
        String sql = """
                UPDATE users
                SET name = ?, username = ?, email = ?
                WHERE id = ?
                """;
        try(       
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, name);
            statement.setString(2, username);
            statement.setString(3, email);
            statement.setInt(4, id);

            try{
                int rowEffected = statement.executeUpdate();

                if(rowEffected == 0){
                    //Failed to update 
                    return 3;
                }
                return 0;
            }catch(SQLException e){
                if(e.getErrorCode() == 1062){
                    if(e.getMessage().contains("unique_username")){
                        return 1;
                    }
                    if(e.getMessage().contains("unique_email")){
                        return 2; 
                    }
                }
                throw e; 
            }
        }
    }

    public boolean updatePassword(int id, String newPassword) throws SQLException{
        String sql = """
            UPDATE users 
            SET password = ?
            WHERE id = ?
            """;
        
        try(
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, newPassword);
            statement.setInt(2, id);

            try{
                int rowEffected = statement.executeUpdate();

                if(rowEffected == 0){
                    return false;
                }
                else{
                    return true;
                }
            }catch(SQLException e){
                throw e;
            }
        }
    }
}
