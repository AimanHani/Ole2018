package com.example.ole.oleandroid.controller.Leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.TeamCountryItemDAO;
import com.example.ole.oleandroid.model.CountryItem;
import com.example.ole.oleandroid.model.PublicLeagueProfile;

import java.util.ArrayList;
import java.util.HashMap;

public class LeaderboardPublicAdapter extends BaseAdapter{
    private Context context; //context
    private ArrayList<PublicLeagueProfile> pubLeagList;//data source of the list adapter
    private ViewHolder viewHolder;
    HashMap<String, CountryItem> countryItemsList = TeamCountryItemDAO.countryItemsList;

    private static class ViewHolder {
        TextView userposition,username,score;
        ImageView userProfilePicture, countryImage;
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
                    inflate(R.layout.leaderboard_user, parent, false);
        }

        /**
         * This will tell initialize the textview element in past matches main
         */
        viewHolder.userposition = convertView.findViewById(R.id.userposition);
        viewHolder.username = convertView.findViewById(R.id.username);
        viewHolder.score = convertView.findViewById(R.id.score);
        viewHolder.userProfilePicture = convertView.findViewById(R.id.userProfilePicture);
        viewHolder.countryImage = convertView.findViewById(R.id.countryImage);

        viewHolder.userposition.setText("#"+ pubLeagList.get(position).getRank()+"");
        viewHolder.username.setText(pubLeagList.get(position).getUsername());
        viewHolder.score.setText(pubLeagList.get(position).getTotalPoints()+" pts");
        viewHolder.countryImage.setImageResource(countryItemsList.get(pubLeagList.get(position).getCountry()).getmFlagImage());

        return convertView;// returns the view for the current row
    }


}
