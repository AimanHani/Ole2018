package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.PrivateLeague;


public class private_league_details extends AppCompatActivity {
    TextView leagueNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_details);

/*
        Intent i = getIntent();
        final PrivateLeague pl = (PrivateLeague)i.getSerializableExtra("PL");

        leagueNameInput = findViewById(R.id.leaguename);

        leagueNameInput.setText(pl.getLeagueName());
*/
    }
}
