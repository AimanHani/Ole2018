package com.example.ole.oleandroid.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueList;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueMain;
import com.example.ole.oleandroid.controller.PublicLeague.PublicLeagueList;

public class HomeLeague extends SideMenuBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home_league);
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.home_league, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        pubLeague = findViewById(R.id.publicCard);
        privLeague = findViewById(R.id.privateCard);
        pubLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomeLeague.this, PublicLeagueList.class);
                startActivity(intent);
            }
        });

        privLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent intent = new Intent(HomeLeague.this, PrivateLeagueMain.class);
                Intent intent = new Intent(HomeLeague.this, PrivateLeagueMain.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        String from = getIntent().getStringExtra("FROM_ACTIVITY");
        if (from != null && from.equals("Login")) {
            Toast.makeText(getBaseContext(), "Logout at the sidebar", Toast.LENGTH_LONG).show();
        } else if (from != null) {
            // do nothing
        } else {
            super.onBackPressed();
        }

    }
}
