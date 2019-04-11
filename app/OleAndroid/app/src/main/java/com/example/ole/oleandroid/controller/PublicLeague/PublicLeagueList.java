package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.HomeLeague;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueDetails;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueMain;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.model.PublicLeague;

import java.util.ArrayList;

public class PublicLeagueList extends SideMenuBar {

    PublicLeagueListAdapter publicLeagueListAdapter;
    ArrayList<PublicLeague> leaguelist;
    ListView publicLeagueListView;
    int leagueID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.publicleaguelist); //contain item list view e.g. item1, item2

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.publicleaguelist, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        leaguelist = new ArrayList<>();
        publicLeagueListView = findViewById(R.id.publicLeagueListView);

        PublicLeagueDAO.getPublicLeague();
//        Thread thread = new Thread(){
//            public void run(){
//                PublicLeagueDAO.getPublicLeagueStats();
//            }
//        };
//        thread.start();

        //PublicLeagueDAO.addPublicleague(new PublicLeague(1009,2,"Arsenal Jersey", 3, "Public League","Premier League", 5, false));

        publicLeagueListAdapter = new PublicLeagueListAdapter(PublicLeagueList.this, PublicLeagueDAO.getAllPublicLeagueList());
        publicLeagueListView.setAdapter(publicLeagueListAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PublicLeagueList.this, HomeLeague.class);
        intent.putExtra("FROM_ACTIVITY", "PublicLeagueList");
        startActivity(intent);
    }
}
