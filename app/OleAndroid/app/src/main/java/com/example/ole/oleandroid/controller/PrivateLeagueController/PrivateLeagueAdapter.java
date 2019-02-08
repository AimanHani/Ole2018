package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ole.oleandroid.R;


import com.example.ole.oleandroid.controller.Leagues;
import com.example.ole.oleandroid.controller.PrivateLeagueDAO;
import com.example.ole.oleandroid.controller.PublicLeagueListAdapter;
import com.example.ole.oleandroid.controller.UserDAO;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.PublicLeague;

import java.util.ArrayList;
import java.util.Arrays;

public class PrivateLeagueAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context; //context
    private ArrayList<PrivateLeague> leaguelist;//data source of the list adapter
    private PrivateLeagueAdapter.ViewHolder viewHolder;
    private Activity view;
    FloatingActionButton createPrivateLeagueBtn;

    private static class ViewHolder {
        TextView league;
        Button joinleaguebtn;
    }

    //public constructor
    // passes in a PublicLeague Arraylist so will be able to retrieve the logId as well and not just the name
    public PrivateLeagueAdapter(Context context, ArrayList<PrivateLeague> leaguelist) {
        this.context = context;
        this.leaguelist = leaguelist;
    }

    @Override
    public int getCount() {
        return leaguelist.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return leaguelist.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            viewHolder = new PrivateLeagueAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_private_league_adapter, parent, false);


        }

        /**
         * This will tell initialize the textview element in publicleaguelistlayout
         */

        if(leaguelist.size()>0 && leaguelist.get(0).getLeagueName()!=null){
            viewHolder.league = convertView.findViewById(R.id.leaguename);
            viewHolder.joinleaguebtn = convertView.findViewById(R.id.joinleaguebtn);
            viewHolder.league.setText(leaguelist.get(position).getLeagueName());
            viewHolder.joinleaguebtn.setOnClickListener(this);
        }else{

        }




        return convertView;// returns the view for the current row
    }



    public void onClick(View view){

    }

}
