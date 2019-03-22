/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class MatchLog {
    int logID;
    int team1Prediction;
    int team2Prediction;
    int matchID;
    String status;
    int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public MatchLog(int logID, int matchID, int points) {
        this.logID = logID;
        this.matchID = matchID;
        this.points = points;
    }

    public MatchLog(int logID, int team1Prediction, int team2Prediction, int matchID, String status) {
        this.logID = logID;
        this.team1Prediction = team1Prediction;
        this.team2Prediction = team2Prediction;
        this.matchID = matchID;
        this.status = status;
    }

    public MatchLog() {
    }

    public MatchLog(int logID, int team1Prediction, int team2Prediction, int matchID) {
        this.logID = logID;
        this.team1Prediction = team1Prediction;
        this.team2Prediction = team2Prediction;
        this.matchID = matchID;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public int getTeam1Prediction() {
        return team1Prediction;
    }

    public void setTeam1Prediction(int team1Prediction) {
        this.team1Prediction = team1Prediction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTeam2Prediction() {
        return team2Prediction;
    }

    public void setTeam2Prediction(int team2Prediction) {
        this.team2Prediction = team2Prediction;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }
}
