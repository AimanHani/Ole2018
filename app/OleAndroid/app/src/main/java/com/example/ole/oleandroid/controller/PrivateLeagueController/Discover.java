package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ole.oleandroid.R;

public class Discover extends Fragment {

    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // checks for Internet connection before displaying activity / fragment
        // create a NoConnection java class for this to work
        /*if(!Util.isNetworkAvailable(this.getContext().getApplicationContext())) {
            Intent toNoConnectionActivity = new Intent(PublicLeagueTab.this.getActivity(), NoConnection.class);
            startActivity(toNoConnectionActivity);

            PublicLeagueTab.this.getActivity().finish();
        }*/

        view = inflater.inflate(R.layout.activity_discover, container, false);
        return view;
    }
}