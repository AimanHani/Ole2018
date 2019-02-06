package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ole.oleandroid.R;

public class PrivateLeagueFinaltempActivity extends AppCompatActivity{

    Button PrivateLeagueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_finaltemp);
        PrivateLeagueText = findViewById(R.id.PrivateLeagueText);


        PrivateLeagueText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(PrivateLeagueFinaltempActivity.this, HomeTile.class);
                Bundle b = getIntent().getExtras();
                if (b != null) {

                    intent.putExtras(b);
                }
                //intent.putExtra("User", u);
                startActivity(intent);
            }
        });
    }
}
