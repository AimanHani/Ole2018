package com.example.ole.oleandroid.model;

import java.util.ArrayList;

public class PublicLeague {
    private int leagueId;
    private int tournamentId;
    private String prize;
    private int pointsAllocated;
    private String leagueName;
    private String tournamentName;
    private int logId;
    private Boolean userJoin;
    private ArrayList<Member> allMembers;

    public PublicLeague(int leagueId, int tournamentId, String prize, int pointsAllocated, String leagueName, String tournamentName, int logId) {
        this.leagueId = leagueId;
        this.tournamentId = tournamentId;
        this.prize = prize;
        this.pointsAllocated = pointsAllocated;
        this.leagueName = leagueName;
        this.tournamentName = tournamentName;
        this.logId = logId;
        this.allMembers = new ArrayList<>();
    }

    public PublicLeague(int leagueId, int tournamentId, String prize, int pointsAllocated, String leagueName, String tournamentName, int logId,  Boolean userJoin) {
        this.leagueId = leagueId;
        this.tournamentId = tournamentId;
        this.prize = prize;
        this.pointsAllocated = pointsAllocated;
        this.leagueName = leagueName;
        this.tournamentName = tournamentName;
        this.logId = logId;
        this.userJoin = userJoin;
        this.allMembers = new ArrayList<>();
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public void setPointsAllocated(int pointsAllocated) {
        this.pointsAllocated = pointsAllocated;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public String getPrize() {
        return prize;
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
    public String getLeagueName() {
        return leagueName;
    }

    public Boolean getUserJoin() {
        return userJoin;
    }

    public void setUserJoin(Boolean userJoin) {
        this.userJoin = userJoin;
    }

    public ArrayList<Member> getAllMembers() {
        return allMembers;
    }

    public void setAllMembers(ArrayList<Member> allMembers) {
        this.allMembers = allMembers;
    }
}
