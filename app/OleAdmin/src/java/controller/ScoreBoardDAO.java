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
import model.Match;
import model.PrivateLeagueProfile;
import model.PublicLeagueProfile;

/**
 *
 * @author user
 */
public class ScoreBoardDAO {

    public static PublicLeagueProfile getUserTotalScoreBasedOnLeague(int leagueID, String username) {
        PublicLeagueProfile plf = new PublicLeagueProfile();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select points from log where username = ? and leagueId = ?");) {
            stmt.setString(1, username);
            stmt.setInt(2, leagueID);
            ResultSet rs = stmt.executeQuery();
            int totalPoints = 0;

            while (rs.next()) {
                totalPoints += rs.getInt(1);

            }
            plf = new PublicLeagueProfile(username, leagueID, totalPoints);
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return plf;
    }

    public static ArrayList<PublicLeagueProfile> getUsersAndTheirTotalPoints(int leagueID) {
        ArrayList<PublicLeagueProfile> plfList = new ArrayList();
        ArrayList<String> usernames = getAllUsers(leagueID);
        for (int i = 0; i < usernames.size(); i++) {
            PublicLeagueProfile plf = new PublicLeagueProfile();
            try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select points from log where username = ? and leagueId = ?");) {
                stmt.setString(1, usernames.get(i));
                stmt.setInt(2, leagueID);
                ResultSet rs = stmt.executeQuery();
                int totalPoints = 0;

                while (rs.next()) {
                    totalPoints += rs.getInt(1);

                }
                plf = new PublicLeagueProfile(usernames.get(i), leagueID, totalPoints);
                rs.close();
                conn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            plfList.add(plf);
        }

        return plfList;
    }

    public static ArrayList<String> getAllUsers(int leagueID) {
        ArrayList<String> usernames = new ArrayList();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select distinct username from log where leagueId = ? and username != ?");) {

            stmt.setInt(1, leagueID);
            stmt.setString(2, "admin");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString(1));

            }

            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return usernames;
    }

    public static ArrayList<Integer> getAllPrivateLeagueIDs() {
        ArrayList<Integer> privateLeagueIDsList = new ArrayList();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select distinct leagueKeyId from privateleague");) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                privateLeagueIDsList.add(rs.getInt(1));
            }

            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return privateLeagueIDsList;
    }

    public static ArrayList<PrivateLeagueProfile> getUsersAndTheirTotalPointsPrivate(int leagueID) {
        ArrayList<PrivateLeagueProfile> plfList = new ArrayList();
        ArrayList<String> usernames = getAllUsers(leagueID);
        
        for (int i = 0; i < usernames.size(); i++) {
            if (!usernames.get(i).equals("admin")) {
                PrivateLeagueProfile plf = new PrivateLeagueProfile();
                try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select points, leagueName from log, league where username = ? and log.leagueId = league.leagueId and log.leagueId = ?");) {
                    stmt.setString(1, usernames.get(i));
                    stmt.setInt(2, leagueID);
                    ResultSet rs = stmt.executeQuery();
                    int totalPoints = 0;
                    String leagueName = "";

                    while (rs.next()) {
                        totalPoints = rs.getInt(1);
                        leagueName = rs.getString(2);
                        plf = new PrivateLeagueProfile(usernames.get(i), leagueID, leagueName, totalPoints);
                        plfList.add(plf);
                    }
                    rs.close();
                    conn.close();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        return plfList;
    }

    public static ArrayList<Integer> privateLeagueIDsOfTheSpecificUserJoined(String username) {
        ArrayList<Integer> privateLeaguesIDsOfTheUserJoined = new ArrayList();
        ArrayList<Integer> privateLeagueIDsList = getAllPrivateLeagueIDs();

        //for (int i = 0; i < privateLeagueIDsList.size(); i++) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select distinct leagueId from log where username = ? and leagueId in (select distinct leagueKeyId from privateleague)");) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                privateLeaguesIDsOfTheUserJoined.add(rs.getInt(1));
            }

            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        //}
        return privateLeaguesIDsOfTheUserJoined;
    }

    public static String getUserCountry(String username) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select country from user where username = ?");) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }

            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
