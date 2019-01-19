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
    ArrayList<String> leaguelist = new ArrayList<String>();
    ListView publicLeagueListView;
    Button joinleaguebtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicleaguelist);
        publicLeagueListView = findViewById(R.id.publicLeagueListView);
        joinleaguebtn = findViewById(R.id.joinleaguebtn);

        //i put 5,2 to test whether thing works, in the future pass in from database
        leaguelist.add("English Premier League");

        publicLeagueListAdapter = new PublicLeagueListAdapter(PublicLeagueList.this, leaguelist);
        publicLeagueListView.setAdapter(publicLeagueListAdapter);

        joinleaguebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublicLeagueList.this , SpecialList.class);
                startActivity(intent);
            }
        });
    }

}
