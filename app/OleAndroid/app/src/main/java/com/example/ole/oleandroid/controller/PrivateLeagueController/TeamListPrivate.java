package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ole.oleandroid.controller.UserDAO;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.Teams;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamListPrivate extends AppCompatActivity {
    TeamListPrivateAdapter teamListPrivateAdapter;
    ListView teamListView;
    Button confirmteambtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamlistprivate);
        teamListView = findViewById(R.id.teamListView);
        confirmteambtn = findViewById(R.id.confirmteambtn);
        ArrayList<Teams> teamsList = new ArrayList<>();


        Bundle bundle = getIntent().getExtras();

        String url = DBConnection.getTeams();
        System.out.println("Getting teams list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            if (response != null) {
                JSONObject result = new JSONObject(response);
                JSONArray publicLeagues = result.getJSONArray("results");
                System.out.println(publicLeagues.length());
                if (publicLeagues.length() > 0) {
                    for (int i = 0; i < publicLeagues.length(); i++) {
                        JSONObject teamObject = publicLeagues.getJSONObject(i);
                        Teams team = new Teams(
                                teamObject.getInt("teamId"),
                                teamObject.getString("name")
                        );
                        teamsList.add(team);
                    }

                } else {
                    //loadSamePage();
                }
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }


        teamListPrivateAdapter = new TeamListPrivateAdapter(TeamListPrivate.this, teamsList);
        teamListView.setAdapter(teamListPrivateAdapter);

        confirmteambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                User loginUser = UserDAO.getLoginUser();

/*
                    System.out.println("Creating Private League");
                    final String[] status = {"error"};
                    JSONObject json = new JSONObject();
                    try {
                        Bundle extras = getIntent().getExtras();
                        if (extras != null) {
                            json.put("method", "insertNew");
                            json.put("username", loginUser.getUserName());
                            json.put("password", extras.getString("password"));
                            json.put("prize", extras.getString("prize"));
                            json.put("leagueName", extras.getString("leaguename"));
                            json.put("tournamentId", extras.getString("leagueid"));
                            json.put("pointsAllocated", extras.getString("pointsAllocated"));
                            json.put("startDate", extras.getString("startdate"));
                            json.put("endDate", extras.getString("enddate"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String url = DBConnection.insertPrivateLeagueUrl();

                    PostHttp connection = new PostHttp();
                    String response = null;
                    //System.out.println("HAHAHAHHA" + json.toString());

                try {
                    response = connection.post(url, json.toString());
                    JSONObject result = new JSONObject(response);
                    status[0] = result.getString("status");

                    if (status[0].equals("success")) {
                        System.out.println(response);
                        intent = new Intent(TeamListPrivate.this, PrivateLeagueMain.class);
                        startActivity(intent);
                    } else {
                        //loadSamePage();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
*/intent = new Intent(TeamListPrivate.this, PrivateLeagueMain.class);
                startActivity(intent);
            }
        });

    }
}
