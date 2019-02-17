package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.Signup;
import com.example.ole.oleandroid.controller.TeamAdapter;
import com.example.ole.oleandroid.model.CountryItem;
import com.example.ole.oleandroid.model.Specials;

import java.util.ArrayList;

public class SpecialListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Integer> pointsList; //data source of the list adapter
    private ArrayList<String> specialNameList;
    private ArrayList<Specials> specialsList;
    private ViewHolder viewHolder;
    private Button doubleitbtn;
    private Button undoubleIt;
    private Spinner spinner;

    private static class ViewHolder {
        TextView points;
        TextView itemname;
        Button doubleitbtn;
        Button undoubleIt;
        Spinner spinner;
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
    public Specials getItem(int position) {
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

            /**
             * This will tell initialize the textview element in speciallistlayout
             */
            viewHolder.points = convertView.findViewById(R.id.points);
            viewHolder.itemname = convertView.findViewById(R.id.itemname);
            viewHolder.doubleitbtn = convertView.findViewById(R.id.doubleitbtn);
            viewHolder.undoubleIt = convertView.findViewById(R.id.unDoubleIt);
            viewHolder.undoubleIt = convertView.findViewById(R.id.unDoubleIt);
            viewHolder.spinner = convertView.findViewById(R.id.spinner);

            final Specials s = getItem(position);
            viewHolder.points.setText(s.getPoints() + "");
            viewHolder.itemname.setText(s.getDescription());
            viewHolder.doubleitbtn.setTag(s);
            viewHolder.undoubleIt.setTag(s);
            viewHolder.spinner.setTag(s);

            ArrayList<String> players = players();
            SpecialSpinnerAdapter adapter2 = new SpecialSpinnerAdapter(context, players);
            viewHolder.spinner.setAdapter(adapter2);


            convertView.setTag(viewHolder);

            final View finalConvertView = convertView;

            viewHolder.doubleitbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Button b = (Button) v;
                    Specials s = (Specials) b.getTag();
                    s.setDoubleIt(true);

                    int sPoints = s.getPoints();

                    System.out.println(sPoints);
                    ViewHolder newViewholder = (ViewHolder) finalConvertView.getTag();
                    newViewholder.points.setText(sPoints * 2 + "");
                    newViewholder.undoubleIt.setVisibility(View.VISIBLE);
                    newViewholder.doubleitbtn.setVisibility(View.GONE);
                    //s.setDoubleIt(true);
                    setSpecialsListDoubleIt(s, true);

                }
            });

            viewHolder.undoubleIt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Button b = (Button) v;
                    Specials s = (Specials) b.getTag();
                    s.setDoubleIt(true);

                    int sPoints = s.getPoints();

                    System.out.println(sPoints);
                    ViewHolder newViewholder = (ViewHolder) finalConvertView.getTag();
                    newViewholder.undoubleIt.setVisibility(View.GONE);
                    newViewholder.doubleitbtn.setVisibility(View.VISIBLE);
                    newViewholder.points.setText(sPoints + "");
                    //s.setDoubleIt(false);
                    setSpecialsListDoubleIt(s, false);
//                viewHolder.itemname.setText(sPoints*2+"");

                }
            });


            viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String clickedItem = (String) parent.getItemAtPosition(position);
                    setSpecialsListPrediction(s, clickedItem);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    setSpecialsListPrediction(s, "");
                }
            });
        }


        // returns the view for the current row
        return convertView;
    }

    public ArrayList<Specials> getUpdatedSpecialList() {
        return this.specialsList;
    }

    public int getIdFromSpecialsArray(Specials s) {
        int id = s.getSpecialsID();

        for (int i = 0; i < specialsList.size(); i++) {
            if (specialsList.get(i).getSpecialsID() == id) {
                return i;
            }
        }

        return 0;
    }

    public void setSpecialsListDoubleIt(Specials s, Boolean b) {
        int id = s.getSpecialsID();

        for (int i = 0; i < specialsList.size(); i++) {
            if (specialsList.get(i).getSpecialsID() == id) {
                specialsList.get(i).setDoubleIt(b);
            }
        }
    }

    public void setSpecialsListPrediction(Specials s, String prediction) {
        int id = s.getSpecialsID();

        for (int i = 0; i < specialsList.size(); i++) {
            if (specialsList.get(i).getSpecialsID() == id) {
                specialsList.get(i).setPrediction(prediction);
            }
        }
    }

    public static ArrayList<String> players() {
        ArrayList<String> players = new ArrayList<>();

        players.add("Lionel Messi");
        players.add("Sergio Ramos");
        players.add("Marco Reus");
        players.add("Zlatan Ibrahimovic");
        players.add("Andrea Pirlo");
        players.add("Lionel Messi");
        players.add("Lionel Messi");

        return players;
    }


}


