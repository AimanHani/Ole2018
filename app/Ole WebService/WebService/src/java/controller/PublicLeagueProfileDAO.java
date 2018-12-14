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

/**
 *
 * @author user
 */
public class PublicLeagueProfileDAO {

    public static PublicLeagueProfile getUserPrediction(String username) {
        PublicLeagueProfile plProfile = new PublicLeagueProfile();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT log.logId, log.username, log.leagueId, l.leagueName, ml.team1_prediction, ml.team2_prediction, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2 FROM log, matcheslog ml, league l, `match` as m WHERE log.logId = ml.logId and l.leagueId = log.leagueId and m.matchId = ml.matchId and log.username = ? limit 1");) {
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

                plProfile = new PublicLeagueProfile(logID, logUsername, leagueID, leagueName, team1Prediction, team2Prediction, matchDate, matchTime, team1, team2);

            }
            rs.close();
            return plProfile;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
