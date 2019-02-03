package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ole.oleandroid.model.Teams;

import java.util.ArrayList;

import com.example.ole.oleandroid.R;

public class TeamListPrivateAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Integer> pointsList; //data source of the list adapter
    private ArrayList<String> teamNameList;
    private ArrayList<Teams> teamList;
    private TeamListPrivateAdapter.ViewHolder viewHolder;
    private Button confirmspecialsbtn;
    private CheckBox checkPrivateTeam;

    private static class ViewHolder {
        TextView teamid;
        TextView itemname;
    }

    //uses list of specials Object so can get both points and name
    public TeamListPrivateAdapter(Context context, ArrayList<Teams> teamList) {
        this.context = context;
        this.teamList = teamList;
    }

    //public constructor
    public TeamListPrivateAdapter(Context context, ArrayList<Integer> pointsList, ArrayList<String> teamNameList, ArrayList<Teams> teamList) {
        this.context = context;
        this.pointsList = pointsList;
        this.teamNameList = teamNameList;
        this.teamList = teamList;
    }

    @Override
    public int getCount() {
        return teamList.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return teamList.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            viewHolder = new TeamListPrivateAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_team_list_private_adapter, parent, false);
        }

        /**
         * This will tell initialize the textview element in speciallistlayout
         */
        viewHolder.teamid = convertView.findViewById(R.id.teamid);
        viewHolder.itemname = convertView.findViewById(R.id.itemname);

        //this will get each point from the arraylist
        viewHolder.teamid.setText(teamList.get(position).getTeamId()+"");
        viewHolder.itemname.setText(teamList.get(position).getTeamName());




        return convertView;
    }
}


