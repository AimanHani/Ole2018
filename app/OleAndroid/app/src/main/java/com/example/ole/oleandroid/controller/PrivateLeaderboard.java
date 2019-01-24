package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ole.oleandroid.R;

public class PrivateLeaderboard extends Fragment implements View.OnClickListener {

    View view;
    Button createPrivLeague;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        /**
         *  Remember to put the no connection
         */

        view = inflater.inflate(R.layout.activity_private_leaderboard, container, false);
        createPrivLeague = (Button) view.findViewById(R.id.createPrivateLeague);
        createPrivLeague.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createPrivateLeague:
                Intent intent = new Intent(getActivity(), private_league_create.class);
                this.startActivity(intent);
        }
    }
    //for backend:
    //use bundle to pass the data (list of specials)
}
