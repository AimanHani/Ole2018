package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.pageController.FAQ;

public class Home extends AppCompatActivity {

    TextView publicLeagues;
    TextView privateLeagues;
    TextView faq;
    TextView profile;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        publicLeagues = findViewById(R.id.publicLeagues);
        privateLeagues = findViewById(R.id.privateLeagues);
        faq = findViewById(R.id.faq);
        profile = findViewById(R.id.profile);
        System.out.println("home");

        publicLeagues.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //link to epl page, currently with only English Premier League
                Intent intent = new Intent(Home.this, PublicLeagueList.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        privateLeagues.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, PrivateLeagueHome.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, FAQ.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, Profile.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
