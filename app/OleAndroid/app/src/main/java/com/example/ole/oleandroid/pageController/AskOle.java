package com.example.ole.oleandroid.pageController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.UserDAO;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AskOle extends AppCompatActivity {

    TextView userName;
    TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_ole);


        //get user object
        Intent i = getIntent();
        //final User u = (User)i.getSerializableExtra("User");
        User loginUser = UserDAO.getLoginUser();
        userName = findViewById(R.id.userName);
        userName.setText(loginUser.getUserName());
        email=findViewById(R.id.email);
        email.setText(loginUser.getEmail());
    }
}
