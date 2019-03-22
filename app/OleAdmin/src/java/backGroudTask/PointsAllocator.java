/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backGroudTask;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Log;
import model.Match;
import model.MatchLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class PointsAllocator implements Runnable {

    public static HashMap<Integer, Match> pastMatches;

    @Override
    public void run() {
        System.out.println("starting");
        pastMatches = getPastMatches();

        Iterator it = pastMatches.keySet().iterator();
        HashMap<Integer, ArrayList<MatchLog>> matchesLogHashMap = new HashMap();
        System.out.println(" size " + matchesLogHashMap.size());
        ArrayList<MatchLog> matchesLogList = new ArrayList();

        for (int matchId : pastMatches.keySet()) {
            System.out.println(" hi " + matchId);
            //System.out.print(it.next().toString());
            //int matchId = (int) it.next();
            matchesLogList = getMatchLog(matchId);
            
            if (!matchesLogList.isEmpty()) {
                matchesLogHashMap.put(matchId, matchesLogList);
            }

        }
        boolean status = allocatePoints(matchesLogHashMap);
        System.out.println(status);

    }

    public static HashMap<Integer, Match> getPastMatches() {
        HashMap<Integer, Match> pastMatches = new HashMap<Integer, Match>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select m.matchId,m.tournamentId, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2, m.team1_score,m.team2_score from `match` m where date < DATE(NOW()) order by date desc limit 20");) {
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

                pastMatches.put(matchID, new Match(matchID, tournamentID, matchDate, matchTime, team1, team2, team1Score, team2Score));

            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return pastMatches;
    }

    public ArrayList<MatchLog> getMatchLog(int matchID) {
        ArrayList<MatchLog> matchesLog = new ArrayList();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select logId, team1_prediction, team2_prediction, matchId,status from matcheslog where matchId = ? and status = ?");) {
            stmt.setInt(1, matchID);
            stmt.setString(2, "no");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int logID = rs.getInt(1);
                int team1Prediction = rs.getInt(2);
                int team2Prediction = rs.getInt(3);
                int matchIDInLog = rs.getInt(4);
                String status = rs.getString(5);
                matchesLog.add(new MatchLog(logID, team1Prediction, team2Prediction, matchIDInLog, status));

            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return matchesLog;
    }

    public static boolean allocatePoints(HashMap<Integer, ArrayList<MatchLog>> matchesLogHashMap) {
        int count = 0;
        try (Connection connection = DBConnection.getConnection(); Statement stmt = connection.createStatement();) {

            for (int matchId : matchesLogHashMap.keySet()) {
                Match match = pastMatches.get(matchId);
                ArrayList<MatchLog> matchLogList = matchesLogHashMap.get(matchId);
                int[] rs = null;
                System.out.println(matchId + " size " + matchLogList.size());
                for (MatchLog ml : matchLogList) {
                    int point = 0;
                    int team1Prediction = ml.getTeam1Prediction();
                    int team2Prediction = ml.getTeam2Prediction();
                    int team1Score = match.getTeam1Score();
                    int team2Score = match.getTeam2Score();
                    int logId = ml.getLogID();
                    System.out.println(matchId + " : logid: " + logId);
                    //check if the get the winning team right
                    if (team1Score > team2Score && team1Prediction > team2Prediction) {
                        point += 1;
                    } else if (team1Score == team2Score && team1Prediction == team2Prediction) {
                        point += 1;
                    } else if (team1Score < team2Score && team1Prediction < team2Prediction) {
                        point += 1;
                    }

                    // check if they get the prediction score right
                    if (team1Score == team1Prediction) {
                        point += 1;
                    }
                    if (team2Score == team2Prediction) {
                        point += 1;
                    }

                    String stmt1 = "Update matcheslog set status =\'yes\' where logId =" + logId + " and matchId =" + matchId;
                    String stmt2 = "Update log set points = points+" + point + " where logId =" + logId;
                    stmt.addBatch(stmt1);
                    stmt.addBatch(stmt2);
                    count += 2;

                }
                rs = stmt.executeBatch();

                if (rs.length == count) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            Logger.getLogger(PointsAllocator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PointsAllocator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
