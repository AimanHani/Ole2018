package com.example.ole.oleandroid.pageController;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.ExpandableListAdapter;
import com.example.ole.oleandroid.controller.SideMenuBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQPublic extends SideMenuBar {
    private ExpandableListView listView;
    ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    Button askOle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_faqpublic, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        listView = (ExpandableListView) findViewById(R.id.output);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);

        askOle = findViewById(R.id.askOleButton);

        askOle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQPublic.this, AskOle.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });



    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Can I drop out of a Public League?");
        listDataHeader.add("How do I see my past predictions?");
        listDataHeader.add("Am I allowed to see what other people predict?");

        List<String> QuesOne = new ArrayList<>();
        QuesOne.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        List<String> QuesTwo = new ArrayList<>();
        QuesTwo.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Diam vel quam elementum pulvinar. At tellus at urna condimentum mattis pellentesque id. Enim facilisis gravida neque convallis a. Eget magna fermentum iaculis eu non diam phasellus.");

        List<String> QuesThree = new ArrayList<>();
        QuesThree.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Diam vel quam elementum pulvinar. At tellus at urna condimentum mattis pellentesque id. Enim facilisis gravida neque convallis a. Eget magna fermentum iaculis eu non diam phasellus.");

        listHash.put(listDataHeader.get(0), QuesOne); // Header, Child data
        listHash.put(listDataHeader.get(1), QuesTwo);
        listHash.put(listDataHeader.get(2), QuesThree);
    }
}

