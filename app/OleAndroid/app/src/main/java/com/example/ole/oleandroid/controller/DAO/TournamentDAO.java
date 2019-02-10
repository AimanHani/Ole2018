package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.Tournament;

import org.json.JSONObject;

import java.util.ArrayList;

public class TournamentDAO {
    static String id;

    private static ArrayList<Tournament> allTournamentLeague = new ArrayList<>();

    public static ArrayList<Tournament> getAllTournamentLeague() {
        return allTournamentLeague;
    }

    public static void setAllTournamentLeague(ArrayList<Tournament> allTournamentLeague) {
        TournamentDAO.allTournamentLeague = allTournamentLeague;
    }

    public static void clearAllTournamentLeague(){
        TournamentDAO.allTournamentLeague = new ArrayList<>();
    }

    public static void addTournament(Tournament tournament){
        allTournamentLeague.add(tournament);
    }

    public static void setId(String id){
        TournamentDAO.id = id;
    }

    public static String getId(){
        return TournamentDAO.id;
    }
}
