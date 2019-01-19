package com.example.ole.oleandroid.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ole.oleandroid.R;

import java.util.ArrayList;

public class PublicLeagueListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<String> leaguelist;//data source of the list adapter
    private ViewHolder viewHolder;

    private static class ViewHolder {
        TextView league;
    }

    //public constructor
    public PublicLeagueListAdapter(Context context, ArrayList<String> leaguelist) {
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
                    inflate(R.layout.publicleaguelistlayout, parent, false);
        }

        /**
         * This will tell initialize the textview element in publicleaguelistlayout
         */
        viewHolder.league = convertView.findViewById(R.id.leaguename);

        //this will get each point from the arraylist
        viewHolder.league.setText(leaguelist.get(position).toString());

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

        // returns the view for the current row
        return convertView;
    }
}
