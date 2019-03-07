package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.ole.oleandroid.R;


import com.example.ole.oleandroid.model.PublicMembers;

import java.util.ArrayList;

public class PublicLeagueDetailsAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<PublicMembers> memberslist;//data source of the list adapter
    private PublicLeagueDetailsAdapter.ViewHolder viewHolder;

    private static class ViewHolder {
        TextView pldusername;
    }

    public PublicLeagueDetailsAdapter(Context context, ArrayList<PublicMembers> memberslist) {
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
            viewHolder = new PublicLeagueDetailsAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_public_league_details_adapter, parent, false);
        }
        viewHolder.pldusername = convertView.findViewById(R.id.pldusername);
        viewHolder.pldusername.setText(memberslist.get(position).getUsername()+"");

        return convertView;// returns the view for the current row
    }


}
