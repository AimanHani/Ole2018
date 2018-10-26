package com.example.ole.oleandroid.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.TeamItems;

import java.util.ArrayList;

public class TeamAdapter extends ArrayAdapter<TeamItems> {
    public TeamAdapter(Context context, ArrayList<TeamItems> countryList){
        super(context,0,countryList);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }
    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext( )).inflate(
                    R.layout.team_spinner_row, parent, false
            );
        }
        ImageView imageViewFlag = convertView.findViewById(R.id.image_team_flag);
        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        TeamItems currentItem = getItem(position);
        if (currentItem != null) {
            imageViewFlag.setImageResource(currentItem.getmTeamImage( ));
            textViewName.setText(currentItem.getmTeamName( ));

        }
        return convertView;
    }
}
