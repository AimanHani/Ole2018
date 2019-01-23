package com.example.ole.oleandroid.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class PrivateLeague implements Serializable {

    private int privateLeaugeId;
    private String leagueName;
    private String prize;
    private String password;
    private Date startDate;
    private Date endDate;
    private int leagueId;
    private String username;

    //private int pointsAllocated;
    //private int tournamentId;
    public PrivateLeague(int privateLeaugeId, String prize, Date startDate, Date endDate, int leagueId, String username) {
        this.privateLeaugeId = privateLeaugeId;
        this.prize = prize;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leagueId = leagueId;
        this.username = username;
    }

    public int getPrivateLeaugeId() {
        return privateLeaugeId;
    }

    public String getPrize() {
        return prize;
    }

    public String getUsername() {
        return username;
    }

    public PrivateLeague(int privateLeaugeId, String leagueName, String prize, Date startDate, Date endDate, int leagueId) {
        this.privateLeaugeId = privateLeaugeId;
        this.leagueName = leagueName;
        this.prize = prize;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leagueId = leagueId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public PrivateLeague() {
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
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
}
