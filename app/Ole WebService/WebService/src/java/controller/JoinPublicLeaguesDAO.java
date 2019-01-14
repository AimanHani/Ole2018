/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.AllPublicLeague;

/**
 *
 * @author user
 */
public class JoinPublicLeaguesDAO {

    public static String insertToLog(String username, int leagueID) {
        int rs = 0;
        // I follow the php script to set the point as 0 here
        int points = 0;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO log (username, leagueId, points) VALUES (?,?,?)");) {

            ps.setString(1, username);
            ps.setInt(2, leagueID);
            ps.setInt(3, points);
            rs = ps.executeUpdate();
            if (rs > 0) {
                return "successful";
            }
        } catch (Exception e) {
            System.out.println("check db connection class");
        }
        return "error";
    }

    public static int selectLog(String username, int leagueID) {
        int logID = 0;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT logId from log where username = ? and leagueId = ? ");) {
            stmt.setString(1, username);
            stmt.setInt(2, leagueID);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                logID = rs.getInt(1);

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return logID;

    }

    public static String insertToMatchLog(int logID) {
        int points = 0;
        int rs = 0;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO matchlog (logId, points) VALUES (?,?)");) {
            ps.setInt(1, logID);
            ps.setInt(2, points);
            rs = ps.executeUpdate();
            if (rs > 0) {
                return "successful";
            }
        } catch (Exception e) {
            System.out.println("check db connection class");
        }
        return "error";

    }

    public static ArrayList<Integer> getSpecialsIDs() {
        int points = 0;

        ArrayList<Integer> specialsIDs = new ArrayList();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("SELECT specialsId from specialslog where prediction = -1");) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                specialsIDs.add(rs.getInt(1));
            }
            rs.close();

        } catch (Exception e) {
            System.out.println("check db connection class");
        }
        return specialsIDs;
    }

    public static String insertSpecialsLog(int logID) {
        int points = 0;
        int rs = 0;
        ArrayList<Integer> specialsIDs = getSpecialsIDs();
        for (int i = 0; i < specialsIDs.size(); i++) {
            try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO specialsLog (logid, specialsId) VALUES(?,?)");) {
                ps.setInt(1, logID);
                ps.setInt(2, specialsIDs.get(i));
                rs = ps.executeUpdate();

            } catch (Exception e) {
                System.out.println("check db connection class");
                return "error";
            }
        }

        return "successful";
    }
}
