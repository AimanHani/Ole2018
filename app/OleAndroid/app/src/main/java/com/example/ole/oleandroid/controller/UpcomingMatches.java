package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ole.oleandroid.R;

public class UpcomingMatches extends Fragment implements View.OnClickListener {

        View view;
        Button predictspecials;
        /**
         *  Remember to put the no connection
         */
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_upcoming_matches, container, false);
        predictspecials = (Button) view.findViewById(R.id.predictspecials);
        predictspecials.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.predictspecials:
                Intent intent = new Intent(getActivity(), SpecialList.class);
                this.startActivity(intent);
        }
    }
    //for backend:
    //use bundle to pass the data (list of specials)
}
