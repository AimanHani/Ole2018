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
import java.util.ArrayList;
import model.Match;
import model.PublicLeagueProfile;

/**
 *
 * @author user
 */
public class ScoreBoardDAO {

    public static PublicLeagueProfile getUserTotalScoreBasedOnLeague(int leagueID, String username) {
        PublicLeagueProfile plf = new PublicLeagueProfile();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select points from log where username = ? and leagueId = ?");) {
            stmt.setString(1, username);
            stmt.setInt(2, leagueID);
            ResultSet rs = stmt.executeQuery();
            int totalPoints = 0;

            while (rs.next()) {
                totalPoints += rs.getInt(1);

            }
            plf = new PublicLeagueProfile(username, leagueID, totalPoints);
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return plf;
    }

    public static ArrayList<PublicLeagueProfile> getUsersAndTheirTotalPoints(int leagueID) {
        ArrayList<PublicLeagueProfile> plfList = new ArrayList();
        ArrayList<String>usernames = getAllUsers(leagueID);
        for(int i=0; i<usernames.size(); i++){
            PublicLeagueProfile plf = new PublicLeagueProfile();
            try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select points from log where username = ? and leagueId = ?");) {
            stmt.setString(1, usernames.get(i));
            stmt.setInt(2, leagueID);
            ResultSet rs = stmt.executeQuery();
            int totalPoints = 0;

            while (rs.next()) {
                totalPoints += rs.getInt(1);

            }
            plf = new PublicLeagueProfile(usernames.get(i), leagueID, totalPoints);
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
            plfList.add(plf);
        }

        return plfList;
    }

    public static ArrayList<String> getAllUsers(int leagueID) {
        ArrayList<String> usernames = new ArrayList();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select distinct username from log where leagueId = ?");) {

            stmt.setInt(1, leagueID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString(1));

            }
            
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return usernames;
    }

}
