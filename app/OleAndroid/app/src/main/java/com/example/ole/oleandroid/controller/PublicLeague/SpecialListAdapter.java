package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    private Button confirmspecialsbtn;

    private static class ViewHolder {
        TextView points;
        TextView itemname;

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
    public View getView(int position, View convertView, ViewGroup parent) {
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

        //this will get each point from the arraylist
        viewHolder.points.setText(specialsList.get(position).getPoints()+"");
        viewHolder.itemname.setText(specialsList.get(position).getDescription());

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


