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

import java.sql.SQLException;
import java.sql.Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import model.AllPublicLeague;
import model.PrivateLeague;
import model.Match;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author user
 */
public class PrivateLeagueDAO {

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

                            try (Connection c4 = DBConnection.getConnection(); PreparedStatement ps4 = c4.prepareStatement("insert into log(username, leagueId, points) values ('admin', " + leagueID + ", -1)");) {
                                rs = ps4.executeUpdate();
                                if (rs > 0) {
                                    return "successful";
                                }
                            } catch (Exception e) {

                            }
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

    public static AllPublicLeague retrieveLeague(String leagueName) {
        PrivateLeague pl = null;
        AllPublicLeague apl = null;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select leagueId,tournamentId,pointsAllocated from league where leagueName = ? ");) {
            stmt.setString(1, leagueName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int leagueKeyId = rs.getInt(1);
                int tournamentId = rs.getInt(2);
                int pointsAllocated = rs.getInt(3);

                apl = new AllPublicLeague(leagueKeyId, tournamentId, pointsAllocated, leagueName);

            }
            rs.close();
            return apl;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static HashMap<Integer, model.PrivateLeague> retrievePrivateLeagueByName(int leagueId) {

        HashMap<Integer, model.PrivateLeague> privateLeaguesList = new HashMap<Integer, model.PrivateLeague>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select prize,startDate,endDate,leagueKeyId,username,password from privateleague where leagueKeyId = ?");) {
            stmt.setInt(1, leagueId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String prize = rs.getString(1);
                Date startDate = rs.getDate(2);
                Date endDate = rs.getDate(3);
                int leagueKeyId = rs.getInt(4);
                String username = rs.getString(5);
                String password = rs.getString(6);

                privateLeaguesList.put(leagueKeyId, new model.PrivateLeague(prize, startDate, endDate, leagueKeyId, username,password));

            }
            rs.close();
            return privateLeaguesList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
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
