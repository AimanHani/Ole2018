package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ole.oleandroid.R;

public class LoadingPage extends AppCompatActivity {
    //Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 3 seconds to show the loading page, After 3 seconds redirect to another intent
                    sleep(3*1000);

            //For testing purposes, for onboarding to happen all the time with the following two lines
                    //Intent i=new Intent(getBaseContext(),OnBoardingActivity.class);
                    //startActivity(i);

                    //Uncomment the if loop (line 28-35) for onboarding to work when its only first time.
                    if (isFirstTime()) {
                        // What you do when the Application is Opened First time Goes here
                        Intent i=new Intent(getBaseContext(),OnBoardingActivity.class);
                        startActivity(i);
                    }else{
                        Intent j = new Intent(getBaseContext(),Login.class );
                        startActivity(j);
                    }
                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }

    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();
        }
        return !ranBefore;
    }
}
