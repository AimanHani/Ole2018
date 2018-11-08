/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LoginDAO contains the method that will validate the login details
 */
public class LoginDAO {

    /**
     * Use this to validate login
     *
     * @param username
     * @param password
     * @return boolean Return true if username and password is valid, false if
     * invalid or null.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static boolean validate(String username, String password) throws SQLException, ClassNotFoundException {

        if (username == null || password == null) {
            return false;
        }

        ResultSet rs = null;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("Select * from user where username=?");) {

            ps.setString(1, username);

            rs = ps.executeQuery();
            while (rs.next()) {
                String queryUsername = rs.getString(1);
                if (queryUsername.equals(username)) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("check db connection class");
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

}
