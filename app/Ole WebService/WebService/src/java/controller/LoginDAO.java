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
        String hashedPw = SHA1(password);

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
    
     private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

}
