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
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Match;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class ManageMatchesDAO {

    public static HashMap<Integer, Match> getRecentMatches() {
        HashMap<Integer, Match> recentMatches = GetMatchesDAO.getRecentMatches();
        return recentMatches;
    }

    public static Match getOneMatch(int id) {
        Match m = GetMatchesDAO.getOneMatch(id);
        return m;
    }

    public static String insertMLog(int logID, int matchID, int team1Prediction, int team2Prediction, boolean doubleIt) {
        int rs = 0;
        String results = "error";
        // I follow the php script to set the point as 0 here
        int doubleValue = 0;
        if (doubleIt) {
            doubleValue = 1;
        }
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("insert into matcheslog (logId, matchId, doubleIt, team1_prediction, team2_prediction) VALUES (?,?,?,?,?)");) {

            ps.setInt(1, logID);
            ps.setString(2, matchID + "");
            ps.setInt(3, doubleValue);
            ps.setInt(4, team1Prediction);
            ps.setInt(5, team2Prediction);

            rs = ps.executeUpdate();
            if (rs > 0) {
                results = "successful";
            }
            c.close();
        } catch (Exception e) {
            System.out.println("insertMLog: check db connection class");
            e.printStackTrace();
            return "error";
        }
        return results;
    }

    public static String insertMultipleMatchLogs(JSONArray matchList, int logId) throws JSONException {
        int[] rs;
        // I follow the php script to set the point as 0 here
        int doubleValue = 0;
        String results = "error";
        System.out.println("matchList " + matchList.length());
        ArrayList<Integer> existingPredictions = getExistingMatchPrediction(logId);
        JSONObject match = matchList.getJSONObject(0);

        try (Connection c = DBConnection.getConnection(); Statement cs = c.createStatement();) {

            for (int i = 0; i < matchList.length(); i++) {
                match = matchList.getJSONObject(i);
                int matchId = match.getInt("matchId");
                String statement = "";
                if (existingPredictions.contains(matchId)) {
                    statement = setUpdateMatchStatement(logId, match.getInt("matchId"), match.getInt("team1Score"), match.getInt("team2Score"), doubleValue);
                } else {
                    statement = setInsertMatchStatement(logId, match.getInt("matchId"), match.getInt("team1Score"), match.getInt("team2Score"), doubleValue);
                }
                cs.addBatch(statement);
            }

            rs = cs.executeBatch();
            System.out.println("The number of rows inserted: " + rs.length);
            if (rs.length == matchList.length()) {
                results = "successful";
            }
            c.close();
        } catch (Exception e) {
            System.out.println("check db connection class" + e.getMessage());
        }

        return results;
    }

    public static ArrayList<Integer> getExistingMatchPrediction(int logId) {
        ArrayList<Integer> matchIdPredictions = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select matchId from matcheslog where logId = ?");) {
            stmt.setInt(1, logId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                matchIdPredictions.add(rs.getInt(1));
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return matchIdPredictions;
    }

    public static String updateMatchesLog(int logID, int matchID, int team1Prediction, int team2Prediction, boolean doubleIt) {
        int rs = 0;
        // I follow the php script to set the point as 0 here

//        + match.getInt("logId") + ","
//                + match.getString("matchId") + ","
//                + doubleValue + ","
//                + match.getString("team1Score") + ","
//                + match.getString("team2Score")
//                + ")";
        int doubleValue = 0;
        if (doubleIt) {
            doubleValue = 1;
        }
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("UPDATE matcheslog set doubleIt = ?, team1_prediction= ?, team2_prediction = ? where logId = ? and matchId = ?");) {

            ps.setInt(1, doubleValue);
            ps.setInt(2, team1Prediction);
            ps.setInt(3, team2Prediction);
            ps.setInt(4, logID);
            ps.setInt(5, matchID);

            rs = ps.executeUpdate();
            if (rs > 0) {
                return "successful";
            }
            c.close();
        } catch (Exception e) {
            System.out.println("check db connection class");
            e.printStackTrace();
        }
        return "error";
    }

    public static String setInsertMatchStatement(int logID, int matchID, int team1Prediction, int team2Prediction, int doubleIt) {
        return "insert into matcheslog (logId, matchId, doubleIt, team1_prediction, team2_prediction) VALUES ("
                + logID + ","
                + matchID + ","
                + doubleIt + ","
                + team1Prediction + ","
                + team2Prediction + ")";
    }

    public static String setUpdateMatchStatement(int logID, int matchID, int team1Prediction, int team2Prediction, int doubleIt) {
        return "UPDATE matcheslog set doubleIt = " + doubleIt
                + ", team1_prediction= " + team1Prediction
                + ", team2_prediction = " + team2Prediction
                + " where logId = " + logID
                + " and matchId = \'" + matchID +"\'";
    }

}
