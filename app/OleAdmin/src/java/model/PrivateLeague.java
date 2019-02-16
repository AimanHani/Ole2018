/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author user
 */
public class PrivateLeague {
     private int leagueID;
    private String tournamentId;
    private String tournamentName;
    private int pointsAllocated;
    private String leagueName;
    private String prize;
    private String username;
    private Date startDate;
    private Date endDate;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PrivateLeague(int leagueID, String tournamentId, String tournamentName, int pointsAllocated, String leagueName, String prize, String username, Date startDate, Date endDate, String password) {
        this.leagueID = leagueID;
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.pointsAllocated = pointsAllocated;
        this.leagueName = leagueName;
        this.prize = prize;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public PrivateLeague(int leagueID, String tournamentId, String tournamentName, int pointsAllocated, String leagueName, String prize, String username, Date startDate, Date endDate) {
        this.leagueID = leagueID;
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.pointsAllocated = pointsAllocated;
        this.leagueName = leagueName;
        this.prize = prize;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PrivateLeague(int leagueID, String tournamentId, String tournamentName, String leagueName, String prize) {
        this.leagueID = leagueID;
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.leagueName = leagueName;
        this.prize = prize;
    }

    public PrivateLeague() {
    }

    public PrivateLeague(int leagueID, String tournamentId, String tournamentName, int pointsAllocated, String leagueName, String prize) {
        this.leagueID = leagueID;
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.pointsAllocated = pointsAllocated;
        this.leagueName = leagueName;
        this.prize = prize;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public int getPointsAllocated() {
        return pointsAllocated;
    }

    public void setPointsAllocated(int pointsAllocated) {
        this.pointsAllocated = pointsAllocated;
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
}
