package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.PrivateLeagueDAO;
import com.example.ole.oleandroid.controller.DAO.PrivateMembersDAO;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.PrivateMembers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PrivateLeagueDetails extends SideMenuBar implements View.OnClickListener{
    View view;
    private ArrayList<PrivateMembers> memberslist;//data source of the list adapter
    ArrayList<PrivateLeague> leaguelist;
    Button button;
    PrivateLeagueDetailsAdapter privateLeagueDetailsAdapter;
    ListView privateLeagueListView;
    TextView privatePrizeInput, leagueNameInput,creator, privatepoints;
    PrivateLeague privateleague = null;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    Intent intent = getIntent();
    String leagueid = intent.getStringExtra("leagueid");

    View contentView = inflater.inflate(R.layout.activity_private_league_details, null, false);
    super.mDrawerlayout.addView(contentView, 0);
        System.out.println("Retrieving... " + leagueid);
        final String[] status = {"error"};

        JSONObject json = new JSONObject();
        try {
            json.put("method", "retrieveLeagueName");
            json.put("leagueId", leagueid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = DBConnection.insertPrivateLeagueUrl();

        PostHttp connection = new PostHttp();
        String response = null;
        try {
            response = connection.post(url, json.toString());
            System.out.println(response);

            JSONObject result = new JSONObject(response);
            status[0] = result.getString("status");

            if (status[0].equals("success")) {
                JSONObject league = result.getJSONObject("league");
                int leagueID = league.getInt("LeagueId");
                int tournamentId = league.getInt("tournamentId");
                int pointsAllocated = league.getInt("pointsAllocated");
                String leagueName = league.getString("leagueName");
                String privateLeagueID = league.getString("privateLeagueID");
                String prize = league.getString("prize");
                String startDate = league.getString("startDate");
                String endDate = league.getString("endDate");
                int leagueKeyId = league.getInt("leagueKeyId");
                String username = league.getString("userName");
                String password = league.getString("password");


                try {
                    privateleague = new PrivateLeague(leagueID, leagueName, prize, password, startDate, endDate, username, pointsAllocated, tournamentId, leagueKeyId);
                    PrivateLeague.setPrivateLeague(privateleague);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
        }

        PrivateLeagueDAO.clearAllPrivateLeague();
        String url2 = DBConnection.insertPrivateLeagueUrl()+"?method=retrieveMembers&leagueid="+leagueid;

        System.out.println("Getting private league list");

        GetHttp getConnection = new GetHttp();
        response = null;
        try {
            response = getConnection.run(url2);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray members = result.getJSONArray("results");

            PrivateMembersDAO.clearAllPrivateMembers();
            if (members.length() > 0) {


                for (int i = 0; i < members.length(); i++) {
                    JSONObject membersObj = members.getJSONObject(i);

                    PrivateMembers privateMembers = new PrivateMembers(

                            membersObj.getInt("logid"),
                            membersObj.getString("username"),
                            membersObj.getInt("leagueid"),
                            membersObj.getInt("points")
                    );
                    PrivateMembersDAO.addPrivateMembers(privateMembers);
                }
            } else {            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
            privatePrizeInput = findViewById(R.id.privatePrizeInput);
            leagueNameInput = findViewById(R.id.leagueNameInput);
            creator = findViewById(R.id.creator);
            privatepoints = findViewById(R.id.privatepoints);

        if (privateleague != null) {
            privatePrizeInput.setText(privateleague.getPrize());
            leagueNameInput.setText(privateleague.getLeagueName());
            creator.setText(privateleague.getUsername());
            privatepoints.setText(privateleague.getPointsAllocated()+"");
        }
        privateLeagueListView = findViewById(R.id.privateLeagueListView);
        privateLeagueDetailsAdapter = new PrivateLeagueDetailsAdapter(PrivateLeagueDetails.this, PrivateMembersDAO.getAllPrivateMembers());
        privateLeagueListView.setAdapter(privateLeagueDetailsAdapter);
}

    @Override
    public void onClick(View v) {    }
}

