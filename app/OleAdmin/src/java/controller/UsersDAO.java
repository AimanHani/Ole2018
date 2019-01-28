package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;

public class UsersDAO {

    public static ArrayList<Users> getAllUsers() {
        //this method gets all the users from the database
        //and creates a user object
        //users object will be stored in arraylist
        //arraylist of users will be returned to the usersservlet
        String statement = "select * from user where username !=?";
        ArrayList<Users> usersList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.setString(1, "admin");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Users user = new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                usersList.add(user);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersList;
    }

    public static ArrayList<Users> searchUsername(String username) {
        //this method gets all the users from the database
        //and creates a user object
        //users object will be stored in arraylist
        //arraylist of users will be returned to the usersservlet
        String statement = "select * from user where username like '%" + username + "%'";
        ArrayList<Users> usersList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Users user = new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                usersList.add(user);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersList;
    }
    
    public static boolean deleteUser(String username) {
        //this method will delete user from database
        String statement = "delete from user where username ='" + username + "'";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
