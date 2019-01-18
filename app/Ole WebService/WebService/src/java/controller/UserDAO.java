package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO {
    private static ArrayList<User> allUsers = new ArrayList<User>();

    public static User getUserInfo(String username) {
        User user = new User();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("Select * from user where username = ?");) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String username1 = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                Date dob = rs.getDate(4);
                String country = rs.getString(5);
                String contactNum = rs.getString(6);
                String email = rs.getString(7);
                String favoriteTeam = rs.getString(8);

                user = new User(username1, name, password, dob, country, contactNum, email, favoriteTeam);
            }
            return user;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static ArrayList<User> getAllUsers() {
        //this method gets all the users from the database
        //and creates a user object
        //users object will be stored in arraylist
        //arraylist of users will be returned to the usersservlet
        String statement = "select * from user";
        ArrayList<User> usersList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String username1 = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                Date dob = rs.getDate(4);
                String country = rs.getString(5);
                String contactNum = rs.getString(6);
                String email = rs.getString(7);
                String favoriteTeam = rs.getString(8);
                User user = new User(username1, name, password, dob, country, contactNum, email, favoriteTeam);
                usersList.add(user);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        allUsers = usersList;
        return usersList;
    }
    
    

}
