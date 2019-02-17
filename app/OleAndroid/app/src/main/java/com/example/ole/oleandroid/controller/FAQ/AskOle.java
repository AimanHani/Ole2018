package com.example.ole.oleandroid.controller.FAQ;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.User;

import org.json.JSONObject;

public class AskOle extends SideMenuBar {

    TextView userName;
    //TextView email;
    Button askOle;
    EditText questionInput, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_ask_ole, null, false);
        super.mDrawerlayout.addView(contentView, 0);


        //get user object
        Intent i = getIntent();
        //final User u = (User)i.getSerializableExtra("User");
        User loginUser = UserDAO.getLoginUser();
        userName = findViewById(R.id.userName);
        userName.setText(loginUser.getUsername());
        email=findViewById(R.id.email);
        //email.setText(loginUser.getEmail());
        askOle=findViewById(R.id.askOleButton);
        questionInput = findViewById(R.id.questionInput);

        askOle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{



                Intent intent = getIntent();
                User loginUser = UserDAO.getLoginUser();
                System.out.println("Creating Ask Ole");
                    System.out.println(loginUser.getUsername());
                    System.out.println(email.getText().toString());
                    System.out.println(questionInput.getText().toString());

                final String[] status = {"error"};
                JSONObject json = new JSONObject();
                try {

                        json.put("method", "insertNew");
                        json.put("username", loginUser.getUsername());
                        json.put("question", questionInput.getText().toString());
                        json.put("category", email.getText().toString());

                }catch(Exception e){
                    e.printStackTrace();
                }
                String url = DBConnection.askOle();

                PostHttp connection = new PostHttp();
                String response = null;
                //System.out.println("HAHAHAHHA" + json.toString());

                response = connection.post(url, json.toString());
                JSONObject result = new JSONObject(response);
                status[0] = result.getString("status");

                System.out.println(response);

            }catch(Exception e){
                //Toast.makeText(TeamListPrivate.this,
                //      "Please select at least 1", Toast.LENGTH_LONG).show();
            }

                final Dialog dialog = new Dialog(AskOle.this);
                dialog.setContentView(R.layout.activity_askole_confirm);

                TextView end = (TextView) dialog.findViewById(R.id.end);
                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(AskOle.this, FAQ.class);
                        startActivity(i);
                        finish();
                    }
                });

                dialog.show();
                //return true;
                //default:
                //return false;
            }
        });
    }
}
