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
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Match;

/**
 *
 * @author user
 */
public class SignUpDAO {

    public static String signUp(String username, String name, String password, String email, String birthdate, String contactNo, String country, String team) throws SQLException, ParseException {
        SimpleDateFormat fromUser = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

        birthdate = myFormat.format(fromUser.parse(birthdate));
       
       
            System.out.println(birthdate);
            boolean status = false;
            int rs = 0;
            //if(isDateValid(birthdate)){
            try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO user (username, name, password, dob, country, contactNo, email, favoriteTeam,salt) VALUES(?,?,?,?,?,?,?,?,?)");) {
                byte[] salt = getSalt();

                ps.setString(1, username);
                ps.setString(2, name);
                ps.setString(3, getSHA1SecurePassword(password, salt));
                ps.setString(4, birthdate);
                ps.setString(5, country);
                ps.setString(6, contactNo);
                ps.setString(7, email);
                ps.setString(8, team);
                ps.setBytes(9, salt);
                rs = ps.executeUpdate();
                if (rs > 0) {
                    return "success";
                }
                c.close();
            } catch (Exception e) {
                System.out.println("check db connection class" + e.toString());
                return "check db connection class or no such algorithm error check getSalt()" + e.toString();
            } finally {
                if (rs > 0) {
                    rs = 0;
                }
            }
        
        return "";

    }

    public static String checkEmailUsername(String email, String username) {
        boolean usernameCheck = checkUsername(username);
        boolean emailCheck = checkEmail(email);

        String msg = "success";
        if (!emailCheck) {
            msg = "email has been taken";
        }
        if (!usernameCheck) {
            msg = "username has been taken";
        }
        if (!usernameCheck && !emailCheck) {
            msg = "username and email have been taken";
        }
        return msg;

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

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static boolean checkEmail(String email) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select * from user where email = ?");) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static boolean checkUsername(String username) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select * from user where username = ?");) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static int getAge(String birthday) {
        System.out.println("getage");
        String[] parts = birthday.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        System.out.println("mth: " + month);
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

//        Integer ageInt = new Integer(age);
//        String ageS = ageInt.toString();
        System.out.println(age + "");
        return age;
    }
}
