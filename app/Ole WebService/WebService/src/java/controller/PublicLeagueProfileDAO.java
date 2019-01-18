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
import model.PublicLeagueProfile;
import java.sql.Time;
import java.util.ArrayList;
import model.User;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author user
 */
public class PublicLeagueProfileDAO {

    public static ArrayList<PublicLeagueProfile> getUserPrediction(String username) {
        ArrayList<PublicLeagueProfile>leagueList = new ArrayList();
        PublicLeagueProfile plProfile = new PublicLeagueProfile();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT log.logId, log.username, log.leagueId, l.leagueName, ml.team1_prediction, ml.team2_prediction, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2 FROM log, matcheslog ml, league l, `match` as m WHERE log.logId = ml.logId and l.leagueId = log.leagueId and m.matchId = ml.matchId and log.username = ?");) {
             stmt.setString(1, username);
             ResultSet rs = stmt.executeQuery();
            

            while (rs.next()) {
                int logID = rs.getInt(1);
                String logUsername = rs.getString(2);
                int leagueID = rs.getInt(3);
                String leagueName = rs.getString(4);
                int team1Prediction = rs.getInt(5);
                int team2Prediction = rs.getInt(6);
                Date matchDate = rs.getDate(7);
                Time matchTime = rs.getTime(8);
                String team1 = rs.getString(9);
                String team2 = rs.getString(10);

                leagueList.add (new PublicLeagueProfile(logID, logUsername, leagueID, leagueName, team1Prediction, team2Prediction, matchDate, matchTime, team1, team2));

            }
            rs.close();
            return leagueList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static User getUserInfo(String username) {
        User u = new User();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("Select * from user where username = ?");) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username1 = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                Date dob = rs.getDate(4);
                String country = rs.getString(5);
                String contactNum = rs.getString(6);
                String email = rs.getString(7);
                String favoriteTeam = rs.getString(8);

                u = new User(username1, name, password, dob, country, contactNum, email, favoriteTeam);

            }
            rs.close();
            return u;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
