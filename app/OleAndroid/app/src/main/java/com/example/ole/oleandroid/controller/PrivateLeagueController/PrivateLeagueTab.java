package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ole.oleandroid.R;

public class PrivateLeagueTab extends Fragment {

    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        /**
         *  Remember to put the no connection
         */

        view = inflater.inflate(R.layout.activity_private_league_home, container, false);



        return view;
    }

}
