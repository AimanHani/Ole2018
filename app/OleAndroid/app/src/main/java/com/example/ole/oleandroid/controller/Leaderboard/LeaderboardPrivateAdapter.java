package com.example.ole.oleandroid.controller.Leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.ScoreBoardDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.model.PrivateLeagueProfile;
import com.example.ole.oleandroid.model.PublicLeague;

import java.util.ArrayList;
import java.util.HashMap;

public class LeaderboardPrivateAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<PrivateLeagueProfile> privLeagList;//data source of the list adapter
    private ViewHolder viewHolder;

    private static class ViewHolder {
        TextView userposition, username, score;
    }

    //public constructor
    // passes in a PublicLeague Arraylist so will be able to retrieve the logId as well and not just the name
    public LeaderboardPrivateAdapter(Context context, ArrayList<PrivateLeagueProfile> privLeagList) {
        this.context = context;
        this.privLeagList = privLeagList;
    }

    @Override
    public int getCount() {
        System.out.println(privLeagList.size());
        return privLeagList.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return privLeagList.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        System.out.println("put");
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.leaderboard_publiclist, parent, false);
        }

        /**
         * This will tell initialize the textview element in past matches main
         */

        viewHolder.userposition = convertView.findViewById(R.id.userposition);
        viewHolder.username = convertView.findViewById(R.id.username);
        viewHolder.score = convertView.findViewById(R.id.score);

        viewHolder.userposition.setText(privLeagList.get(position).getRank()+"");
        viewHolder.username.setText(privLeagList.get(position).getUsername());
        viewHolder.score.setText(privLeagList.get(position).getTotalPoints()+"");

        return convertView;// returns the view for the current row
    }


}
