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
import model.AllPublicLeague;
import model.Match;

/**
 *
 * @author user
 */
public class GetMatchesDAO {

    public static HashMap<Integer, Match> getRecentMatches() {
        HashMap<Integer, Match> recentMatches = new HashMap<Integer, Match>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select m.matchId,m.tournamentId, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2, m.team1_score,m.team2_score from `match` m where date > DATE(NOW()) LIMIT 3");) {
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

                recentMatches.put(matchID, new Match(matchID, tournamentID, matchDate, matchTime, team1, team2, team1Score, team2Score));

            }
            rs.close();

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
                m = new Match(matchID,matchDate,matchTime,team1,team2);

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return m;
    }
}
