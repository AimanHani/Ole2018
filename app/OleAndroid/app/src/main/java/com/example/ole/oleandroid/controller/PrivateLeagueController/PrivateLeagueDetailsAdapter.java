package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.Leaderboard.LeaderboardPublicAdapter;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.PrivateMembers;

import java.util.ArrayList;

public class PrivateLeagueDetailsAdapter extends BaseAdapter {
    Button predictButton;
    EditText privatePrizeInput, leagueNameInput, pldusername;
    TextView creator;
    private Context context; //context
    private ArrayList<PrivateMembers> memberslist;//data source of the list adapter
    ArrayList<PrivateLeague> leaguelist;

    private Activity view;
    FloatingActionButton createPrivateLeagueBtn;
    Button button;
    String leaguid;
    private ViewHolder viewHolder;

    private static class ViewHolder {
        TextView pldusername, mempoints, country;
    }

    public PrivateLeagueDetailsAdapter(Context context, ArrayList<PrivateMembers> memberslist) {
        this.context = context;
        this.memberslist = memberslist;
    }

    @Override
    public int getCount() {
        return memberslist.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return memberslist.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_private_league_details_adapter, parent, false);
        }
        viewHolder.pldusername = convertView.findViewById(R.id.pldusername);
        viewHolder.country = convertView.findViewById(R.id.country);
        viewHolder.mempoints = convertView.findViewById(R.id.mempoints);

        viewHolder.pldusername.setText(memberslist.get(position).getUsername()+"");
        viewHolder.country.setText(memberslist.get(position).getCountry()+"");
        viewHolder.mempoints.setText(memberslist.get(position).getPoints()+" pts");

        return convertView;// returns the view for the current row
    }


}
