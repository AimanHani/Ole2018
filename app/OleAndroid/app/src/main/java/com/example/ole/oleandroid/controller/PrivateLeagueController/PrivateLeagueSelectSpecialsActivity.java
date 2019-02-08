package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.SideMenuBar;

public class PrivateLeagueSelectSpecialsActivity extends SideMenuBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_select_specials, null, false);
        super.mDrawerlayout.addView(contentView, 0);
    }
}
