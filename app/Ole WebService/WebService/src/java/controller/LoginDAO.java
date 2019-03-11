/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.DBConnection;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static boolean validate(String username, String password) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {

        if (username == null || password == null) {
            return false;
        }
//        byte[] salt = getSalt(username);
//
//        if (salt == null) {
//            return false;
//        }

        ResultSet rs = null;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("Select username, password, salt from user where username=?");) {
            ps.setString(1, username);
            //ps.setString(2,hashedPw);

            rs = ps.executeQuery();
            while (rs.next()) {
                String queryUsername = rs.getString(1);
                String queryPassword = rs.getString(2);
                byte[] querySalt = rs.getBytes(3);

                if (querySalt == null) {
                    return false;
                }

                String hashedPw = new String(getSHA1SecurePassword(password, querySalt));

                if (queryPassword.equals(hashedPw)) {
                    return true;
                }
            }
            c.close();
        } catch (ClassNotFoundException e) {
            System.out.println("check db connection class");
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    private static String getSHA1SecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt(String username) throws SQLException {
        ResultSet rs = null;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("Select salt from user where username=?");) {

            ps.setString(1, username);
            byte[] salt = null;

            rs = ps.executeQuery();
            while (rs.next()) {
                salt = rs.getBytes(1);
            }
            return salt;
        } catch (ClassNotFoundException e) {
            System.out.println("check db connection class");
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public static boolean changePassword(String email, String password) throws SQLException {
        boolean results = false;
        String queryUsername = null;
        byte[] querySalt = null;

        ResultSet rs = null;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("Select username, salt from user where email=?");) {
            ps.setString(1, email);

            rs = ps.executeQuery();
            while (rs.next()) {
                queryUsername = rs.getString(1);
                querySalt = rs.getBytes(2);
            }
            
            if (querySalt == null) {
                return false;
            } else {
                String newHashedPassword = getSHA1SecurePassword(password, querySalt);

                PreparedStatement updateStmt = c.prepareStatement("Update user set password = ? where username = ?");
                updateStmt.setString(1, newHashedPassword);
                updateStmt.setString(2, queryUsername);
                int updateCount = updateStmt.executeUpdate();
                
                if (updateCount == 1){
                    return true;
                } 
            }
            
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("check db connection class");
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return results;
    }

}
