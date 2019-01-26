package com.example.ole.oleandroid.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class PrivateLeague {
    private static PrivateLeague privateLeague;

    public static PrivateLeague getPrivateLeague() {
        return privateLeague;
    }

    public static void setPrivateLeague(PrivateLeague privLeague){privateLeague = privLeague;}


    private int leagueId;
    private String leagueName;
    private String prize;
    private String password;
    private Date startDate;
    private Date endDate;
    private String username;
    private int pointsAllocated;
    private int tournamentId;
    private int leagueKeyId;

    public PrivateLeague(int leagueId, String leagueName, String prize, String password, Date startDate, Date endDate, String username, int pointsAllocated, int tournamentId, int leagueKeyId) {
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

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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