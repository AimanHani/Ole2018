package com.example.ole.oleandroid.controller.PublicLeague;

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

public class SpecialSpinnerAdapter extends ArrayAdapter<String> {
    public SpecialSpinnerAdapter(Context context, ArrayList<String> specialNames) {
        super(context, 0, specialNames);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getDropDownView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.specials_spinner_row, parent, false
            );
        }
        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        String currentItem = getItem(position);
        if (currentItem != null) {
            textViewName.setText(currentItem);
        }
        return convertView;
    }
}
