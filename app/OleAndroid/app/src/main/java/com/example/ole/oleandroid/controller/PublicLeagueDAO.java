package com.example.ole.oleandroid.controller;

import com.example.ole.oleandroid.model.PublicLeague;

import java.util.ArrayList;

public class PublicLeagueDAO {
    private static ArrayList<PublicLeague> allPublicLeague = new ArrayList<>();

    public static ArrayList<PublicLeague> getAllPublicLeague() {
        return allPublicLeague;
    }

    public static void setAllPublicLeague(ArrayList<PublicLeague> allPublicLeague) {
        PublicLeagueDAO.allPublicLeague = allPublicLeague;
    }

    public static void clearAllPublicLeague(){
        PublicLeagueDAO.allPublicLeague = new ArrayList<>();
    }

    public static void addPublicleague(PublicLeague publicLeague){
        allPublicLeague.add(publicLeague);
    }
}
