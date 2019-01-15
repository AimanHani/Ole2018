package com.example.ole.oleandroid.model;

public class PublicLeague {
    private int leagueId;
    private int tournamentId;
    private String prize;
    private int pointsAllocated;
    private String leagueName;
    private String tournamentName;

    public PublicLeague(int leagueId, int tournamentId, String prize, int pointsAllocated, String leagueName) {
        this.leagueId = leagueId;
        this.tournamentId = tournamentId;
        this.prize = prize;
        this.pointsAllocated = pointsAllocated;
        this.leagueName = leagueName;
    }

    public PublicLeague(int leagueId, String prize, int tournamentId, int pointsAllocated, String leagueName, String tournamentName) {
        this.leagueId = leagueId;
        this.prize = prize;
        this.tournamentId = tournamentId;
        this.pointsAllocated = pointsAllocated;
        this.leagueName = leagueName;
        this.tournamentName = tournamentName;
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

    public int getPointsAllocated() {
        return pointsAllocated;
    }

    public String getLeagueName() {
        return leagueName;
    }
}
