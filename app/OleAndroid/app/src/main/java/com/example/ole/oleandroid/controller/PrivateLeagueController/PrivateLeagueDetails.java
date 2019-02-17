package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.PrivateMembersDAO;
import com.example.ole.oleandroid.controller.PrivateLeagueTabActivity;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.PrivateMembers;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;


public class PrivateLeagueDetails extends BaseAdapter implements View.OnClickListener{
    //TextView leagueNameInput;
    Button predictButton;
    EditText privatePrizeInput, leagueNameInput, pldusername;
    TextView creator;
    private Context context; //context
    private ArrayList<PrivateMembers> memberslist;//data source of the list adapter
    ArrayList<PrivateLeague> leaguelist;
    private PrivateLeagueDetails.ViewHolder viewHolder;
    private Activity view;
    FloatingActionButton createPrivateLeagueBtn;
    Button button;
    String leaguid;


    private static class ViewHolder {
        public String pldusername;
        Button button;
    }

    //public constructor
    // passes in a PublicLeague Arraylist so will be able to retrieve the logId as well and not just the name
    public PrivateLeagueDetails(Context context, ArrayList<PrivateMembers> memberslist) {
        this.context = context;
        this.memberslist = memberslist;
        this.leaguelist = this.leaguelist;
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



        // inflate the layout for each list row



        if (convertView == null) {
            viewHolder = new PrivateLeagueDetails.ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_private_league_details, parent, false);


            /**
             * This will tell initialize the textview element in publicleaguelistlayout
             */

            String leagueId = PrivateMembersDAO.getId();

            System.out.println("Retrieving... " + leagueId);
            final String[] status = {"error"};

            JSONObject json = new JSONObject();
            try {
                json.put("method", "retrieveLeagueName");
                json.put("leagueId", leagueId);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String url = DBConnection.insertPrivateLeagueUrl();

            PostHttp connection = new PostHttp();
            String response = null;
            try {
                response = connection.post(url, json.toString());
                System.out.println(response);

                JSONObject result = new JSONObject(response);
                status[0] = result.getString("status");

                if (status[0].equals("success")) {
                    JSONObject league = result.getJSONObject("league");
                    int leagueID = league.getInt("LeagueId");
                    int tournamentId = league.getInt("tournamentId");
                    int pointsAllocated = league.getInt("pointsAllocated");
                    String leagueName = league.getString("leagueName");
                    String privateLeagueID = league.getString("privateLeagueID");
                    String prize = league.getString("prize");
                    String startDate = league.getString("startDate");
                    String endDate = league.getString("endDate");
                    int leagueKeyId = league.getInt("leagueKeyId");
                    String username = league.getString("userName");
                    String password = league.getString("password");

                    //System.out.println("HEY MISTA" + java.sql.Date.valueOf(endDate) + password);
                    PrivateLeague privateleague = null;
                    try {
                        privateleague = new PrivateLeague(leagueID, leagueName, prize, password, startDate, endDate, username, pointsAllocated, tournamentId, leagueKeyId);
                        //pl = new PrivateLeague(privateLeagueId, prize,java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), leagueKeyId, username);
                        //pl = new PrivateLeague(privateLeaugeId, leagueName,java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), leagueKeyId, username);
                        //(int privateLeaugeId, String prize, Date startDate, Date endDate, int leagueId, String username) {
                        //(int privateLeaugeId, String leagueName, String prize, Date startDate, Date endDate, int leagueId) {
                        PrivateLeague.setPrivateLeague(privateleague);
                    } catch (Exception e) {
                    }


                    predictButton = convertView.findViewById(R.id.predictButton);
                    privatePrizeInput = convertView.findViewById(R.id.privatePrizeInput);
                    pldusername = convertView.findViewById(R.id.pldusername);
                    leagueNameInput = convertView.findViewById(R.id.leagueNameInput);
                    creator = convertView.findViewById(R.id.creator);


                    if (privateleague != null) {
                        privatePrizeInput.setInputType(InputType.TYPE_NULL);
                        leagueNameInput.setInputType(InputType.TYPE_NULL);
                        creator.setInputType(InputType.TYPE_NULL);
                        pldusername.setInputType(InputType.TYPE_NULL);


                        privatePrizeInput.setText(privateleague.getPrize());
                        leagueNameInput.setText(privateleague.getLeagueName());
                        creator.setText(privateleague.getUsername());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(memberslist.size()>0 && memberslist.get(0).getLeagueId()!=0) {
                        pldusername = convertView.findViewById(R.id.pldusername);
                        button = convertView.findViewById(R.id.button);
                        pldusername.setText(memberslist.get(position).getUsername());
                        //viewHolder.joinleaguebtn.setOnClickListener(this);
                        convertView.setTag(viewHolder);

//                        viewHolder.joinleaguebtn.setOnClickListener(new View.OnClickListener() {
//                            public void onClick(View v) {
//                                Button cb = (Button) v;
//                                PrivateLeague s = (PrivateLeague) cb.getTag();
//                    /*Toast.makeText(context.getApplicationContext(),
//                            "Clicked on Checkbox: " + cb.getText() +
//                                    " is " + cb.isChecked(),
//                            Toast.LENGTH_LONG).show();*/
//                                loadNextPage(v, context, s.getLeagueId()+"");
//
//                            }
//                        });
                    }}else{
                    viewHolder = (PrivateLeagueDetails.ViewHolder)convertView.getTag();
                }

                PrivateMembers s = memberslist.get(position);
                pldusername.setText("" +  s.getUsername() + "");
//        viewHolder.checkPrivateTeam.setText(t.getTeamId());
                button.setTag(s);




        return convertView;// returns the view for the current row
    }


    public void onClick(View view){

    }




}

