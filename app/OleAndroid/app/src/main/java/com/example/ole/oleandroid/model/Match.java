package com.example.ole.oleandroid.model;

public class Match {
    private int matchID;
    private int tournamentID;
    private String matchDate;
    private String matchTime;
    private String team1;
    private String team2;
    private int team1Score;
    private int team2Score;
    private int team1Prediction;
    private int team2Prediction;
    private int points;

    public Match(int matchID, String matchDate, String matchTime, String team1, String team2) {
        this.matchID = matchID;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Match(int matchID, int tournamentID, String matchDate, String matchTime, String team1, String team2, int team1Score, int team2Score) {
        this.matchID = matchID;
        this.tournamentID = tournamentID;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.team1 = team1;
        this.team2 = team2;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
    }

    public Match(int matchID, int tournamentID, String matchDate, String matchTime, String team1, String team2, int team1Score, int team2Score, int team1Prediction, int team2Prediction, int points) {
        this.matchID = matchID;
        this.tournamentID = tournamentID;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.team1 = team1;
        this.team2 = team2;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.team1Prediction = team1Prediction;
        this.team2Prediction = team2Prediction;
        this.points = points;
    }


    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
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

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }

    public int getTeam1Prediction() { return team1Prediction; }

    public void setTeam1Prediction(int team1Prediction) { this.team1Prediction = team1Prediction; }

    public int getTeam2Prediction() { return team2Prediction; }

    public void setTeam2Prediction(int team2Prediction) { this.team2Prediction = team2Prediction; }

    public int getPoints() { return points; }

    public void setPoints(int points) { this.points = points; }
}
