package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.Specials;
import com.example.ole.oleandroid.model.Tournament;
import com.example.ole.oleandroid.controller.DAO.TournamentDAO;

import java.util.ArrayList;

public class PrivateLeagueSelectLeaguesActivity extends BaseAdapter implements View.OnClickListener{

    Button selectDateButton;
    private RadioGroup radio;
    private RadioButton joinleaguebtn;


    private Context context; //context
    private ArrayList<Tournament> leaguelist;//data source of the list adapter
    private PrivateLeagueSelectLeaguesActivity.ViewHolder viewHolder;
    private Activity view;

    private static class ViewHolder {
        TextView leaguename;
        //TextView league;
        RadioGroup radio;
        RadioButton joinleaguebtn;
        Button selectDateButton;
    }

    //public constructor
    // passes in a PublicLeague Arraylist so will be able to retrieve the logId as well and not just the name
    public PrivateLeagueSelectLeaguesActivity(Context context, ArrayList<Tournament> leaguelist) {
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
            viewHolder = new PrivateLeagueSelectLeaguesActivity.ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_private_league_select_leagues, parent, false);


            /**
             * This will tell initialize the textview element in publicleaguelistlayout
             */
            if (leaguelist.size() > 0 && leaguelist.get(0).getName() != null) {
                viewHolder.radio = convertView.findViewById(R.id.radio);
                viewHolder.joinleaguebtn = convertView.findViewById(R.id.joinleaguebtn);
                viewHolder.leaguename = convertView.findViewById(R.id.leaguename);
                viewHolder.leaguename.setText(leaguelist.get(position).getName());
            } else {

            }
            convertView.setTag(viewHolder);


            viewHolder.radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    Tournament s = (Tournament) group.getTag();
                    s.setChecked(group.isSelected());
                    System.out.println(s.getTournamentId());
                    TournamentDAO.setId(s.getTournamentId());
                }
            });
        }else    {
            // the getTag returns the viewHolder object set as a tag to the view
            viewHolder = (PrivateLeagueSelectLeaguesActivity.ViewHolder)convertView.getTag();
        }


        Tournament s = leaguelist.get(position);
        viewHolder.leaguename.setText("" +  s.getName() + "");
//        viewHolder.checkPrivateTeam.setText(t.getTeamId());
        s.setChecked(s.getChecked());
        viewHolder.radio.setTag(s);


        TournamentDAO.setAllTournamentLeague(leaguelist);
        return convertView;// returns the view for the current row
    }

    public void onClick(View view){

    }
}


