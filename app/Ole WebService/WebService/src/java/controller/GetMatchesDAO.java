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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AllPublicLeague;
import model.Match;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class GetMatchesDAO {

    public static HashMap<Integer, Match> getRecentMatches() {
        HashMap<Integer, Match> recentMatches = new HashMap<Integer, Match>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select m.matchId,m.tournamentId, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2, m.team1_score,m.team2_score from `match` m where date > DATE(NOW()) and TIMESTAMPDIFF(day, date(NOW()), date) <= 7");) {
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("no data 7 days");
                PreparedStatement stmtAgain = conn.prepareStatement("select m.matchId,m.tournamentId, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2, m.team1_score,m.team2_score from `match` m where date > DATE(NOW()) and TIMESTAMPDIFF(day, date(NOW()), date) <= 14");
                rs = stmtAgain.executeQuery();
            }

            while (rs.next()) {
                int matchID = rs.getInt(1);
                int tournamentID = rs.getInt(2);
                Date matchDate = rs.getDate(3);
                Time matchTime = rs.getTime(4);
                String team1 = rs.getString(5);
                String team2 = rs.getString(6);
                int team1Score = rs.getInt(7);
                int team2Score = rs.getInt(8);

                recentMatches.put(matchID, new Match(matchID, tournamentID, matchDate, matchTime, team1, team2, team1Score, team2Score));

            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return recentMatches;
    }

    public static Match getOneMatch(int id) {
        Match m = new Match();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select m.matchId, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2 from `match` m where m.matchId = ?");) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int matchID = rs.getInt(1);
                Date matchDate = rs.getDate(2);
                Time matchTime = rs.getTime(3);
                String team1 = rs.getString(4);
                String team2 = rs.getString(5);
                m = new Match(matchID, matchDate, matchTime, team1, team2);

            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return m;
    }

    public static HashMap<Integer, Match> getPastMatches(String username) {
        HashMap<Integer, Match> pastMatcehs = new HashMap<Integer, Match>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select m.matchId,m.tournamentId, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2, m.team1_score,m.team2_score, coalesce(matches.team1_prediction,-1), coalesce(matches.team2_prediction,-1), (SELECT l.pointsAllocated FROM league l where leagueId = m.tournamentId ) AS points from `match` m left join (select ml.team1_prediction, ml.team2_prediction, ml.matchId, l.username from matcheslog ml inner join log l on l.logId = ml.logId and username = '"+username+"') AS matches on m.matchId = matches.matchId where date < DATE(NOW()) and TIMESTAMPDIFF(day, date, date(NOW())) <= 7 AND (m.team1_score != -1 OR m.team2_score != -1) order by m.date desc ");) {
            System.out.println("Here");
            System.out.println(stmt);
            //try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select m.matchId,m.tournamentId, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2, m.team1_score,m.team2_score from `match` m where date < DATE(NOW()) and TIMESTAMPDIFF(day, date, date(NOW())) <= 7 order by m.date desc");) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int matchID = rs.getInt(1);
                int tournamentID = rs.getInt(2);
                Date matchDate = rs.getDate(3);
                Time matchTime = rs.getTime(4);
                String team1 = rs.getString(5);
                String team2 = rs.getString(6);
                int team1Score = rs.getInt(7);
                int team2Score = rs.getInt(8);
                int team1Prediction = rs.getInt(9);
                int team2Prediction = rs.getInt(10);
                int points = rs.getInt(11);
                pastMatcehs.put(matchID, new Match(matchID, tournamentID, matchDate, matchTime, team1, team2, team1Score, team2Score, team1Prediction, team2Prediction, points));
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return pastMatcehs;
    }

    public static HashMap<Integer, JSONObject> getExistingMatchPrediction(int logId) {
        ArrayList<Integer> matchIdPredictions = new ArrayList<>();
        HashMap<Integer, JSONObject> existingMatches = new HashMap<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select matchId, team1_prediction, team2_prediction from matcheslog where logId = ?");) {
            stmt.setInt(1, logId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                try {
                    JSONObject json = new JSONObject();
                    int matchId = rs.getInt(1);
                    json.put("matchId", matchId);
                    json.put("team1_prediction", rs.getInt(2));
                    json.put("team2_prediction", rs.getInt(3));
                    existingMatches.put(matchId, json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return existingMatches;
    }

    public static HashMap<Integer, JSONObject> getExistingMatchPrediction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
