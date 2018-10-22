package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ole.oleandroid.R;

public class Home extends AppCompatActivity {
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
            }
        });

    }
}
