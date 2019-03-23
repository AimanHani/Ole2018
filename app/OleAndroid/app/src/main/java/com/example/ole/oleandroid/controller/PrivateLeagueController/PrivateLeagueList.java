package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.PrivateLeagueDAO;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.PrivateLeague;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PrivateLeagueList extends SideMenuBar implements View.OnClickListener{

    PrivateLeagueAdapter privateLeagueAdapter;
    ArrayList<PrivateLeague> leaguelist;
    ListView privateLeagueListView;
    ArrayList<String> allLeagues = new ArrayList<>();
    //int leagueID;
    android.support.constraint.ConstraintLayout football;
    FloatingActionButton createPrivateLeagueBtn;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createPrivateLeagueBtn:
                Intent intent = new Intent(PrivateLeagueList.this, PrivateLeagueCreate.class);
                intent.putExtra("allLeague", allLeagues);
                this.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_list, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        createPrivateLeagueBtn = (FloatingActionButton) contentView.findViewById(R.id.createPrivateLeagueBtn);
        createPrivateLeagueBtn.setOnClickListener(this);

        leaguelist = new ArrayList<>();
        privateLeagueListView = findViewById(R.id.privateLeagueListView);
        PrivateLeagueDAO.clearAllPrivateLeague();
        String url = DBConnection.privateLeagueUrl()+"?method=retrievePrivateLeague&username="+UserDAO.getLoginUser().getUsername();


        Thread thread = new Thread(){
            public void run(){
                retrieveAll();
            }
        };
        thread.start();


        //String url = DBConnection.insertPrivateLeagueUrl()+"?method=retrievePrivateLeague&username="+UserDAO.getLoginUser().getUsername();
        //json.put("method", "insertNew");
        System.out.println("Getting private league list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray privateLeagues = result.getJSONArray("results");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if (privateLeagues.length() > 0) {

                football = findViewById(R.id.football);
                football.setVisibility(View.INVISIBLE);
                for (int i = 0; i < privateLeagues.length(); i++) {
                    JSONObject privateLeagueObject = privateLeagues.getJSONObject(i);

//public PrivateLeague(int leagueId, String leagueName, String prize, String password, Date startDate, Date endDate, String username, int pointsAllocated, int tournamentId, int leagueKeyId)

                    PrivateLeague privateLeague = new PrivateLeague(
                            privateLeagueObject.getInt("leagueID"),
                            privateLeagueObject.getString("leagueName"),
                            privateLeagueObject.getString("prize"),
                            privateLeagueObject.getString("password"),
                            privateLeagueObject.getString("startDate"),
                            privateLeagueObject.getString("endDate"),
                            privateLeagueObject.getString("userName"),
                            privateLeagueObject.getInt("pointsAllocated"),
                            privateLeagueObject.getInt("tournamentID"),
                            privateLeagueObject.getInt("leagueID")


                    );
                    PrivateLeagueDAO.addPrivateleague(privateLeague);
                }
                privateLeagueAdapter = new PrivateLeagueAdapter(PrivateLeagueList.this, PrivateLeagueDAO.getAllPrivateLeague());
                privateLeagueListView.setAdapter(privateLeagueAdapter);

            } else {

            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public void retrieveAll(){
        String url2 = DBConnection.privateLeagueUrl()+"?method=retrieveAllPrivateLeague";
        System.out.println("Getting private league list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url2);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray privateLeagues = result.getJSONArray("results");

            if (privateLeagues.length() > 0) {
                for (int i = 0; i < privateLeagues.length(); i++) {
                    JSONObject privateLeagueObject = privateLeagues.getJSONObject(i);

//public PrivateLeague(int leagueId, String leagueName, String prize, String password, Date startDate, Date endDate, String username, int pointsAllocated, int tournamentId, int leagueKeyId)

                    allLeagues.add(privateLeagueObject.getString("leagueName"));

                }


            } else {

            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        System.out.println("Total Size  = " + allLeagues.size());
    }

}
