package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.PrivateLeagueDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.PrivateLeague;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Mine extends Fragment implements View.OnClickListener{
    View view;
    PrivateLeagueAdapter privateLeagueAdapter;
    ArrayList<PrivateLeague> leaguelist;
    ListView privateLeagueListView;
    ArrayList<String> allLeagues = new ArrayList<>();
    android.support.constraint.ConstraintLayout football;
    FloatingActionButton createPrivateLeagueBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_mine, container, false);

        createPrivateLeagueBtn = view.findViewById(R.id.createPrivateLeagueBtn);
        createPrivateLeagueBtn.setOnClickListener(this);

        leaguelist = new ArrayList<>();
        privateLeagueListView = view.findViewById(R.id.privateLeagueListView);
        PrivateLeagueDAO.clearAllPrivateLeague();
        String url = DBConnection.insertPrivateLeagueUrl()+"?method=retrievePrivateLeague&username="+UserDAO.getLoginUser().getUsername();

        Thread thread = new Thread(){
            public void run(){
                retrieveAll();
            }
        };
        thread.start();
        System.out.println("Getting private league list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            //System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray privateLeagues = result.getJSONArray("results");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if (privateLeagues.length() > 0) {

                football = view.findViewById(R.id.football);
                football.setVisibility(View.INVISIBLE);
                for (int i = 0; i < privateLeagues.length(); i++) {
                    JSONObject privateLeagueObject = privateLeagues.getJSONObject(i);
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
            } else { }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        privateLeagueAdapter = new PrivateLeagueAdapter(getContext(), PrivateLeagueDAO.getAllPrivateLeague());
        privateLeagueListView.setAdapter(privateLeagueAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createPrivateLeagueBtn:
                Intent intent = new Intent(getActivity(), PrivateLeagueCreate.class);
                this.startActivity(intent);
        }
    }

    public void retrieveAll(){
        String url2 = DBConnection.insertPrivateLeagueUrl()+"?method=retrieveAllPrivateLeague";
        System.out.println("Getting private league list");
        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url2);
            //System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray privateLeagues = result.getJSONArray("results");
            if (privateLeagues.length() > 0) {
                for (int i = 0; i < privateLeagues.length(); i++) {
                    JSONObject privateLeagueObject = privateLeagues.getJSONObject(i);
                    allLeagues.add(privateLeagueObject.getString("leagueName"));
                }
            } else {}
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        System.out.println("Total Size  = " + allLeagues.size());
    }
}
