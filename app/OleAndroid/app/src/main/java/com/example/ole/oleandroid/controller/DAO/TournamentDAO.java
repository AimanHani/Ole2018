package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.Tournament;

import org.json.JSONArray;
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

    public static void clearAllTournamentLeague() {
        TournamentDAO.allTournamentLeague = new ArrayList<>();
    }

    public static void addTournament(Tournament tournament) {
        allTournamentLeague.add(tournament);
    }

    public static void setId(String id) {
        TournamentDAO.id = id;
    }

    public static String getId() {
        return TournamentDAO.id;
    }


    public static void loadAllTournaments() {
        TournamentDAO.clearAllTournamentLeague();
        String url = DBConnection.insertPrivateLeagueUrl() + "?method=retrieveTournament";
        System.out.println("Getting tournament list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray privateLeagues = result.getJSONArray("results");
            if (privateLeagues.length() > 0) {
                for (int i = 0; i < privateLeagues.length(); i++) {
                    JSONObject tournamentObj = privateLeagues.getJSONObject(i);
                    Tournament tournament = new Tournament(
                            tournamentObj.getString("tournamentId"),
                            tournamentObj.getString("name")
                    );
                    TournamentDAO.addTournament(tournament);
                }
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
