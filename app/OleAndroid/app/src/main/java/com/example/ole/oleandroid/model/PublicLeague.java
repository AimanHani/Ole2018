package com.example.ole.oleandroid.model;

public class PublicLeague {
    private int leagueId;
    private int tournamentId;
    private String prize;
    private int pointsAllocated;
    private String leagueName;
    private String tournamentName;
    private int logId;

    public PublicLeague(int leagueId, int tournamentId, String prize, int pointsAllocated, String leagueName, String tournamentName, int logId) {
        this.leagueId = leagueId;
        this.tournamentId = tournamentId;
        this.prize = prize;
        this.pointsAllocated = pointsAllocated;
        this.leagueName = leagueName;
        this.tournamentName = tournamentName;
        this.logId = logId;
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
}
