package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.SideMenuBar;

public class PrivateLeagueSelectLeaguesActivity extends SideMenuBar {

    Button selectDateButton;
    private RadioGroup radioGroup;
    private RadioButton joinleaguebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_select_leagues, null, false);
        super.mDrawerlayout.addView(contentView, 0);


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
