package com.example.ole.oleandroid.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.ole.oleandroid.R;

import java.util.ArrayList;

public class PublicLeagueList extends AppCompatActivity{

    PublicLeagueListAdapter publicLeagueListAdapter;
    ArrayList<Integer> scoreList;
    ListView publicLeagueListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicleaguelist);
        scoreList = new ArrayList<>();
        publicLeagueListView = findViewById(R.id.publicLeagueListView);

        //i put 5,2 to test whether thing works, in the future pass in from database
        scoreList.add(5);
        scoreList.add(2);

        publicLeagueListAdapter = new PublicLeagueListAdapter(PublicLeagueList.this, scoreList);
        publicLeagueListView.setAdapter(publicLeagueListAdapter);

    }

}
