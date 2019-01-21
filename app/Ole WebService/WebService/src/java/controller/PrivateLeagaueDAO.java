/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author user
 */
public class PrivateLeagaueDAO {

    public static String createPrivateLeague(String leagueName, String prize, String password, String startDate, String endDate, String username, int leagueId) {
        int rs = 0;
        // I follow the php script to set the point as 0 here
        int points = 0;
        if (isDateValid(startDate) && isDateValid(endDate)) {
            try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO privateleague (name, prize, password,startDate,endDate,username,leagueKeyId) VALUES (?,?,?,?,?,?,?)");) {

                ps.setString(1, leagueName);
                ps.setString(2, prize);
                ps.setString(3, password);
                ps.setString(4, startDate);
                ps.setString(5, endDate);
                ps.setString(6, username);
                ps.setInt(7, leagueId);
                

                rs = ps.executeUpdate();
                if (rs > 0) {
                    return "successful";
                }

            } catch (Exception e) {
                System.out.println("check db connection class");
            }
        } else {
            return " start date and end date is not in valid format";
        }
        return "error";
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
}