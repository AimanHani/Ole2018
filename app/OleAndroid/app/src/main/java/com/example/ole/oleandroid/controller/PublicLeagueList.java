package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ole.oleandroid.R;

import java.util.ArrayList;

public class PublicLeagueList extends AppCompatActivity{

    PublicLeagueListAdapter publicLeagueListAdapter;
    ArrayList<String> leaguelist;
    ListView publicLeagueListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicleaguelist); //contain item list view e.g. item1, item2
        leaguelist = new ArrayList<String>();
        publicLeagueListView = findViewById(R.id.publicLeagueListView);

        //i put 5,2 to test whether thing works, in the future pass in from database
        leaguelist.add("English Premier League");
        leaguelist.add("League 2");
        leaguelist.add("League 3");
        leaguelist.add("League 4");

        publicLeagueListAdapter = new PublicLeagueListAdapter(PublicLeagueList.this, leaguelist);
        //publicLeagueListView.setAdapter(publicLeagueListAdapter);
        publicLeagueListView.setAdapter(publicLeagueListAdapter);

    }

    //league logo
    //league name
    //join btn

}
