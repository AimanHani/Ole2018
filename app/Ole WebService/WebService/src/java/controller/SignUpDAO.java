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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.Match;

/**
 *
 * @author user
 */
public class SignUpDAO {
    
    public static String signUp(String username, String name, String password, String email, String birthdate, String contactNo, String country, String team) throws SQLException{
        
        boolean emailCheck = checkEmail(email);
        if(emailCheck){
        boolean status = false;
        int rs = 0;
        if(isDateValid(birthdate)){
            try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO user (username, name, password,dob, country, contactNo, email, favoriteTeam) VALUES(?,?,?,?,?,?,?,?)");) {

            
                ps.setString(1, username);
                ps.setString(2, name);
                ps.setString(3,SHA1(password));
                ps.setString(4, birthdate);
                ps.setString(5, country);
                ps.setString(6, contactNo); 
                ps.setString(7, email);
                ps.setString(8, team);
                
                rs = ps.executeUpdate();
                if(rs>0){
                    return "success";
                }
            }catch (Exception e) {
                System.out.println("check db connection class");
            } 
            finally {
                if (rs >0) {
                rs=0;
                }
            }
        }
        else{
            return "birthdate error";
        }
        
        return "username has been taken";
    }
        else{
            return "email has been taken";
        }
        
    }
    
    
     final static String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
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
    public static boolean checkEmail(String email){
         try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select * from user where email = ?");) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
         return true;
    }
}
