package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ole.oleandroid.R;

public class Main extends AppCompatActivity {

    Button publicLeague;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        publicLeague = (Button) findViewById(R.id.publicLeague);

        publicLeague.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Main.this, PublicLeaguePage.class);
                startActivity(intent);
            }
        });
    }
}
