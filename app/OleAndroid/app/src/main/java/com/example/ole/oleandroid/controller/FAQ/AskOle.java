package com.example.ole.oleandroid.controller.FAQ;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
        //email = findViewById(R.id.email);
        final String[] category = {"General"};

        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"General/Others", "Private League", "Public League"}; //"Leaderboard", "League", "Profile",
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = (String) parent.getItemAtPosition(position);
                if (clickedItem.equals("General/Others")) {
                    clickedItem = "General";
                }
                category[0] = clickedItem;
                System.out.println(clickedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //email.setText(loginUser.getEmail());
        askOle = findViewById(R.id.askOleButton);
        questionInput = findViewById(R.id.questionInput);

        askOle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                User loginUser = UserDAO.getLoginUser();
                System.out.println("Creating Ask Ole");
//                System.out.println(loginUser.getUsername());
//                System.out.println(questionInput.getText().toString());
                String response = null;
                final String[] status = {"error"};
                JSONObject json = new JSONObject();

                try {
                    json.put("method", "insertNew");
                    json.put("username", loginUser.getUsername());
                    json.put("question", questionInput.getText().toString());
                    json.put("category", category[0]);

                    String url = DBConnection.askOle();
                    PostHttp connection = new PostHttp();

                    //System.out.println("inputs " + json.toString());

                    response = connection.post(url, json.toString());
                    JSONObject result = new JSONObject(response);
                    status[0] = result.getString("status");
                    System.out.println(response);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                final Dialog dialog = new Dialog(AskOle.this);
                dialog.setContentView(R.layout.activity_askole_confirm);
                dialog.show();

                TextView end = (TextView) dialog.findViewById(R.id.end);
                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(AskOle.this, FAQ.class);
                        startActivity(i);
                        finish();
                        dialog.dismiss();
                    }
                });

            }
        });

    }
}
