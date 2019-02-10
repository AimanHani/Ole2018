package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.ScoreBoardDAO;
import com.example.ole.oleandroid.model.PublicLeagueProfile;
import com.example.ole.oleandroid.model.PublicLeague;

import java.util.ArrayList;
import java.util.HashMap;

public class LeaderboardPublicAdapter extends BaseAdapter{
    private Context context; //context
    private ArrayList<PublicLeagueProfile> pubLeagList;//data source of the list adapter
    private ViewHolder viewHolder;

    private static class ViewHolder {
        TextView userposition,username,score;
    }

    //public constructor
    // passes in a PublicLeague Arraylist so will be able to retrieve the logId as well and not just the name
    public LeaderboardPublicAdapter(Context context, ArrayList<PublicLeagueProfile> pubLeagList) {
        this.context = context;
        this.pubLeagList = pubLeagList;
    }

    @Override
    public int getCount() {
        return pubLeagList.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return pubLeagList.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.leaderboard_publiclist, parent, false);
        }

        /**
         * This will tell initialize the textview element in past matches main
         */

        //TextView team1_name, team2_name, match_date, team1_score, team2_score;
        //ImageView team1_photo, team2_photo;
        viewHolder.userposition = convertView.findViewById(R.id.userposition);
        viewHolder.username = convertView.findViewById(R.id.username);
        viewHolder.score = convertView.findViewById(R.id.score);

        viewHolder.userposition.setText("1");
        viewHolder.username.setText(pubLeagList.get(position).getUsername());
        viewHolder.score.setText(pubLeagList.get(position).getTotalPoints());
        /*
        // get current item to be displayed
        Item currentItem = (Item) getItem(position);

        // get the TextView for item name and item description
        TextView points = (TextView)
                convertView.findViewById(R.id.text_view_item_name);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getItemName());
        textViewItemDescription.setText(currentItem.getItemDescription());
        */

        return convertView;// returns the view for the current row
    }


}
