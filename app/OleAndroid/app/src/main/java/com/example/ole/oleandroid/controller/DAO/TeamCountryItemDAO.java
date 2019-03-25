package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.CountryItem;
import com.example.ole.oleandroid.model.TeamItems;

import java.util.ArrayList;
import java.util.HashMap;

public class TeamCountryItemDAO {
    public static HashMap<String, TeamItems> teamItemsList = initiateTeamList();
    public static HashMap<String, CountryItem> countryItemsList = initiateCountryList();

    public static ArrayList<TeamItems> mTeamList = initiateTeamArrayList();

    public static HashMap<String, TeamItems> initiateTeamList() {
        HashMap<String, TeamItems> teamItemsList = new HashMap<>();
        teamItemsList.put("Arsenal", new TeamItems("Arsenal", R.drawable.arsenal));
        teamItemsList.put("Bournemouth", new TeamItems("Bournemouth", R.drawable.afc_bournemouth));
        teamItemsList.put("Brighton", new TeamItems("Brighton", R.drawable.brighton));
        teamItemsList.put("Burnley", new TeamItems("Burnley", R.drawable.burnley));
        teamItemsList.put("Cardiff", new TeamItems("Cardiff", R.drawable.cardiff));
        teamItemsList.put("Chelsea", new TeamItems("Chelsea", R.drawable.chelsea));
        teamItemsList.put("Crystal Palace", new TeamItems("Crystal Palace", R.drawable.crystal_palace));
        teamItemsList.put("Everton", new TeamItems("Everton", R.drawable.everton));
        teamItemsList.put("Fulham", new TeamItems("Fulham", R.drawable.fulham));
        teamItemsList.put("Huddersfield", new TeamItems("Huddersfield", R.drawable.hudderfield));
        teamItemsList.put("Leicester", new TeamItems("Leicester", R.drawable.leicester_city));
        teamItemsList.put("Liverpool", new TeamItems("Liverpool", R.drawable.liverpool));
        teamItemsList.put("Manchester City", new TeamItems("Manchester City", R.drawable.manchester_city));
        teamItemsList.put("Manchester United", new TeamItems("Manchester United", R.drawable.manchester_united));
        teamItemsList.put("Newcastle", new TeamItems("Newcastle", R.drawable.newcastle_united));
        teamItemsList.put("Southampton", new TeamItems("Southampton", R.drawable.southampton));
        teamItemsList.put("Tottenham", new TeamItems("Tottenham", R.drawable.tottenham_hotspur));
        teamItemsList.put("Watford", new TeamItems("Watford", R.drawable.watford));
        teamItemsList.put("West Ham", new TeamItems("West Ham", R.drawable.west_ham));
        teamItemsList.put("Wolves", new TeamItems("Wolves", R.drawable.wolverhampton));
        return teamItemsList;
    }

    public static HashMap<String, CountryItem> initiateCountryList() {
        HashMap<String, CountryItem> countryItemsList = new HashMap<>();
        countryItemsList.put("Singapore", new CountryItem("Singapore", R.drawable.singapore));
        countryItemsList.put("Malaysia", new CountryItem("Malaysia", R.drawable.malaysia));
        return countryItemsList;
    }

    public static ArrayList<TeamItems> initiateTeamArrayList() {

        ArrayList<TeamItems> teamItemsList = new ArrayList<>();
        teamItemsList.add(new TeamItems("Arsenal", R.drawable.arsenal));
        teamItemsList.add(new TeamItems("AFC Bournemouth ", R.drawable.afc_bournemouth));
        teamItemsList.add(new TeamItems("Brighton and Hove Albion", R.drawable.brighton));
        teamItemsList.add(new TeamItems("Burnley", R.drawable.burnley));
        teamItemsList.add(new TeamItems("Cardiff City", R.drawable.cardiff));
        teamItemsList.add(new TeamItems("Chelsea", R.drawable.chelsea));
        teamItemsList.add(new TeamItems("Crystal Palace", R.drawable.crystal_palace));
        teamItemsList.add(new TeamItems("Everton", R.drawable.everton));
        teamItemsList.add(new TeamItems("Fulham", R.drawable.fulham));
        teamItemsList.add(new TeamItems("Huddersfield Town", R.drawable.hudderfield));
        teamItemsList.add(new TeamItems("Leicester City", R.drawable.leicester_city));
        teamItemsList.add(new TeamItems("Liverpool", R.drawable.liverpool));
        teamItemsList.add(new TeamItems("Manchester City", R.drawable.manchester_city));
        teamItemsList.add(new TeamItems("Manchester United", R.drawable.manchester_united));
        teamItemsList.add(new TeamItems("Newcastle United", R.drawable.newcastle_united));
        teamItemsList.add(new TeamItems("Southampton", R.drawable.southampton));
        teamItemsList.add(new TeamItems("Tottenham Hotspur", R.drawable.tottenham_hotspur));
        teamItemsList.add(new TeamItems("Watford", R.drawable.watford));
        teamItemsList.add(new TeamItems("West Ham United", R.drawable.west_ham));
        teamItemsList.add(new TeamItems("Wolverhampton Wanderes", R.drawable.wolverhampton));

        return teamItemsList;
    }

    public static int getCountryImageResource(String country) {
        return countryItemsList.get(country).getmFlagImage();
    }

    public static int getTeamImageResource(String team) {
        return teamItemsList.get(team).getmTeamImage();
    }


}
