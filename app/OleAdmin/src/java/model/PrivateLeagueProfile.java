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
public class PrivateLeagueProfile implements Comparable {

    private int logID;
    private String username;
    private int leagueID;
    private String leagueName;
    private int totalPoints;
    int rank;
    String country;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PrivateLeagueProfile(String username, int leagueID, String leagueName, int totalPoints, int rank, String country) {
        this.username = username;
        this.leagueID = leagueID;
        this.leagueName = leagueName;
        this.totalPoints = totalPoints;
        this.rank = rank;
        this.country = country;
    }

    public PrivateLeagueProfile(String username, int leagueID, int totalPoints) {
        this.username = username;
        this.leagueID = leagueID;
        this.totalPoints = totalPoints;
    }

    public PrivateLeagueProfile() {
    }

    public PrivateLeagueProfile(String username, int leagueID, String leagueName, int totalPoints) {
        this.username = username;
        this.leagueID = leagueID;
        this.leagueName = leagueName;
        this.totalPoints = totalPoints;
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

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public int compareTo(Object o) {
        int compareTotalScore=((PrivateLeagueProfile)o).getTotalPoints();
        /* For Ascending order*/
        return compareTotalScore-this.totalPoints;
    }

}
