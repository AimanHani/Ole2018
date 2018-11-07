package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ole.oleandroid.R;

public class Home extends AppCompatActivity {

    Button publicLeague;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);


        publicLeague = (Button) findViewById(R.id.publicLeague);

        publicLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, PublicLeaguePage.class);
                Bundle b = getIntent().getExtras();

                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
