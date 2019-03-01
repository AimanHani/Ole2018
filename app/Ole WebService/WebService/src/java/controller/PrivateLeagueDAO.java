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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import model.AllPublicLeague;
import model.PrivateLeague;
import model.Match;
import model.PrivateMembers;
import model.PublicLeague;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author user
 */
public class PrivateLeagueDAO {

    public static ArrayList<PrivateLeague> getPrivateLeagues(String username) {
        ArrayList<PrivateLeague> privateLeagueList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select l.leagueId, l.tournamentId, l.pointsAllocated, l.leagueName, pl.prize, pl.password, pl.startDate, pl.endDate, pl.username from league l inner join privateleague pl inner join log lg on l.leagueId = pl.leagueKeyId AND pl.leagueKeyId = lg.leagueId where lg.username='" + username + "'");) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int leagueID = rs.getInt(1);
                int tournamentID = rs.getInt(2);
                int pointsAllocated = rs.getInt(3);
                String leagueName = rs.getString(4);
                String prize = rs.getString(5);
                String password = rs.getString(6);
                Date startDate = rs.getDate(7);
                Date endDate = rs.getDate(8);
                String userName = rs.getString(9);

                privateLeagueList.add(new PrivateLeague(leagueID, leagueName, prize, password, startDate, endDate, leagueID, userName, pointsAllocated, tournamentID));
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return privateLeagueList;
    }

    public static ArrayList<PrivateLeague> getAllNonPrivateLeagues(String username) {
        ArrayList<PrivateLeague> privateLeagueList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select l.leagueId, l.tournamentId, l.pointsAllocated, l.leagueName, pv.prize, pv.password, pv.startDate, pv.endDate, pv.username from privateleague pv inner join league l on pv.leagueKeyId = l.leagueId where l.leagueId NOT IN ( SELECT lg.leagueId FROM log lg where lg.username = '" + username + "')");) {
            //"select distinct l.leagueId, l.tournamentId, l.pointsAllocated, l.leagueName, pl.prize, pl.password, pl.startDate, pl.endDate, pl.username from league l inner join privateleague pl inner join log lg on l.leagueId = pl.leagueKeyId AND pl.leagueKeyId = lg.leagueId where pl.username <> '" + username + "'");) {
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int leagueID = rs.getInt(1);
                int tournamentID = rs.getInt(2);
                int pointsAllocated = rs.getInt(3);
                String leagueName = rs.getString(4);
                String prize = rs.getString(5);
                String password = rs.getString(6);
                Date startDate = rs.getDate(7);
                Date endDate = rs.getDate(8);
                String userName = rs.getString(9);

                privateLeagueList.add(new PrivateLeague(leagueID, leagueName, prize, password, startDate, endDate, leagueID, userName, pointsAllocated, tournamentID));
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return privateLeagueList;
    }

    public static ArrayList<PrivateLeague> getAllPrivateLeagues() {
        ArrayList<PrivateLeague> privateLeagueList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select distinct l.leagueId, l.tournamentId, l.pointsAllocated, l.leagueName, pl.prize, pl.password, pl.startDate, pl.endDate, pl.username from league l inner join privateleague pl on l.leagueId = pl.leagueKeyId");) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int leagueID = rs.getInt(1);
                int tournamentID = rs.getInt(2);
                int pointsAllocated = rs.getInt(3);
                String leagueName = rs.getString(4);
                String prize = rs.getString(5);
                String password = rs.getString(6);
                Date startDate = rs.getDate(7);
                Date endDate = rs.getDate(8);
                String userName = rs.getString(9);

                privateLeagueList.add(new PrivateLeague(leagueID, leagueName, prize, password, startDate, endDate, leagueID, userName, pointsAllocated, tournamentID));
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return privateLeagueList;
    }

