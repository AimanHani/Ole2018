package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.PrivateLeagueDAO;
import com.example.ole.oleandroid.controller.DAO.PrivateMembersDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.PrivateMembers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PrivateMemberList extends SideMenuBar implements View.OnClickListener{

    PrivateLeagueDetails privateLeagueDetails;
    ArrayList<PrivateMembers> leaguelist;
    ListView privateLeagueListView;
    ArrayList<String> allLeagues = new ArrayList<>();
    //int leagueID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Intent intent = getIntent();
        String leagueid = intent.getStringExtra("leagueid");

        View contentView = inflater.inflate(R.layout.activity_private_member_list, null, false);
        super.mDrawerlayout.addView(contentView, 0);



        PrivateMembersDAO.setId(leagueid);


        leaguelist = new ArrayList<>();
        privateLeagueListView = findViewById(R.id.privateLeagueListView);
        PrivateLeagueDAO.clearAllPrivateLeague();
        String url = DBConnection.insertPrivateLeagueUrl()+"?method=retrieveMembers&leagueid="+intent.getStringExtra("leagueid");

        System.out.println("Getting private league list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray members = result.getJSONArray("results");

            if (members.length() > 0) {


                for (int i = 0; i < members.length(); i++) {
                    JSONObject membersObj = members.getJSONObject(i);

                    PrivateMembers privateMembers = new PrivateMembers(

                            membersObj.getInt("logid"),
                            membersObj.getString("username"),
                            membersObj.getInt("leagueid"),
                            membersObj.getInt("points")
                    );
                    PrivateMembersDAO.addPrivateMembers(privateMembers);
                }
                privateLeagueDetails = new PrivateLeagueDetails(PrivateMemberList.this, PrivateMembersDAO.getAllPrivateMembers());
                privateLeagueListView.setAdapter(privateLeagueDetails);

            } else {

            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

    }
    @Override
    public void onClick(View v) {

    }
}
