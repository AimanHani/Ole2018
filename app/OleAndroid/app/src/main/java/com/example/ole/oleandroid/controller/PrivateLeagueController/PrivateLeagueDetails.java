package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.LoginDAO;
import com.example.ole.oleandroid.controller.DAO.PrivateLeagueDAO;
import com.example.ole.oleandroid.controller.DAO.PrivateMembersDAO;
import com.example.ole.oleandroid.controller.DAO.ScoreBoardDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.controller.HomeLeague;
import com.example.ole.oleandroid.controller.Leaderboard.LeaderboardPrivateAdapter;
import com.example.ole.oleandroid.controller.Login;
import com.example.ole.oleandroid.controller.MatchesTabs;
import com.example.ole.oleandroid.controller.PublicLeague.PublicLeagueDetails;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.Member;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.PrivateLeagueProfile;
import com.example.ole.oleandroid.model.PrivateMembers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PrivateLeagueDetails extends SideMenuBar implements View.OnClickListener {
    View view;
    private ArrayList<Member> memberslist;//data source of the list adapter
    ArrayList<PrivateLeague> leaguelist;
    Button button;
    PrivateLeagueDetailsAdapter privateLeagueDetailsAdapter;
    ListView privateLeagueListView;
    TextView privatePrizeInput, leagueNameInput, creator, totalNoMembers;
    PrivateLeague privateleague = null;
    int logid = 0;
    LinearLayout blackoutimage;
    FloatingActionButton main, predictSpecial, predictMatch, shareLeague, deleteLeague;
    Animation FoodFabOpen, FoodFabClose, FabRClockwise, FabRAntiClockwise, Fadein, Fadeout;
    TextView specialtext, matchtext, mainview, sharetext, deleteText;
    boolean isOpen = false;
    int numMembers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Intent intent = getIntent();
        final String leagueid = intent.getStringExtra("leagueid");

        View contentView = inflater.inflate(R.layout.activity_private_league_details, null, false);
        super.mDrawerlayout.addView(contentView, 0);
        System.out.println("Retrieving... " + leagueid);
        final String[] status = {"error"};

        PrivateLeagueDAO.clearAllPrivateLeague();
        privateleague = PrivateLeagueDAO.getPrivateLeague(leagueid);
        ArrayList<PrivateLeagueProfile> privateLeagueProfileList = ScoreBoardDAO.getOnePrivateLeagueProfiles(Integer.parseInt(leagueid));
        logid = PrivateLeagueDAO.getAdminLogId(leagueid);


        privatePrizeInput = findViewById(R.id.privatePrizeInput);
        leagueNameInput = findViewById(R.id.leagueNameInput);
        creator = findViewById(R.id.creator);
//        privatepoints = findViewById(R.id.privatepoints);
        totalNoMembers = findViewById(R.id.totalNoMembers);

        privatePrizeInput.setText(privateleague.getPrize());
        leagueNameInput.setText(privateleague.getLeagueName());
        creator.setText(privateleague.getUsername());
//        privatepoints.setText(privateleague.getPointsAllocated() + "");
        totalNoMembers.setText(privateLeagueProfileList.size() + "");

        privateLeagueListView = findViewById(R.id.privateLeagueListView);
        LeaderboardPrivateAdapter leaderboardPrivateAdapter = new LeaderboardPrivateAdapter(PrivateLeagueDetails.this, privateLeagueProfileList);
        privateLeagueListView.setAdapter(leaderboardPrivateAdapter);


        // codes below is the plus button to make prediction and
        blackoutimage = findViewById(R.id.blackoutimage);
        main = findViewById(R.id.floatingActionButton);
        predictSpecial = findViewById(R.id.predictSpecial);
        predictMatch = findViewById(R.id.predictMatch);
        shareLeague = findViewById(R.id.shareLeague);
        deleteLeague = findViewById(R.id.deleteLeague);
        mainview = findViewById(R.id.mainview);

        // enable animations for FloatingActionButton
        FoodFabOpen = AnimationUtils.loadAnimation(this, R.anim.foodfabopen);
        FoodFabClose = AnimationUtils.loadAnimation(this, R.anim.foodfabclose);
        FabRClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise);
        FabRAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise);
        Fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        Fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        specialtext = findViewById(R.id.specialtext);
        matchtext = findViewById(R.id.matchtext);
        sharetext = findViewById(R.id.sharetext);
        deleteText = findViewById(R.id.deleteText);


        main.bringToFront();
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    closeBlackout();
                } else {
                    openBlackout();

                    predictMatch.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent(PrivateLeagueDetails.this, PrivateLeagueMatchesMain.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("logId", logid);
                            bundle.putInt("leagueId", Integer.parseInt(leagueid));
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    predictSpecial.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent(PrivateLeagueDetails.this, PrivateLeagueSpecialsList.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("logId", logid);
                            bundle.putInt("leagueId", Integer.parseInt(leagueid));
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });

                    shareLeague.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            try {
                                String message = "Join \"" + privateleague.getLeagueName() + "\" Private Competition on Olé Football. Friends. Fun. Password is: " + privateleague.getPassword();

                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                                sendIntent.setType("text/plain");
                                sendIntent.setPackage("com.whatsapp");
                                startActivity(sendIntent);

                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(getBaseContext(), "Whatsapp have not been installed.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    deleteLeague.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            //Toast.makeText(getBaseContext(), "Delete in Progress", Toast.LENGTH_LONG).show();
                            final Dialog load = loadingDialog();
                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            //boolean deleteStatus = PrivateLeagueDAO.deletePrivateLeague(leagueid);
                                            Intent intent = new Intent(PrivateLeagueDetails.this, PrivateLeagueMain.class);
                                            startActivity(intent);
                                        }
                                    }, 3000);
                        }
                    });

                    blackoutimage.setOnClickListener(this);
                }
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blackoutimage:
                closeBlackout();
                break;

            case R.id.specialtext:
                //Intent = new Intent(this.getActivity(), xxx.class);
                //startActivity();
                break;
            case R.id.predictMatch:
                //Intent intent = new Intent(PrivateLeagueDetails.this, HomeLeague.class);
                //startActivity(intent);
                break;
            case R.id.floatingActionButton:
                if (isOpen) {
                    closeBlackout();
                } else {
                    openBlackout();
                }
                break;
        }
    }

    public void closeBlackout() {
        blackoutimage.startAnimation(Fadeout);
        blackoutimage.setVisibility(View.GONE);
        predictSpecial.startAnimation(FoodFabClose);
        predictMatch.startAnimation(FoodFabClose);
        shareLeague.startAnimation(FoodFabClose);
        deleteLeague.startAnimation(FoodFabClose);
        main.startAnimation(FabRAntiClockwise);
        predictSpecial.setClickable(false);
        predictMatch.setClickable(false);
        shareLeague.setClickable(false);
        deleteLeague.setClickable(false);
        specialtext.setVisibility(View.GONE);
        matchtext.setVisibility(View.GONE);
        sharetext.setVisibility(View.GONE);
        deleteText.setVisibility(View.GONE);
        isOpen = false;
    }

    public void openBlackout() {
        // if user is not the creator of the priv league, hide the delete button
        System.out.println("creator: " + privateleague.getUsername() + " user: " + UserDAO.getLoginUser().getUsername());
        if (!privateleague.getUsername().equals(UserDAO.getLoginUser().getUsername())) {
            deleteText.setVisibility(View.GONE);
            deleteLeague.startAnimation(FoodFabClose);
        } else {
            deleteLeague.startAnimation(FoodFabOpen);
            deleteText.setVisibility(View.VISIBLE);
        }

        blackoutimage.setVisibility(View.VISIBLE);
        blackoutimage.startAnimation(Fadein);
        predictSpecial.startAnimation(FoodFabOpen);
        predictMatch.startAnimation(FoodFabOpen);
        shareLeague.startAnimation(FoodFabOpen);
        main.startAnimation(FabRClockwise);
        predictSpecial.setClickable(true);
        predictMatch.setClickable(true);
        shareLeague.setClickable(true);
        deleteLeague.setClickable(true);
        specialtext.setVisibility(View.VISIBLE);
        matchtext.setVisibility(View.VISIBLE);
        sharetext.setVisibility(View.VISIBLE);
        specialtext.bringToFront();
        matchtext.bringToFront();
        sharetext.bringToFront();
        deleteText.bringToFront();
        isOpen = true;
    }

    public Dialog loadingDialog() {
        System.out.println("loading pop");
        Dialog dialog = new Dialog(PrivateLeagueDetails.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_popup);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        TextView text = dialog.findViewById(R.id.textStatus);
        text.setText("Deleting");
        dialog.show();
        return dialog;
    }

}

