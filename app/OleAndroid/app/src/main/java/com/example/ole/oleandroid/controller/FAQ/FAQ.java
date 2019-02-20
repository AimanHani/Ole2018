package com.example.ole.oleandroid.controller.FAQ;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.Leaderboard.Leaderboard;
import com.example.ole.oleandroid.controller.SideMenuBar;

public class FAQ extends SideMenuBar {
    CardView profile;
    CardView pubLeague;
    CardView leaderboard;
    CardView privLeague;
    CardView league;
    CardView askOle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_faq, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        profile = findViewById(R.id.profileCard);
        pubLeague = findViewById(R.id.publicCard);
        privLeague = findViewById(R.id.privateCard);
        league = findViewById(R.id.generalCard);
        leaderboard = findViewById(R.id.leaderboardCard);
        askOle = findViewById(R.id.askCard);

        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQ.this, FAQProfile.class);
                startActivity(intent);
            }
        });

        pubLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQ.this, FAQPublic.class);
                startActivity(intent);
            }
        });

        privLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQ.this, FAQPrivate.class);
                startActivity(intent);
            }
        });

        league.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQ.this, FAQLeague.class);
                startActivity(intent);
            }
        });

        askOle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQ.this, AskOle.class);
                startActivity(intent);
            }
        });

        leaderboard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQ.this, FAQLeaderboard.class);
                startActivity(intent);
            }
        });

        /*frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                aOne.setVisibility(View.GONE);
                return true;
            }
        });*/



        /*qOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_faq);
                aOne.setVisibility(View.VISIBLE);
            }
        });*/
    }
}
