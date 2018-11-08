package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Matches;

public class MatchesDAO {

    public static ArrayList<Matches> getAllMatches() {
        //this method gets all the users from the database
        //and creates a user object
        //users object will be stored in arraylist
        //arraylist of users will be returned to the usersservlet
        String statement = "SELECT * FROM `match` order by `matchId`";
        ArrayList<Matches> matchesList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Matches match = new Matches(rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                matchesList.add(match);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MatchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matchesList;
    }    
}
