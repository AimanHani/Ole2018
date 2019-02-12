package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.Specials;

import java.util.ArrayList;

public class SpecialListAdapter extends BaseAdapter{
    private Context context; //context
    private ArrayList<Integer> pointsList; //data source of the list adapter
    private ArrayList<String> specialNameList;
    private ArrayList<Specials> specialsList;
    private ViewHolder viewHolder;
    private Button doubleitbtn;

    private static class ViewHolder {
        TextView points;
        TextView itemname;
        Button doubleitbtn;
    }

    //uses list of specials Object so can get both points and name
    public SpecialListAdapter(Context context, ArrayList<Specials> specialsList) {
        this.context = context;
        this.specialsList = specialsList;
    }

    //public constructor
    public SpecialListAdapter(Context context, ArrayList<Integer> pointsList, ArrayList<String> specialNameList, ArrayList<Specials> specialsList) {
        this.context = context;
        this.pointsList = pointsList;
        this.specialNameList = specialNameList;
        this.specialsList = specialsList;
    }

    @Override
    public int getCount() {
        return specialsList.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return specialsList.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            viewHolder = new SpecialListAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_speciallist, parent, false);
        }

        /**
         * This will tell initialize the textview element in speciallistlayout
         */
        viewHolder.points = convertView.findViewById(R.id.points);
        viewHolder.itemname = convertView.findViewById(R.id.itemname);
        viewHolder.doubleitbtn = convertView.findViewById(R.id.doubleitbtn);

        //this will get each point from the arraylist
        viewHolder.points.setText(specialsList.get(position).getPoints()+"");
        viewHolder.itemname.setText(specialsList.get(position).getDescription());

//        viewHolder.doubleitbtn.setOnClickListener( new View.OnClickListener() {
//            public void onClick(View v) {
//                int pts = Integer.parseInt(viewHolder.points.getText().toString());
//                System.out.println(viewHolder.points.getText().toString()+" "+pts);
//                viewHolder.points.setText(pts*2+"");
//            }
//        });

        // returns the view for the current row
        return convertView;
    }


}


