/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author user
 */
public class Match {
    private int matchID;
    private int tournamentID;
    private Date matchDate;

    public Match(int matchID, Date matchDate, Time matchTime, String team1, String team2) {
        this.matchID = matchID;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.team1 = team1;
        this.team2 = team2;
    }
    private Time matchTime;
    private String team1;
    private String team2;
    private int team1Score;
    private int team2Score;
    private int team1Prediction;
    private int team2Prediction;
    private int points;

    public Match() {
    }

    public Match(int matchID, int tournamentID, Date matchDate, Time matchTime, String team1, String team2, int team1Score, int team2Score) {
        this.matchID = matchID;
        this.tournamentID = tournamentID;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.team1 = team1;
        this.team2 = team2;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
    }
    
    public Match(int matchID, int tournamentID, Date matchDate, Time matchTime, String team1, String team2, int team1Score, int team2Score, int team1Prediction, int team2Prediction, int points) {
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    
}