    public static String createPrivateLeague(String leagueName, String prize, String password, String startDate, String endDate, String username, String pointsAllocated, String tournamentId, String specials, String teams) {

        int rs = 0;
        // I follow the php script to set the point as 0 here

        System.out.println("hey" + leagueName + tournamentId + pointsAllocated);
        if (isDateValid(startDate) && isDateValid(endDate)) {
            try (Connection c = DBConnection.getConnection();) {

                PreparedStatement ps1 = c.prepareStatement("INSERT INTO league (tournamentId, pointsAllocated, leagueName) VALUES (?, ?, ?)");
                ps1.setString(1, tournamentId);
                ps1.setInt(2, Integer.parseInt(pointsAllocated));
                ps1.setString(3, leagueName);
                System.out.println(ps1);
                rs = ps1.executeUpdate();
                int leagueID = 0;

                if (rs > 0) {
                    PreparedStatement ps2 = c.prepareStatement("SELECT * FROM `league` where leagueName='" + leagueName + "'");
                    ResultSet resultSet = ps2.executeQuery();

                    resultSet.next();
                    leagueID = resultSet.getInt("leagueId");
                }
                Statement cs = c.createStatement();
                int stmtCount = 3;
                //PreparedStatement ps3 = c.prepareStatement();
                //insert privateLeague
                cs.addBatch("INSERT INTO privateleague (prize, password,startDate,endDate,username,leagueKeyId) VALUES (\'"
                        + prize + "\',\'"
                        + password + "\',\'"
                        + startDate + "\',\'"
                        + endDate + "\',\'"
                        + username + "\', "
                        + leagueID + ")");

                cs.addBatch("insert into log(username, leagueId, points) values ('admin', " + leagueID + ", -1)"); // insert admin log
                cs.addBatch("insert into log(username, leagueId, points) values ('" + username + "', " + leagueID + ", -1)"); // insert user log
                String[] teamsList = teams.split(",");
                for (String teamid : teamsList) {
                    cs.addBatch("insert into PrivateLeagueTeams(teamId, leagueId) values ('" + teamid + "', " + leagueID + ")"); // insert private league teams
                    stmtCount++;
                }

                int[] rs2 = cs.executeBatch();

                int logId = 0;
                if (rs2.length == stmtCount) {
                    PreparedStatement ps = c.prepareStatement("select logId from log where leagueId = '" + leagueID + "' AND username = 'admin'");
                    ResultSet resultSet = ps.executeQuery();
                    resultSet.next();
                    logId = resultSet.getInt(1);
                }
                Statement cs2 = c.createStatement();
                String[] specialsList = specials.split(",");
                for (String specialid : specialsList) {
                    cs2.addBatch("insert into specialslog(logid, specialsId, prediction) values(" + logId + "," + specialid + ", -1)");
                }
                
                rs2 = cs2.executeBatch();
                
                c.close();
                
                if (rs2.length == specialsList.length){
                    return "successful";
                }
               

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("check db connection class");
            }
        } else {
            return " start date and end date is not in valid format";
        }
        return "error";
    }

    public static String joinPrivateLeague(String username, String leagueId) {
        int rs = 0;
        System.out.println("Joining Private League: " + username + leagueId);
        try (Connection c5 = DBConnection.getConnection(); PreparedStatement ps5 = c5.prepareStatement("insert into log(username, leagueId, points) values ('" + username + "', " + leagueId + ", -1)");) {
            rs = ps5.executeUpdate();
            if (rs > 0) {
                return "successful";
            }
        } catch (Exception e) {
            return "error";
        }
        return "error";
    }

    public static AllPublicLeague retrieveLeague(int leagueId) {
        PrivateLeague pl = null;
        AllPublicLeague apl = null;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select leagueId,tournamentId,pointsAllocated,leagueName from league where leagueId = ? ");) {
            stmt.setInt(1, leagueId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int leagueKeyId = rs.getInt(1);
                int tournamentId = rs.getInt(2);
                int pointsAllocated = rs.getInt(3);
                String leagueName = rs.getString(4);

                apl = new AllPublicLeague(leagueKeyId, tournamentId, pointsAllocated, leagueName);

            }
            rs.close();
            conn.close();
            return apl;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static ArrayList<model.PrivateMembers> getMembers(int leagueid) {
        ArrayList<model.PrivateMembers> memlist = new ArrayList<model.PrivateMembers>();
        PrivateMembers members = null;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select *  from log where leagueId = ? and username != 'admin';");) {
            stmt.setInt(1, leagueid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int logid = rs.getInt(1);
                String username = rs.getString(2);
                int leagueId = rs.getInt(3);
                int points = rs.getInt(4);

                memlist.add(new PrivateMembers(logid, username, leagueId, points));

            }
            rs.close();
            conn.close();
            return memlist;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static HashMap<Integer, model.PrivateLeague> retrievePrivateLeagueByName(int leagueId) {

        HashMap<Integer, model.PrivateLeague> privateLeaguesList = new HashMap<Integer, model.PrivateLeague>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select prize,startDate,endDate,leagueKeyId,username,password from privateleague where leagueKeyId = ?");) {
            stmt.setInt(1, leagueId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String prize = rs.getString(1);
                Date startDate = rs.getDate(2);
                Date endDate = rs.getDate(3);
                int leagueKeyId = rs.getInt(4);
                String username = rs.getString(5);
                String password = rs.getString(6);

                privateLeaguesList.put(leagueKeyId, new model.PrivateLeague(prize, startDate, endDate, leagueKeyId, username, password));

            }
            rs.close();
            conn.close();
            return privateLeaguesList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static ArrayList<model.Tournament> getTournament() {
        ArrayList<model.Tournament> list = new ArrayList<model.Tournament>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select * from tournament");) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String tournamentId = rs.getString(1);
                String name = rs.getString(2);
                list.add(new model.Tournament(tournamentId, name));
            }
            rs.close();
            conn.close();
            return list;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    final static String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            //df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
