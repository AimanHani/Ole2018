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
public class AllPublicLeague {
    private int leagueID;
    private String prize;
    private int tournamentID;
    private int pointsAllocated;
    private String leagueName;
    

 public AllPublicLeague(int leagueID, String prize, int tournamentID, int pointsAllocated, String leagueName) {
        this.leagueID = leagueID;
        this.prize = prize;
        this.tournamentID = tournamentID;
        this.pointsAllocated = pointsAllocated;
        this.leagueName = leagueName;
    }
    
    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
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

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
    
}
