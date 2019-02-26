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
import java.util.ArrayList;
import java.util.HashMap;
import model.User;
import model.AllPublicLeague;
import model.PublicLeague;

/**
 *
 * @author user
 */
public class PublicLeagueDAO {

    public static ArrayList<PublicLeague> getPublicLeagues() {
        ArrayList<PublicLeague> publicLeagueList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select l.leagueId, pl.prize, l.leagueName, t.tournamentId, l.pointsAllocated, t.name as \"tournamentName\", log.logId\n"
                        + "from publicleague pl, league l, tournament t, log\n"
                        + "where pl.leagueId = l.leagueId\n"
                        + "and t.tournamentId = l.tournamentId\n"
                        + "and log.leagueId = l.leagueId\n"
                        + "and log.username = 'admin'");) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int leagueID = rs.getInt(1);
                String prize = rs.getString(2);
                String leagueName = rs.getString(3);
                int tournamentID = rs.getInt(4);
                int pointsAllocated = rs.getInt(5);
                String tournamentName = rs.getString(6);
                int logid = rs.getInt(7);

                publicLeagueList.add(new PublicLeague(leagueID, prize, tournamentID, pointsAllocated, tournamentName, leagueName, logid));
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return publicLeagueList;
    }

    public static HashMap<Integer, AllPublicLeague> retrieveAllPublicLeagues() {
        HashMap<Integer, AllPublicLeague> dbAllPublicLeague = new HashMap<Integer, AllPublicLeague>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT l.leagueId, prize, t.tournamentId, pointsAllocated, leagueName,t.name  FROM publicleague pl, league l, tournament t where l.leagueId = pl.leagueId");) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int leagueId = rs.getInt(1);
                String prize = rs.getString(2);
                int tournamentID = rs.getInt(3);
                int pointsAllocated = rs.getInt(4);
                String leagueName = rs.getString(5);
                String tournamentName = rs.getString(6);

                dbAllPublicLeague.put(leagueId, new AllPublicLeague(leagueId, prize, tournamentID, pointsAllocated, leagueName, tournamentName));
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return dbAllPublicLeague;
    }

    public static int getNumbersOfParticipantsInTheLeague(int leagueID) {
        int totalNumberOfParticipants = 0;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT count(username) AS num_participants, l.leagueId from log l inner join publicleague p where l.leagueId =" + leagueID + "");) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                totalNumberOfParticipants = rs.getInt(1);
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return totalNumberOfParticipants;
    }

    public static ArrayList<Integer> getUserPublicLeaguesID(String username) {
        ArrayList<PublicLeague> publicLeagueList = new ArrayList<>();
        ArrayList<Integer> leaguesEntered = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select l.leagueId\n"
                        + "from publicleague pl, league l, tournament t, log\n"
                        + "where pl.leagueId = l.leagueId\n"
                        + "and t.tournamentId = l.tournamentId\n"
                        + "and log.leagueId = l.leagueId\n"
                        + "and log.username = ?;");) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int leagueID = rs.getInt(1);

                leaguesEntered.add(leagueID);

                //publicLeagueList.add(new PublicLeague(leagueID, prize, tournamentID, pointsAllocated, tournamentName, leagueName, logid));
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return leaguesEntered;
    }

}
