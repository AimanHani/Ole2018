package com.example.ole.oleandroid.controller;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.ole.oleandroid.R;

import java.util.HashMap;
import java.util.List;

public class LeaderboardExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<Object>> listHashMap;

    public LeaderboardExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<Object>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int j) {
        return listHashMap.get(listDataHeader.get(i)).get(j);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int j) {
        return j;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String)getGroup(i);
        if(view == null) {
            LayoutInflater inflater =(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.leaderboard_privatelistgroup,null);
        }
        TextView faqListHeader = (TextView)view.findViewById(R.id.lbListGrp);
        faqListHeader.setTypeface(null, Typeface.BOLD);
        faqListHeader.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int j, boolean b, View view, ViewGroup viewGroup) {
        /*final String childText = (String) getChild(i,j);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.leaderboard_privatelistitem,null  );
        }
        TextView userPosition = (TextView)view.findViewById(R.id.userPosition);
        userPosition.setText(childText);*/

        // this part I am not sure yet tbc
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
