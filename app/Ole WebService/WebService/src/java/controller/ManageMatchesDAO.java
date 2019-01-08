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
import java.util.HashMap;
import model.Match;

/**
 *
 * @author user
 */
public class ManageMatchesDAO {
    public static HashMap<Integer, Match> getRecentMatches() {
        HashMap<Integer, Match> recentMatches= GetMatchesDAO.getRecentMatches();
        return recentMatches;
    }
    
    public static Match getOneMatch(int id) {
        Match m = GetMatchesDAO.getOneMatch(id);
        return m;
    }
    
    public static String insertMLog(int logID, int matchID,int team1Prediction, int team2Prediction,boolean doubleIt){
        int rs = 0;
        // I follow the php script to set the point as 0 here
        int doubleValue = 0;
        if(doubleIt){
            doubleValue = 1;
        }
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("insert into matcheslog (logId, matchId, doubleIt, team1_prediction, team2_prediction) VALUES (?,?,?,?,?)");) {

            ps.setInt(1, logID);
            ps.setInt(2, matchID);
            ps.setInt(3, team1Prediction);
            ps.setInt(4, team2Prediction);
            ps.setInt(5, doubleValue);
            
            rs = ps.executeUpdate();
            if (rs > 0) {
                return "successful";
            }
        } catch (Exception e) {
            System.out.println("check db connection class");
        }
        return "error";
    }
     public static String updateMatchesLog(int logID, int matchID,int team1Prediction, int team2Prediction,boolean doubleIt){
        int rs = 0;
        // I follow the php script to set the point as 0 here
        int doubleValue = 0;
        if(doubleIt){
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
        } catch (Exception e) {
            System.out.println("check db connection class");
        }
        return "error";
    }
     
}
