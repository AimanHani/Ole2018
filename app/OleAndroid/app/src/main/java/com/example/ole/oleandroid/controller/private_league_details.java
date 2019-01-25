package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.PrivateLeague;


public class private_league_details extends AppCompatActivity {
    //TextView leagueNameInput;
    Button predictButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_details);

        predictButton = findViewById(R.id.predictButton);

        predictButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(private_league_details.this, PrivateLeagueTabActivity.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

/*
        Intent i = getIntent();
        final PrivateLeague pl = (PrivateLeague)i.getSerializableExtra("PL");

        leagueNameInput = findViewById(R.id.leaguename);

        leagueNameInput.setText(pl.getLeagueName());
*/
}

