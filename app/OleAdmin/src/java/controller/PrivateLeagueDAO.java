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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PrivateLeague;
import model.PublicLeague;

/**
 *
 * @author user
 */
public class PrivateLeagueDAO {

    public static ArrayList<PrivateLeague> getAllPrivateLeague() {
        //this method gets all the specials from the database
        //and creates a special object
        //special object will be stored in arraylist
        //arraylist of specials will be returned to the specialsservlet
        String statement = "SELECT l.leagueId, l.tournamentId, t.name, l.pointsAllocated, l.leagueName, p.prize,p.username,p.startDate,p.endDate,p.password FROM league l inner join tournament t on l.tournamentId = t.tournamentId inner join privateleague p on l.leagueId = p.leagueKeyId ";
        ArrayList<PrivateLeague> privateLeaugeList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PrivateLeague pl = new PrivateLeague(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getDate(9), rs.getString(10));
                privateLeaugeList.add(pl);

            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return privateLeaugeList;
    }

    public static Boolean updatePoints(int leagueId, int points) {
        String statement = "update league set pointsAllocated = " + points + " where leagueId = " + leagueId;
        System.out.println(statement);
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Boolean updatePrize(int leagueId, String prize) {
        String statement = "update privateleague set prize = '" + prize + "' where leagueKeyId = " + leagueId;
        System.out.println(statement);
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ArrayList<Integer> getListOfLogIds(int leagueId) {
        String statement = "select logId from log where leagueId = " + leagueId;
        ArrayList<Integer> logIdList = new ArrayList();
        System.out.println(statement);
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                logIdList.add(rs.getInt(1));
            }
            conn.close();
            return logIdList;

        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logIdList;
    }

    public static Boolean deleteSpecialLogs(ArrayList<Integer> logIdList) {
        for (int i = 0; i < logIdList.size(); i++) {
            String statement = "delete from specialslog where logid = " + logIdList.get(i);
            try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
                stmt.executeUpdate();
                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public static Boolean deleteMatchesLogs(ArrayList<Integer> logIdList) {
        for (int i = 0; i < logIdList.size(); i++) {
            String statement = "delete from matcheslog where logId = " + logIdList.get(i);
            try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
                stmt.executeUpdate();
                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public static Boolean deleteLogs(int leagueId) {
        String statement = "delete from log where leagueId = " + leagueId;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static Boolean deletePrivateLeagueTeams(int leagueId) {
        String statement = "delete from privateleagueteams where leagueId = " + leagueId;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static Boolean deletePrivateLeague(int leagueId) {
        String statement = "delete from privateleague where leagueKeyId = " + leagueId;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Boolean deleteLeague(int leagueId) {
        String statement = "delete from league where leagueId = " + leagueId;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement);) {
            stmt.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
