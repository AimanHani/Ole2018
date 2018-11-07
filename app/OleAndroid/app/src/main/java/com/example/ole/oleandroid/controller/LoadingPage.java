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
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    // After 5 seconds redirect to another intent
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

        //login = (Button) findViewById(R.id.login);

        //login.setOnClickListener(new View.OnClickListener() {

        //    @Override
       //     public void onClick(View view)

        //       Intent intent = new Intent(LoadingPage.this,Login.class);
         //       startActivity(intent);
        //    }
        //});

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
