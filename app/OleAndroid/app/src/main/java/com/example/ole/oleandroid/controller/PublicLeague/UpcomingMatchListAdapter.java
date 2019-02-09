package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.TeamItemDAO;
import com.example.ole.oleandroid.model.Match;
import com.example.ole.oleandroid.model.TeamItems;

import java.util.ArrayList;
import java.util.HashMap;

public class UpcomingMatchListAdapter extends BaseAdapter{
    private Context context; //context
    private ArrayList<Match> matchList;//data source of the list adapter
    private ViewHolder viewHolder;
    ImageView team1_photo, team2_photo;
    TextView team1_name, team2_name, match_date, team1_scoreinput, team2_scoreinput;

    private static class ViewHolder {
        TextView team1_name, team2_name, match_date, team1_scoreinput, team2_scoreinput;
        ImageView team1_photo, team2_photo;
    }

    public UpcomingMatchListAdapter(Context context, ArrayList<Match> matchList) {
        this.context = context;
        this.matchList = matchList;
    }

    @Override
    public int getCount() {
        return matchList.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return matchList.get(position); //returns list item at the specified position
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
                    inflate(R.layout.activity_upcoming_matches, parent, false);
        }

        /**
         * This will tell initialize the textview element in past matches main
         */

        //TextView team1_name, team2_name, match_date, team1_score, team2_score;
        //ImageView team1_photo, team2_photo;
        viewHolder.team1_name = convertView.findViewById(R.id.team1_name);
        viewHolder.team2_name = convertView.findViewById(R.id.team2_name);
        viewHolder.team1_scoreinput = convertView.findViewById(R.id.team1_scoreinput);
        viewHolder.team2_scoreinput = convertView.findViewById(R.id.team2_scoreinput);
        viewHolder.match_date = convertView.findViewById(R.id.match_date);
        viewHolder.team1_photo = convertView.findViewById(R.id.team1_photo);
        viewHolder.team2_photo = convertView.findViewById(R.id.team2_photo);

        HashMap<String, TeamItems> teamItemsList = TeamItemDAO.teamItemsList;
        //System.out.println(viewHolder.team1_name.getText().toString());
        viewHolder.team1_name.setText(matchList.get(position).getTeam1());
        viewHolder.team1_scoreinput.setText("__");
        viewHolder.team1_photo.setImageResource(teamItemsList.get(matchList.get(position).getTeam1()).getmTeamImage());

        viewHolder.team2_name.setText(matchList.get(position).getTeam2());
        viewHolder.team1_scoreinput.setText("__");
        viewHolder.team2_photo.setImageResource(teamItemsList.get(matchList.get(position).getTeam2()).getmTeamImage());

        viewHolder.match_date.setText(matchList.get(position).getMatchDate()+" "+matchList.get(position).getMatchTime());
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
