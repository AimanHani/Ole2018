package com.example.ole.oleandroid.controller;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.model.CountryItem;
import com.example.ole.oleandroid.model.TeamItems;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.ProgressDialog;
import android.os.Bundle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    private static final String TAG = "Signup";
    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
    private ArrayList<TeamItems> mTeamList;
    private TeamAdapter mAdapter2;
    EditText username, name, password, birthdate, email, contactNo;
    Button signupBtn;
    Spinner spinnerTeams, spinnerCountries;
//    TextView result;
    String clickedTeamName;
    String clickedCountryName;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initList();

        Spinner spinnerCountries = findViewById(R.id.countries);
        Spinner spinnerTeams = findViewById(R.id.favouriteTeam);
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        birthdate = findViewById(R.id.birthdate);
        email = findViewById(R.id.email);
        contactNo = findViewById(R.id.contactNo);
        signupBtn = findViewById(R.id.signupBtn);
        //result = findViewById(R.id.result);


        mAdapter = new CountryAdapter(this, mCountryList);
        spinnerCountries.setAdapter(mAdapter);

        mAdapter2 = new TeamAdapter(this, mTeamList);
        spinnerTeams.setAdapter(mAdapter2);


        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                clickedCountryName = clickedItem.getmCountryName();
                Toast.makeText(Signup.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TeamItems clickedItem = (TeamItems) parent.getItemAtPosition(position);
                clickedTeamName = clickedItem.getmTeamName();
                Toast.makeText(Signup.this, clickedTeamName + " selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
                //result.setText("");
                System.out.println("signup");
//                String results = LoginDAO.validate(username.getText().toString(), password.getText().toString());
//                result.append(results);

                String url = new DBConnection().getSignupUrl();
                //final String[] results = new String[1];

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                System.out.println(ServerResponse);
                                //results[0] = ServerResponse;
                                //result.append(ServerResponse);
                                Intent intent = new Intent(Signup.this, Login.class);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                System.out.println("error  :");
                                volleyError.printStackTrace();

                                Intent intent = new Intent(Signup.this, Signup.class);
                                startActivity(intent);
                                //result.append("error");
                                //results[0] = "error";
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        try {
                            params.put("username", username.getText().toString());
                            params.put("name", name.getText().toString());
                            params.put("password", SHA1(password.getText().toString()));
                            params.put("dob", birthdate.getText().toString());
                            params.put("country", clickedCountryName);
                            params.put("contactNo", contactNo.getText().toString());
                            params.put("email", email.getText().toString());
                            params.put("favoriteTeam", clickedTeamName);
                        } catch (Exception e) {

                        }
                        return params;
                    }

                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);


            }
        });
    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String validateuserName = username.getText().toString();
        String validateName = name.getText().toString();
        String validateEmail = email.getText().toString();
        String validatePassword = password.getText().toString();
        String validateBirthdate = birthdate.getText().toString();
        String validatePhoneNo = contactNo.getText().toString();
        String validateTeam = clickedTeamName;
        String validateCountries = clickedCountryName;


        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        signupBtn.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        signupBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String validateuserName = username.getText().toString();
        String validateName = name.getText().toString();
        String validateEmail = email.getText().toString();
        String validatePassword = password.getText().toString();
        String validateBirthdate = birthdate.getText().toString();
        String validatePhoneNo = contactNo.getText().toString();
        String validateTeam = clickedTeamName;
        String validateCountries = clickedCountryName;

        if (validateuserName.isEmpty() || name.length() < 3) {
            username.setError("at least 3 characters");
            valid = false;
        } else {
            username.setError(null);
        }

        if (validateEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(validateEmail).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (validatePassword.isEmpty() || validatePassword.length() < 4 || validatePassword.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }
        if (validateCountries.equals(null)) {
            setSpinnerError(spinnerCountries, "Please select your country");
            valid = false;
        }

        if (validateTeam.equals(null)) {
            setSpinnerError(spinnerTeams, "Please select a favourite team");
            valid = false;
        }
        if (validateBirthdate.isEmpty() || !isDateValid(validateBirthdate)) {
            birthdate.setError("Please enter the date as yy-MM-dd format");
        }

        return valid;
    }

    private void initList() {
        mCountryList = new ArrayList<>();
        mCountryList.add(new CountryItem("Singapore", R.drawable.singapore));
        mCountryList.add(new CountryItem("Malaysia", R.drawable.malaysia));


        mTeamList = new ArrayList<>();
        mTeamList.add(new TeamItems("Arsenal", R.drawable.arsenal));
        mTeamList.add(new TeamItems("AFC Bournemouth ", R.drawable.afc_bournemouth));
        mTeamList.add(new TeamItems("Brighton and Hove Albion", R.drawable.brighton));
        mTeamList.add(new TeamItems("Burnley", R.drawable.burnley));
        mTeamList.add(new TeamItems("Cardiff City", R.drawable.cardiff));
        mTeamList.add(new TeamItems("Chelsea", R.drawable.chelsea));
        mTeamList.add(new TeamItems("Crystal Palace", R.drawable.crystal_palace));
        mTeamList.add(new TeamItems("Everton", R.drawable.everton));
        mTeamList.add(new TeamItems("Fulham", R.drawable.fulham));
        mTeamList.add(new TeamItems("Huddersfield Town", R.drawable.hudderfield));
        mTeamList.add(new TeamItems("Leicester City", R.drawable.leicester_city));
        mTeamList.add(new TeamItems("Liverpool", R.drawable.liverpool));
        mTeamList.add(new TeamItems("Manchester City", R.drawable.manchester_city));
        mTeamList.add(new TeamItems("Manchester United", R.drawable.manchester_united));
        mTeamList.add(new TeamItems("Newcastle United", R.drawable.newcastle_united));
        mTeamList.add(new TeamItems("Southampton", R.drawable.southampton));
        mTeamList.add(new TeamItems("Tottenham Hotspur", R.drawable.tottenham_hotspur));
        mTeamList.add(new TeamItems("Watford", R.drawable.watford));
        mTeamList.add(new TeamItems("West Ham United", R.drawable.west_ham));
        mTeamList.add(new TeamItems("Wolverhampton Wanderes", R.drawable.wolverhampton));


    }

    private void setSpinnerError(Spinner spinner, String error) {
        View selectedView = spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error"); // any name of the error will do
            selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView.setText(error); // actual error message
            spinner.performClick(); // to open the spinner list if error is found.

        }
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    final static String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
