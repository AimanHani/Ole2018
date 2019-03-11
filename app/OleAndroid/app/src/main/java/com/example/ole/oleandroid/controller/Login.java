package com.example.ole.oleandroid.controller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.LoginDAO;
import com.example.ole.oleandroid.controller.PublicLeague.PastMatchesTab;
import com.example.ole.oleandroid.controller.PublicLeague.SpecialList;


public class Login extends AppCompatActivity {
    EditText username;
    TextInputEditText password;
    Button signin;
    TextView signup, forgotPwd;
    RequestQueue requestQueue;
    TextView result;
    TextView checksmth;
    CheckBox showPwd;
    // Button facebookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //to hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        checksmth = findViewById(R.id.checksmth);
        forgotPwd = findViewById(R.id.forgotPwd);
        //facebookButton = findViewById(R.id.facebookButton);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //result = (TextView) findViewById(R.id.result);
        //setContentView(R.layout.activity_loading_page);

/*        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, PastMatchesTab.class);
                startActivity(intent);
            }
        });*/

        forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "Forgot Click", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                } else {
                    final Dialog load = loadingDialog();
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    System.out.println("Signing In " + username.getText().toString());
                                    String status = "error";

                                    String send = concatUsernamePwd(username.getText().toString(), password.getText().toString());

                                    Boolean valid = LoginDAO.validate(send);
                                    load.dismiss();

                                    if (valid) {
                                        Intent intent = new Intent(Login.this, HomeLeague.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, 3000);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        checksmth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, PredictMainPage.class);
                startActivity(intent);
            }
        });

    }

    public Dialog loadingDialog() {
        System.out.println("loading pop");
        Dialog dialog2 = new Dialog(Login.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.loading_popup);
        TextView text = dialog2.findViewById(R.id.textStatus);
        text.setText("Logging in");
        dialog2.show();
        return dialog2;
    }


    public void loadSamePage() {
        Intent intent = new Intent(Login.this, Login.class);
        startActivity(intent);
    }

    public String concatUsernamePwd(String username, String pwd) {
        return "username=" + username + "&password=" + pwd;
    }
}

