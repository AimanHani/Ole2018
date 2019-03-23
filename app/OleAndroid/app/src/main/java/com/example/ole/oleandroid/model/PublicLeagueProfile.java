package com.example.ole.oleandroid.model;

import java.sql.Date;
import java.sql.Time;

public class PublicLeagueProfile implements Comparable {
    private int logID;
    private String username;
    private int leagueID;
    private String leagueName;
    private int team1Prediction;
    private int team2Prediction;
    private Date matchDate;
    private Time matchTime;
    private String team1;
    private String team2;
    private int totalPoints;
    private int rank;
    private String country;

    public PublicLeagueProfile(String username, int leagueID, int totalPoints, int rank, String country) {
        this.username = username;
        this.leagueID = leagueID;
        this.totalPoints = totalPoints;
        this.rank = rank;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PublicLeagueProfile(int logID, String username, int leagueID, String leagueName, int team1Prediction, int team2Prediction, Date matchDate, Time matchTime, String team1, String team2) {
        this.logID = logID;
        this.username = username;
        this.leagueID = leagueID;
        this.leagueName = leagueName;
        this.team1Prediction = team1Prediction;
        this.team2Prediction = team2Prediction;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.team1 = team1;
        this.team2 = team2;
    }


    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public PublicLeagueProfile() {
        // default constructor
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

    public int getTeam1Prediction() {
        return team1Prediction;
    }

    public void setTeam1Prediction(int team1Prediction) {
        this.team1Prediction = team1Prediction;
    }

    public int getTeam2Prediction() {
        return team2Prediction;
    }

    public void setTeam2Prediction(int team2Prediction) {
        this.team2Prediction = team2Prediction;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public Time getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Time matchTime) {
        this.matchTime = matchTime;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Object o) {
        int compareTotalScore=((PublicLeagueProfile)o).getTotalPoints();
        /* For Ascending order*/
        return compareTotalScore-this.totalPoints;
    }

}
