package com.example.ole.oleandroid.controller;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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
                final String usernameInput = username.getText().toString();
                final String passwordInput = password.getText().toString();

                if (usernameInput.equals("") || passwordInput.equals("")) {
                    showToast();
                } else {
                    final Dialog load = loadingDialog();
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    System.out.println("Signing In " + usernameInput);

                                    String send = concatUsernamePwd(usernameInput, passwordInput);
                                    Boolean valid = LoginDAO.validate(send);
                                    load.dismiss();

                                    if (valid) {
                                        SharedPreferences preferences = getSharedPreferences("logged", MODE_PRIVATE);
                                        preferences.edit().putBoolean("login", true).apply();
                                        preferences.edit().putString("username", usernameInput).apply();
                                        preferences.edit().putString("password", passwordInput).apply();

                                        Intent intent = new Intent(Login.this, HomeLeague.class);
                                        intent.putExtra("FROM_ACTIVITY", "Login");
                                        startActivity(intent);
                                    } else {
                                        showToast();
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

//                try {
//                    Intent sendIntent = new Intent();
//                    sendIntent.setAction(Intent.ACTION_SEND);
//                    sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                    sendIntent.setType("text/plain");
//                    sendIntent.setPackage("com.whatsapp");
//                    startActivity(sendIntent);
//
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(getBaseContext(), "Whatsapp have not been installed.", Toast.LENGTH_LONG).show();
//                }

                Intent intent = new Intent(Login.this, FormGuideDetails.class);
                startActivity(intent);
            }
        });

    }

    public void showToast() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
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

    public static String concatUsernamePwd(String username, String pwd) {
        return "username=" + username + "&password=" + pwd;
    }


    @Override
    public void onBackPressed() {
        //do nothing
    }

//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
}

