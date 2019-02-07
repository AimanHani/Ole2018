package com.example.ole.oleandroid.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.PublicLeague;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PublicLeagueListAdapter extends BaseAdapter implements View.OnClickListener{
    private Context context; //context
    private ArrayList<PublicLeague> leaguelist;//data source of the list adapter
    private ViewHolder viewHolder;

    private static class ViewHolder {
        TextView league;
        Button joinleaguebtn;
    }

    //public constructor
    // passes in a PublicLeague Arraylist so will be able to retrieve the logId as well and not just the name
    public PublicLeagueListAdapter(Context context, ArrayList<PublicLeague> leaguelist) {
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
            viewHolder = new PublicLeagueListAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_public_league_list, parent, false);
        }

        /**
         * This will tell initialize the textview element in publicleaguelistlayout
         */
        viewHolder.league = convertView.findViewById(R.id.leaguename);
        viewHolder.joinleaguebtn = convertView.findViewById(R.id.joinleaguebtn);
        //this will get each point from the arraylist
        viewHolder.league.setText(leaguelist.get(position).getLeagueName());
        viewHolder.joinleaguebtn.setTag(new ArrayList<>(Arrays.asList(position+"", leaguelist.get(position).getUserJoin().toString()))); //label the first item on the list
        //viewHolder.joinleaguebtn.setTag(2, leaguelist.get(position).getLogId());
        if (leaguelist.get(position).getUserJoin()){
            viewHolder.joinleaguebtn.setText("View");
        }
        //viewHolder.joinleaguebtn.setTag(2, leaguelist.get(position).getUserJoin().toString());
        viewHolder.joinleaguebtn.setOnClickListener(this);

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

    public void onClick(View view){
        ArrayList<String> tags = (ArrayList<String>) view.getTag();
        int position = Integer.parseInt(tags.get(0));
        Boolean userJoin = Boolean.parseBoolean(tags.get(1));

        if (view.getId() == R.id.joinleaguebtn && !userJoin){
            Intent intent = new Intent(context, Leagues.class);
            Bundle bundle = new Bundle();
            bundle.putInt("logId", leaguelist.get(position).getLogId());

//            int leagueId = leaguelist.get(position).getLeagueId();
//            String username = UserDAO.getLoginUser().getUsername();
            PublicLeagueDAO.joinPublicLeague(leaguelist.get(position).getLeagueId(), UserDAO.getLoginUser().getUsername());

            intent.putExtras(bundle);
            context.startActivity(intent);
        } else if (view.getId() == R.id.joinleaguebtn && userJoin){


        }
//        switch(view.getId()){
//            case R.id.joinleaguebtn && userJoin:
//                Intent intent = new Intent(context, Leagues.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("logId", (Integer) leaguelist.get(position).getLogId());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//
//        }
    }
}
