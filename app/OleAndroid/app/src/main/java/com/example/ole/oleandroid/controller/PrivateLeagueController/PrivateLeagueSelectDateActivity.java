package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ole.oleandroid.R;

public class PrivateLeagueSelectDateActivity extends AppCompatActivity {
    EditText startdate, enddate;
    Button selectSpecialsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_select_date);

        /*
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String leaguename = extras.getString("prize");
            System.out.println("hello" + leaguename);
            //The key argument here must match that used in the other activity
        }
        */
        startdate = (EditText) findViewById(R.id.startdate);
        enddate = (EditText) findViewById(R.id.endDate);

        selectSpecialsButton = findViewById(R.id.selectSpecialsButton);
        selectSpecialsButton.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view){
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    Intent intent = new Intent(PrivateLeagueSelectDateActivity.this, SpecialListPrivate.class);
                    intent.putExtra("leaguename", extras.getString("leaguename"));
                    intent.putExtra("prize", extras.getString("prize"));
                    intent.putExtra("password", extras.getString("password"));
                    intent.putExtra("pointsAllocated", extras.getString("pointsAllocated"));
                    intent.putExtra("leagueid", extras.getString("leagueid"));
                    intent.putExtra("startdate", startdate.getText().toString());
                    intent.putExtra("enddate", enddate.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
