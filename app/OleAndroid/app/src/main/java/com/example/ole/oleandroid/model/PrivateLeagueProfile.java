package com.example.ole.oleandroid.model;

public class PrivateLeagueProfile implements Comparable {

    private int logID;
    private String username;
    private int leagueID;
    private String leagueName;
    private int totalPoints;

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
