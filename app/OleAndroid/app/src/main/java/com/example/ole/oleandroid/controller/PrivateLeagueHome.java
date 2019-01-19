package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.ole.oleandroid.R;

import com.example.ole.oleandroid.pageController.FAQ;

public class PrivateLeagueHome extends AppCompatActivity {

    FloatingActionButton addPrivateLeague;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_home);

        addPrivateLeague = findViewById(R.id.addPrivateLeague);

        addPrivateLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(PrivateLeagueHome.this, private_league_create.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
