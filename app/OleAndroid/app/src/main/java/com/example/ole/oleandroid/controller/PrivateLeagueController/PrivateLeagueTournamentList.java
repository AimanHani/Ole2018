package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ole.oleandroid.controller.DAO.PrivateLeagueDAO;
import com.example.ole.oleandroid.controller.DAO.TournamentDAO;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.Specials;
import com.example.ole.oleandroid.model.Tournament;
import com.example.ole.oleandroid.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PrivateLeagueTournamentList extends SideMenuBar implements View.OnClickListener{
    PrivateLeagueSelectLeaguesActivity privateLeagueSelectLeaguesActivity;
    ArrayList<Tournament> leaguelist;
    ListView tournamentListView;
    Button selectDateButton;
    String tournament = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_private_league_tournament_list);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_tournament_list, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        leaguelist = new ArrayList<>();
        tournamentListView = findViewById(R.id.tournamentListView);
        TournamentDAO.clearAllTournamentLeague();
        String url = DBConnection.privateLeagueUrl()+"?method=retrieveTournament";
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

                //ArrayList<Tournament> allLeagues = TournamentDAO.getAllTournamentLeague();
                privateLeagueSelectLeaguesActivity = new PrivateLeagueSelectLeaguesActivity(PrivateLeagueTournamentList.this, TournamentDAO.getAllTournamentLeague());
                tournamentListView.setAdapter(privateLeagueSelectLeaguesActivity);

                selectDateButton = findViewById(R.id.selectDateButton);
                selectDateButton.setOnClickListener(new View.OnClickListener( ) {
                    @Override
                    public void onClick(View view){
                        ///System.out.println("tournament pls..." + TournamentDAO.getId());

                        Bundle extras = getIntent().getExtras();
                        if (extras != null) {
                            Intent intent = new Intent(PrivateLeagueTournamentList.this, PrivateLeagueSelectDateActivity.class);
                            intent.putExtra("leaguename", extras.getString("leaguename"));
                            intent.putExtra("prize", extras.getString("prize"));
                            intent.putExtra("password", extras.getString("password"));
                            intent.putExtra("pointsAllocated", extras.getString("pointsAllocated"));
                            intent.putExtra("leagueid", TournamentDAO.getId());
                            startActivity(intent);
                        }
                    }
                });

            } else {

            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }



    }

    @Override
    public void onClick(View v) {

    }
}
