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
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author user
 */
public class PrivateLeagaueDAO {

    public static String createPrivateLeague(String leagueName, String prize, String password, String startDate, String endDate, String username, String pointsAllocated, String tournamentId) {
        int rs = 0;
        // I follow the php script to set the point as 0 here

        if (isDateValid(startDate) && isDateValid(endDate)) {
            try (Connection c1 = DBConnection.getConnection(); PreparedStatement ps1 = c1.prepareStatement("INSERT INTO league (tournamentId, pointsAllocated, leagueName) VALUES (?, ?, ?)");) {
                System.out.println(ps1);
                ps1.setInt(1, Integer.parseInt(tournamentId));
                ps1.setInt(2, Integer.parseInt(pointsAllocated));
                ps1.setString(3, leagueName);

                rs = ps1.executeUpdate();

                try (Connection c2 = DBConnection.getConnection(); PreparedStatement ps2 = c2.prepareStatement("SELECT * FROM `league` where leagueName='" + leagueName + "'");) {
                    ResultSet resultSet = ps2.executeQuery();
                    if (resultSet.next()) {
                        int leagueID = resultSet.getInt(1);
                        try (Connection c3 = DBConnection.getConnection(); PreparedStatement ps3 = c3.prepareStatement("INSERT INTO privateleague (prize, password,startDate,endDate,username,leagueKeyId) VALUES (?,?,?,?,?,?)");) {
                            ps3.setString(1, prize);
                            ps3.setString(2, password);
                            ps3.setString(3, startDate);
                            ps3.setString(4, endDate);
                            ps3.setString(5, username);
                            ps3.setInt(6, leagueID);
                            rs = ps3.executeUpdate();
                        }
                    }
                    if (rs > 0) {
                        return "successful";
                    }
                } catch (Exception e) {
                    System.out.println("check db connection class");
                }

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
            //df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
