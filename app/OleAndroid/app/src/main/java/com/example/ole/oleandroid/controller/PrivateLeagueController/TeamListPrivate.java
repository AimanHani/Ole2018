package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ole.oleandroid.controller.Login;
import com.example.ole.oleandroid.controller.PublicLeague.SpecialList;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.controller.Signup;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.Teams;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.graphics.Color.TRANSPARENT;

public class TeamListPrivate extends SideMenuBar {
    TeamListPrivateAdapter teamListPrivateAdapter;
    ListView teamListView;
    Button confirmteambtn;
    String teams = "";
    ArrayList<Teams> teamsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.teamlistprivate, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        teamListView = findViewById(R.id.teamListView);
        confirmteambtn = findViewById(R.id.confirmteambtn);

        Bundle bundle = getIntent().getExtras();

        String url = DBConnection.getTeams();
        System.out.println("Getting teams list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            if (response != null) {
                JSONObject result = new JSONObject(response);
                JSONArray publicLeagues = result.getJSONArray("results");
                System.out.println(publicLeagues.length());
                if (publicLeagues.length() > 0) {
                    for (int i = 0; i < publicLeagues.length(); i++) {
                        JSONObject teamObject = publicLeagues.getJSONObject(i);
                        Teams team = new Teams(
                                teamObject.getInt("teamId"),
                                teamObject.getString("name")
                        );
                        teamsList.add(team);
                    }

                } else {
                    //loadSamePage();
                }
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

        teamListPrivateAdapter = new TeamListPrivateAdapter(TeamListPrivate.this, teamsList);
        teamListView.setAdapter(teamListPrivateAdapter);
        confirmteambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*                final Dialog dialog = new Dialog(TeamListPrivate.this);
                dialog.setContentView(R.layout.privateleague_create_success);
                TextView msg = dialog.findViewById(R.id.confirmMessage);
                msg.setText("Successfully created Private League!" );
                TextView cancel = dialog.findViewById(R.id.cancel);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                TextView confirmbtn = dialog.findViewById(R.id.confirmbtn);
                confirmbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });*/

                final Dialog dialog = new Dialog(TeamListPrivate.this);
                dialog.setContentView(R.layout.privateleague_create_success);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT)); //to set bg transparent
                dialog.show();

                TextView confirmbtn = (TextView) dialog.findViewById(R.id.confirmbtn);

                confirmbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                try {
                    teams = "";
                    for (Teams t : teamsList) {
                        if (t.getChecked()) {
                            teams += t.getTeamId() + ",";
                        }
                    }
                    //System.out.println("HI"+teams.substring(0, teams.length() - 1));
                    teams = teams.substring(0, teams.length() - 1);

                    if (!teams.equals("")) {

                        Intent intent = getIntent();
                        User loginUser = UserDAO.getLoginUser();

                        //Bundle extras = getIntent().getExtras();
                        System.out.println("Creating Private League");
                        //final Dialog load = loadingDialog();
                        //        Toast.makeText(TeamListPrivate.this,
                        //                "Creating private league", Toast.LENGTH_LONG).show();

                        final String[] status = {"error"};
                        JSONObject json = new JSONObject();
                        try {
                            Bundle extras = getIntent().getExtras();
                            if (extras != null) {
                                json.put("method", "insertNew");
                                json.put("username", loginUser.getUsername());
                                json.put("password", extras.getString("password"));
                                json.put("prize", extras.getString("prize"));
                                json.put("leagueName", extras.getString("leaguename"));
                                json.put("tournamentId", extras.getString("leagueid"));
                                json.put("pointsAllocated", extras.getString("pointsAllocated"));
                                json.put("startDate", extras.getString("startdate"));
                                json.put("endDate", extras.getString("enddate"));
                                json.put("specials", extras.getString("specials"));
                                json.put("teams", teams);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String url = DBConnection.privateLeagueUrl();

                        PostHttp connection = new PostHttp();
                        String response = null;
                        //System.out.println("HAHAHAHHA" + json.toString());

                        response = connection.post(url, json.toString());
                        JSONObject result = new JSONObject(response);
                        status[0] = result.getString("status");

                        //                Toast.makeText(TeamListPrivate.this,
                        //                        "Creating private league", Toast.LENGTH_LONG).show();

                        System.out.println(response);


                        //load.dismiss();
                        intent = new Intent(TeamListPrivate.this, PrivateLeagueMain.class);
                        intent.putExtra("FROM_ACTIVITY", "created");
                        startActivity(intent);

                    } else {
                        Toast.makeText(TeamListPrivate.this,
                                "Please select at least 1", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    //Toast.makeText(TeamListPrivate.this,
                    //      "Please select at least 1", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    /*public Dialog loadingDialog() {
        System.out.println("loading pop");
        Dialog dialog2 = new Dialog(TeamListPrivate.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.loading_popup);
        TextView text = dialog2.findViewById(R.id.textStatus);
        text.setText("Creating new Private League");
        dialog2.show();
        return dialog2;
    }*/

}
