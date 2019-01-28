package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.User;


public class private_league_details extends AppCompatActivity {
    //TextView leagueNameInput;
    Button predictButton;
    EditText privatePrizeInput, leagueNameInput;
    TextView creator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_details);

        predictButton = findViewById(R.id.predictButton);
        privatePrizeInput = findViewById(R.id.privatePrizeInput);
        leagueNameInput = findViewById(R.id.leagueNameInput);
        creator = findViewById(R.id.creator);
        PrivateLeague privateLeague = PrivateLeague.getPrivateLeague();


        //System.out.println("hehhehee" + privateLeague.getPrize());


        privatePrizeInput.setText(privateLeague.getPrize());
        leagueNameInput.setText(privateLeague.getLeagueName());
        creator.setText(privateLeague.getUsername());


        predictButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(private_league_details.this, PrivateLeagueTabActivity.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }




}

