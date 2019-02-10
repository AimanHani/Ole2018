package com.example.ole.oleandroid.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class PrivateLeague {
    private static PrivateLeague privateLeague;

    public PrivateLeague(int leagueID, String leagueName, String prize, String password, java.util.Date startDate, java.util.Date endDate, String username, int pointsAllocated, int tournamentId, int leagueKeyId) {
    }

    public static PrivateLeague getPrivateLeague() {
        return privateLeague;
    }

    public static void setPrivateLeague(PrivateLeague privLeague){privateLeague = privLeague;}


    private int leagueId;
    private String leagueName;
    private String prize;
    private String password;
    private String startDate;
    private String endDate;
    private String username;
    private int pointsAllocated;
    private int tournamentId;
    private int leagueKeyId;

    public PrivateLeague(int leagueId, String leagueName, String prize, String password, String username, int pointsAllocated, int tournamentId, int leagueKeyId) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.prize = prize;
        this.password = password;

        this.username = username;
        this.pointsAllocated = pointsAllocated;
        this.tournamentId = tournamentId;
        this.leagueKeyId = leagueKeyId;
    }

    public PrivateLeague(int leagueId, String leagueName, String prize, String password, String startDate, String endDate, String username, int pointsAllocated, int tournamentId, int leagueKeyId) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.prize = prize;
        this.password = password;
        this.startDate = startDate;
        this.endDate = endDate;
        this.username = username;
        this.pointsAllocated = pointsAllocated;
        this.tournamentId = tournamentId;
        this.leagueKeyId = leagueKeyId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public String getLeagueName() {return leagueName; }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPointsAllocated() {
        return pointsAllocated;
    }

    public void setPointsAllocated(int pointsAllocated) {
        this.pointsAllocated = pointsAllocated;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getLeagueKeyId() {
        return leagueKeyId;
    }

    public void setLeagueKeyId(int leagueKeyId) {
        this.leagueKeyId = leagueKeyId;
    }

}