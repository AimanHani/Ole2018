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
        byte[]salt = getSalt(username);
        
        if(salt == null){
          return false;  
        }
        
        String hashedPw = new String(getSHA1SecurePassword(password,salt));

        ResultSet rs = null;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("Select username,password from user where username=? and password=?");) {

            ps.setString(1, username);
            ps.setString(2,hashedPw);

            rs = ps.executeQuery();
            while (rs.next()) {
                String queryUsername = rs.getString(1);
                String queryPassword = rs.getString(2);
                if (queryUsername.equals(username) && queryPassword.equals(hashedPw)) {
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
    
    private static String getSHA1SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
   private static byte[] getSalt(String username) throws SQLException{
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
    

}
