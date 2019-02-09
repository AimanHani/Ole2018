package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ole.oleandroid.R;

public class Mine extends Fragment implements View.OnClickListener{

    FloatingActionButton createPrivateLeagueBtn;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // checks for Internet connection before displaying activity / fragment
        // create a NoConnection java class for this to work
        /*if(!Util.isNetworkAvailable(this.getContext().getApplicationContext())) {
            Intent toNoConnectionActivity = new Intent(PublicLeagueTab.this.getActivity(), NoConnection.class);
            startActivity(toNoConnectionActivity);

            PublicLeagueTab.this.getActivity().finish();
        }*/

        view = inflater.inflate(R.layout.activity_mine, container, false);

        createPrivateLeagueBtn = (FloatingActionButton) view.findViewById(R.id.createPrivateLeagueBtn);
        createPrivateLeagueBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createPrivateLeagueBtn:
                Intent intent = new Intent(getActivity(), PrivateLeagueCreate.class);
                this.startActivity(intent);
        }
    }
}
