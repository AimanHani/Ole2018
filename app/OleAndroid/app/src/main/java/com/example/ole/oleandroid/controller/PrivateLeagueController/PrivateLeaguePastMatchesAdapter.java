package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.TeamItemDAO;
import com.example.ole.oleandroid.controller.PublicLeague.PastMatchListAdapter;
import com.example.ole.oleandroid.model.Match;
import com.example.ole.oleandroid.model.TeamItems;

import java.util.ArrayList;
import java.util.HashMap;

public class PrivateLeaguePastMatchesAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Match> matchList;//data source of the list adapter
    private ViewHolder viewHolder;
    ImageView team1_photo, team2_photo;
    TextView team1_name, team2_name, match_date, team1_score, team2_score;

    private static class ViewHolder {
        TextView team1_name, team2_name, match_date, team1_score, team2_score;
        ImageView team1_photo, team2_photo;
    }

    //public constructor
    // passes in a PublicLeague Arraylist so will be able to retrieve the logId as well and not just the name
    public PrivateLeaguePastMatchesAdapter(Context context, ArrayList<Match> matchList) {
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
                    //inflate(R.layout.matches_with_predicted_score, parent, false);
                            inflate(R.layout.activity_private_league_past_matches_adapter, parent, false);
        }
        //TextView team1_name, team2_name, match_date, team1_score, team2_score;
        //ImageView team1_photo, team2_photo;
        viewHolder.team1_name = convertView.findViewById(R.id.team1_name);
        viewHolder.team2_name = convertView.findViewById(R.id.team2_name);
        viewHolder.team1_score = convertView.findViewById(R.id.team1_score);
        viewHolder.team2_score = convertView.findViewById(R.id.team2_score);
        viewHolder.match_date = convertView.findViewById(R.id.match_date);
        viewHolder.team1_photo = convertView.findViewById(R.id.team1_photo);
        viewHolder.team2_photo = convertView.findViewById(R.id.team2_photo);

        HashMap<String, TeamItems> teamItemsList = TeamItemDAO.teamItemsList;
        //System.out.println(viewHolder.team1_name.getText().toString());
        viewHolder.team1_name.setText(matchList.get(position).getTeam1());
        viewHolder.team1_score.setText(matchList.get(position).getTeam1Score()+"");
        viewHolder.team1_photo.setImageResource(teamItemsList.get(matchList.get(position).getTeam1()).getmTeamImage());

        viewHolder.team2_name.setText(matchList.get(position).getTeam2());
        viewHolder.team2_score.setText(matchList.get(position).getTeam2Score()+"");
        viewHolder.team2_photo.setImageResource(teamItemsList.get(matchList.get(position).getTeam2()).getmTeamImage());

        viewHolder.match_date.setText(matchList.get(position).getMatchDate()+" "+matchList.get(position).getMatchTime());
        return convertView;// returns the view for the current row
    }
}
