package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ole.oleandroid.R;

public class PrivateLeagueSelectLeaguesActivity extends AppCompatActivity {

    Button selectDateButton;
    private RadioGroup radioGroup;
    private RadioButton joinleaguebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_select_leagues);


        radioGroup = (RadioGroup) findViewById(R.id.radio);
        //joinleaguebtn = findViewById(R.id.joinleaguebtn);

        System.out.println(radioGroup.getCheckedRadioButtonId());

        selectDateButton = findViewById(R.id.selectDateButton);
        selectDateButton.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view){
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    Intent intent = new Intent(PrivateLeagueSelectLeaguesActivity.this, PrivateLeagueSelectDateActivity.class);
                    intent.putExtra("leaguename", extras.getString("leaguename"));
                    intent.putExtra("prize", extras.getString("prize"));
                    intent.putExtra("password", extras.getString("password"));
                    intent.putExtra("pointsAllocated", extras.getString("pointsAllocated"));
                    intent.putExtra("leagueid", "2");
                    startActivity(intent);
                }
            }
        });
    }
}
