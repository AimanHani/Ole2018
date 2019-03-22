/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class Log {
    int logID;
    String username;
    int leagueID;
    int points;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Log(int logID, String username, int leagueID, int points, String status) {
        this.logID = logID;
        this.username = username;
        this.leagueID = leagueID;
        this.points = points;
        this.status = status;
    }

    public Log() {
    }

    public Log(int logID, String username, int leagueID, int points) {
        this.logID = logID;
        this.username = username;
        this.leagueID = leagueID;
        this.points = points;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
}
