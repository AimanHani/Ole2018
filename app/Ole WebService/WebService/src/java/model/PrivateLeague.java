/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class PrivateLeague {
    private String leagueName;
    private String password;
    private String specials;
    private String teamPref;
    private Date startDate;
    private Date endDate;
    private int leagueId;

    public PrivateLeague(String leagueName, String password, String specials, String teamPref, Date startDate, Date endDate, int leagueId, ArrayList<User> participants) {
        this.leagueName = leagueName;
        this.password = password;
        this.specials = specials;
        this.teamPref = teamPref;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leagueId = leagueId;
        this.participants = participants;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }
    private ArrayList<User>participants;

    public PrivateLeague() {
    }

    public PrivateLeague(String leagueName, String password, String specials, String teamPref, Date startDate, Date endDate) {
        this.leagueName = leagueName;
        this.password = password;
        this.specials = specials;
        this.teamPref = teamPref;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getSpecials() {
        return specials;
    }

    public void setSpecials(String specials) {
        this.specials = specials;
    }

    public String getTeamPref() {
        return teamPref;
    }

    public void setTeamPref(String teamPref) {
        this.teamPref = teamPref;
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
