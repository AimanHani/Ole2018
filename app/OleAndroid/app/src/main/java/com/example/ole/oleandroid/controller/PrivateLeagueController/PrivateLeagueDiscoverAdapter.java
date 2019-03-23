package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.PrivateLeagueDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.controller.FAQ.AskOle;
import com.example.ole.oleandroid.controller.FAQ.FAQ;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.User;

import org.json.JSONObject;

import java.util.ArrayList;

import static android.graphics.Color.*;

public class PrivateLeagueDiscoverAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context; //context
    private ArrayList<PrivateLeague> leaguelist;//data source of the list adapter
    private PrivateLeagueDiscoverAdapter.ViewHolder viewHolder;
    private Activity view;
    FloatingActionButton createPrivateLeagueBtn;
    Button joinprivateleaguebtn;
    String leaguid;
    EditText passwordInput;
    //ImageView close;

    private static class ViewHolder {
        TextView league;
        Button joinprivateleaguebtn;
    }

    public PrivateLeagueDiscoverAdapter(Context context, ArrayList<PrivateLeague> leaguelist) {
        this.context = context;
        this.leaguelist = leaguelist;
    }

    @Override
    public int getCount() {
        return leaguelist.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return leaguelist.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new PrivateLeagueDiscoverAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_private_league_discover_adapter, parent, false);

            if(leaguelist.size()>0 && leaguelist.get(0).getLeagueName()!=null) {
                viewHolder.league = convertView.findViewById(R.id.leaguename);
                viewHolder.joinprivateleaguebtn = convertView.findViewById(R.id.joinprivateleaguebtn);
                viewHolder.league.setText(leaguelist.get(position).getLeagueName());
                viewHolder.joinprivateleaguebtn.setOnClickListener(this);
                convertView.setTag(viewHolder);

                viewHolder.joinprivateleaguebtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Button cb = (Button) v;
                        final PrivateLeague s = (PrivateLeague) cb.getTag();

                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.activity_private_league_input_password);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT)); //to set bg transparent
                        dialog.show();

                        TextView join = (TextView) dialog.findViewById(R.id.join);
                        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
                        passwordInput = (EditText) dialog.findViewById(R.id.passwordInput);
                        ImageView close = (ImageView) dialog.findViewById(R.id.close);

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        join.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String leaguePassword = s.getPassword();
                                String inputPassword = passwordInput.getText().toString();
                                System.out.println(leaguePassword);
                                System.out.println(inputPassword);
                                if(leaguePassword.equals(inputPassword)){
                                    dialog.dismiss();
                                    System.out.println("Match! League Password: " + leaguePassword + ", Input Password: " + inputPassword);
                                    //System.out.println("Joining Private League");
                                    //Toast.makeText(context,"Password Matched!", Toast.LENGTH_LONG).show();
                                    Toast.makeText(context,"Joining..." + s.getLeagueName()+"", Toast.LENGTH_LONG).show();

                                    final String[] status = {"error"};
                                    JSONObject json = new JSONObject();
                                    try {
                                            json.put("method", "joinPrivate");
                                            json.put("username", UserDAO.getLoginUser().getUsername()+"");
                                            json.put("leagueid", s.getLeagueId()+"");

                                    String url = DBConnection.privateLeagueUrl();

                                    PostHttp connection = new PostHttp();
                                    String response = null;

                                    response = connection.post(url, json.toString());
                                    JSONObject result = new JSONObject(response);
                                    status[0] = result.getString("status");

                                    System.out.println("Status of joining private league: " + status[0]);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    PrivateLeagueDAO.clearAllPrivateLeague();
                                    Intent intent = new Intent(context, PrivateLeagueMain.class);
                                    context.startActivity(intent);

                                    //add pop up success
                                }else{
                                    System.out.println("Non Match! League Password: " + leaguePassword + ", Input Password: " + inputPassword);
                                    Toast.makeText(context,"Invalid password", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                });
            }}else{
            viewHolder = (PrivateLeagueDiscoverAdapter.ViewHolder)convertView.getTag();
        }
        PrivateLeague s = leaguelist.get(position);
        viewHolder.league.setText("" +  s.getLeagueName() + "");
        viewHolder.joinprivateleaguebtn.setTag(s);
        return convertView;// returns the view for the current row
    }
    public void onClick(View view){}

    private void loadNextPage(View view, Context ctx, String leagueid) {
        Intent intent = new Intent(ctx, PrivateLeagueDetails.class);
        Bundle bundle = new Bundle();
        bundle.putString("leagueid", leagueid);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
