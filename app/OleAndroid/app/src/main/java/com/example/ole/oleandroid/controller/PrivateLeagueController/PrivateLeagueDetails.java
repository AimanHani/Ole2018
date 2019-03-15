package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    LinearLayout blackoutimage;
    FloatingActionButton main, predictSpecial, predictMatch;
    Animation FoodFabOpen, FoodFabClose, FabRClockwise, FabRAntiClockwise, Fadein, Fadeout;
    TextView specialtext, matchtext, mainview;
    boolean isOpen = false;

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

    blackoutimage = findViewById(R.id.blackoutimage);
    main = findViewById(R.id.floatingActionButton);
    predictSpecial = findViewById(R.id.predictSpecial);
    predictMatch = findViewById(R.id.predictMatch);
    mainview = findViewById(R.id.mainview);

    // enable animations for FloatingActionButton
    FoodFabOpen = AnimationUtils.loadAnimation(this, R.anim.foodfabopen);
    FoodFabClose = AnimationUtils.loadAnimation(this, R.anim.foodfabclose);
    FabRClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise);
    FabRAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise);
    Fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
    Fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);

    specialtext = findViewById(R.id.specialtext);
    matchtext = findViewById(R.id.matchtext);

    main.bringToFront();
    main.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isOpen) {
                blackoutimage.startAnimation(Fadeout);
                blackoutimage.setVisibility(View.GONE);
                predictSpecial.startAnimation(FoodFabClose);
                predictMatch.startAnimation(FoodFabClose);
                main.startAnimation(FabRAntiClockwise);
                predictSpecial.setClickable(false);
                predictMatch.setClickable(false);
                specialtext.setVisibility(View.GONE);
                matchtext.setVisibility(View.GONE);
                isOpen = false;


            } else {
                blackoutimage.setVisibility(View.VISIBLE);
                blackoutimage.startAnimation(Fadein);
                predictSpecial.startAnimation(FoodFabOpen);
                predictMatch.startAnimation(FoodFabOpen);
                main.startAnimation(FabRClockwise);
                predictSpecial.setClickable(true);
                predictMatch.setClickable(true);
                specialtext.setVisibility(View.VISIBLE);
                matchtext.setVisibility(View.VISIBLE);
                isOpen = true;

                predictMatch.setOnClickListener(this);
                predictSpecial.setOnClickListener(this);
                blackoutimage.setOnClickListener(this);
            }
        }
    });

}

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.blackoutimage:
                blackoutimage.startAnimation(Fadeout);
                blackoutimage.setVisibility(View.GONE);
                predictSpecial.startAnimation(FoodFabClose);
                predictMatch.startAnimation(FoodFabClose);
                main.startAnimation(FabRAntiClockwise);
                predictSpecial.setClickable(false);
                predictMatch.setClickable(false);
                specialtext.setVisibility(View.GONE);
                matchtext.setVisibility(View.GONE);
                isOpen = false;
                break;

            case R.id.specialtext:
                //Intent = new Intent(this.getActivity(), xxx.class);
                //startActivity();
                break;

            case R.id.matchtext:
                //Intent = new Intent(this.getActivity(), xxx.class);
                //startActivity();
                break;

            case R.id.floatingActionButton:
                //mainview.setText("BYEBYE");
                if (isOpen) {
                    blackoutimage.startAnimation(Fadeout);
                    blackoutimage.setVisibility(View.GONE);
                    predictSpecial.startAnimation(FoodFabClose);
                    predictMatch.startAnimation(FoodFabClose);
                    main.startAnimation(FabRAntiClockwise);
                    predictSpecial.setClickable(false);
                    predictMatch.setClickable(false);
                    specialtext.setVisibility(View.GONE);
                    matchtext.setVisibility(View.GONE);
                    isOpen = false;


                } else {
                    blackoutimage.setVisibility(View.VISIBLE);
                    blackoutimage.startAnimation(Fadein);
                    predictSpecial.startAnimation(FoodFabOpen);
                    predictMatch.startAnimation(FoodFabOpen);
                    main.startAnimation(FabRClockwise);
                    predictSpecial.setClickable(true);
                    predictMatch.setClickable(true);
                    specialtext.setVisibility(View.VISIBLE);
                    matchtext.setVisibility(View.VISIBLE);
                    isOpen = true;

                    predictMatch.setOnClickListener(this);
                    predictSpecial.setOnClickListener(this);
                    blackoutimage.setOnClickListener(this);
                }

                break;
        }
    }

}

