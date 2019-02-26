package controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Matches;
import org.json.JSONObject;

public class MatchesDAO {

    public static ArrayList<Matches> getAllMatches() {
        //this method gets all the users from the database
        //and creates a user object
        //users object will be stored in arraylist
        //arraylist of users will be returned to the usersservlet
        //String statement = "SELECT * FROM `match` order by `matchId`";
        
        String statement = "SELECT DISTINCT m.`matchId`,m.`tournamentId`,m.`date`,m.`time`,m.`team1`,(SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team01, m.`team2`,(SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team02, m.`team1_score`,m.`team2_score` FROM `match` m ORDER BY m.`date` ASC  ";
        
        ArrayList<Matches> matchesList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Matches match = new Matches(rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(8), rs.getInt(9), rs.getInt(10));
                matchesList.add(match);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MatchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matchesList;
    }

    public static ArrayList<Matches> getPastMatches() {
        //this method gets all the users from the database
        //and creates a user object
        //users object will be stored in arraylist
        //arraylist of users will be returned to the usersservlet
        
        
        //String statement = "SELECT * FROM `match` where `date` < CURRENT_DATE() ORDER BY `match`.`date` DESC ";
        String statement = "SELECT DISTINCT m.`matchId`,m.`tournamentId`,m.`date`,m.`time`,m.`team1`,(SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team01, m.`team2`,(SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team02, m.`team1_score`,m.`team2_score` FROM `match` m where m.`date` < CURRENT_DATE() ORDER BY m.`date` DESC ";
        
        
        ArrayList<Matches> matchesList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Matches match = new Matches(rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(8), rs.getInt(9), rs.getInt(10));
                matchesList.add(match);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MatchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matchesList;
    }
    
    public static ArrayList<Matches> getUpcomingMatches() {
        //this method gets all the users from the database
        //and creates a user object
        //users object will be stored in arraylist
        //arraylist of users will be returned to the usersservlet
        
        //String statement = "SELECT * FROM `match` where `date` > CURRENT_DATE() ORDER BY `match`.`date` ASC ";
        String statement = "SELECT DISTINCT m.`matchId`,m.`tournamentId`,m.`date`,m.`time`,m.`team1`,(SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team01, m.`team2`,(SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team02, m.`team1_score`,m.`team2_score` FROM `match` m where m.`date` > CURRENT_DATE() ORDER BY m.`date` ASC ";
        
        ArrayList<Matches> matchesList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Matches match = new Matches(rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(8), rs.getInt(9), rs.getInt(10));
                matchesList.add(match);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MatchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matchesList;
    }
    
}
