package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PublicLeague;

public class PublicLeagueDAO {

    public static ArrayList<PublicLeague> getAllPublicLeague() {
        //this method gets all the specials from the database
        //and creates a special object
        //special object will be stored in arraylist
        //arraylist of specials will be returned to the specialsservlet
        String statement = "SELECT l.leagueId, l.tournamentId, t.name, l.pointsAllocated, l.leagueName, p.prize FROM league l inner join tournament t on l.tournamentId = t.tournamentId inner join publicleague p on l.leagueId = p.leagueId ";
        ArrayList<PublicLeague> publicLeagueList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PublicLeague pl = new PublicLeague(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                publicLeagueList.add(pl);

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publicLeagueList;
    }

    public static Boolean updatePoints(int leagueId, int points) {
        String statement = "update league set pointsAllocated = " + points + " where leagueId = " + leagueId;
        System.out.println(statement);
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Boolean updatePrize(int leagueId, String prize) {
        String statement = "update publicleague set prize = '" + prize + "' where leagueId = " + leagueId;
        System.out.println(statement);
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
