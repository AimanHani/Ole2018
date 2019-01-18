package com.example.ole.oleandroid.controller;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    private static final String TAG = "Signup";
    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
    private ArrayList<TeamItems> mTeamList;
    private TeamAdapter mAdapter2;
    private Timer timer;
    private Dialog dialog;
    private Button birthdateButton;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    EditText username, name, password, birthdate, email, contactNo;
    Button signupBtn;
    Spinner spinnerTeams, spinnerCountries;
    Drawable tickDone;
    //    TextView result;
    String clickedTeamName;
    String clickedCountryName;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initList( );

        Spinner spinnerCountries = findViewById(R.id.countries);
        Spinner spinnerTeams = findViewById(R.id.favouriteTeam);
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        birthdate = findViewById(R.id.birthdate);
        email = findViewById(R.id.email);
        contactNo = findViewById(R.id.contactNo);
        signupBtn = findViewById(R.id.signupBtn);
        tickDone = getResources( ).getDrawable(R.drawable.ic_done_black_24dp);
        tickDone.setBounds(0, 0, tickDone.getIntrinsicWidth( ), tickDone.getIntrinsicHeight( ));
        //result = findViewById(R.id.result);
        validationOnTextView();//method used to vaidate when user input see the method at the buttom

        mAdapter = new CountryAdapter(this, mCountryList);
        spinnerCountries.setAdapter(mAdapter);

        mAdapter2 = new TeamAdapter(this, mTeamList);
        spinnerTeams.setAdapter(mAdapter2);


        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener( ) {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                clickedCountryName = clickedItem.getmCountryName( );
                Toast.makeText(Signup.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show( );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener( ) {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TeamItems clickedItem = (TeamItems) parent.getItemAtPosition(position);
                clickedTeamName = clickedItem.getmTeamName( );
                Toast.makeText(Signup.this, clickedTeamName + " selected", Toast.LENGTH_SHORT).show( );

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        signupBtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                signup( );
                //result.setText("");
                System.out.println("signup");
//                String results = LoginDAO.validate(username.getText().toString(), password.getText().toString());
//                result.append(results);

                String url = new DBConnection( ).getSignupUrl( );
                //final String[] results = new String[1];

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>( ) {
                            @Override
                            public void onResponse(String ServerResponse) {
                                System.out.println(ServerResponse);
                                //results[0] = ServerResponse;
                                //result.append(ServerResponse);
                                Intent intent = new Intent(Signup.this, Home.class);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener( ) {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                System.out.println("error  :");
                                volleyError.printStackTrace( );

                                Intent intent = new Intent(Signup.this, Signup.class);
                                startActivity(intent);
                                //result.append("error");
                                //results[0] = "error";
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>( );
                        try {
                            params.put("username", username.getText( ).toString( ));
                            params.put("name", name.getText( ).toString( ));
                            params.put("password", password.getText( ).toString( ));
                            params.put("birthdate", birthdate.getText( ).toString( ));
                            params.put("country", clickedCountryName);
                            params.put("contactNo", contactNo.getText( ).toString( ));
                            params.put("email", email.getText( ).toString( ));
                            params.put("team", clickedTeamName);
                            validate( );
                        } catch (Exception e) {

                        }
                        return params;
                    }
                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext( ));

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);


            }
        });
    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate( )) {
            onSignupFailed( );
            return;
        }else {

            signupBtn.setEnabled(false);

            /*final ProgressDialog progressDialog = new ProgressDialog(Signup.this,
                    R.style.AppTheme);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();*/


            // TODO: Implement your own signup logic here.

            /*new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onSignupSuccess or onSignupFailed
                            // depending on success
                            onSignupSuccess();
                            // onSignupFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000); */
            onSignupSuccess();
        }
    }


    public void onSignupSuccess() {
        signupBtn.setEnabled(true);
        setResult(RESULT_OK, null);
        finish( );
        successfulAlertDialog();
    }

    public void successfulAlertDialog(){
        dialog = new Dialog(Signup.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.successful_pop_up);

        Button close = dialog.findViewById(R.id.close);

        close.setEnabled(true);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();

        /*signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v1) {
                Intent launchActivity1= new Intent(MainActivity.this,SignUp.class);
                startActivity(launchActivity1);

            }*/
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext( ), "Signup failed", Toast.LENGTH_LONG).show( );

        signupBtn.setEnabled(true);
    }


   //methods used for above UI

    public boolean validate() {
        boolean valid = true;

        String validateuserName = username.getText( ).toString( );
        String validateName = name.getText( ).toString( );
        String validateEmail = email.getText( ).toString( );
        String validatePassword = password.getText( ).toString( );
        String validateBirthdate = birthdate.getText( ).toString( );
        String validatePhoneNo = contactNo.getText( ).toString( );
        String validateTeam = clickedTeamName;
        String validateCountries = clickedCountryName;

        if (validateuserName.isEmpty( ) || name.length( ) < 3) {
            username.setError("at least 3 characters");
            valid = false;
        } else {
            username.setError("Good", tickDone);
        }

        if (validateName.isEmpty( ) || validateName.length( ) < 3) {
            name.setError("at least 3 characters for your name");

        } else {
            name.setError("Good", tickDone);
        }

        if (validateEmail.isEmpty( ) || !android.util.Patterns.EMAIL_ADDRESS.matcher(validateEmail).matches( )) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError("Good", tickDone);
        }

        if (isStrong(validatePassword)) {
            password.setError("Good", tickDone);

        } else {
            password.setError("8 characters length\n" +
                    "2 letters in Upper Case\n" +
                    "1 Special Character (!@#$&*)\n" +
                    "2 numerals (0-9)\n" +
                    "3 letters in Lower Case");
            valid = false;
        }
        if (validateCountries.equals(null)) {
            setSpinnerError(spinnerCountries, "Please select your country");
            valid = false;
        }

        if (validateTeam.equals(null)) {
            setSpinnerError(spinnerTeams, "Please select a favourite team");
            valid = false;
        }
        if (validateBirthdate.isEmpty( ) || !isDateValid(validateBirthdate)) {
            birthdate.setError("Please enter the date as yyyy-MM-dd format");
            valid = false;
        }
        if (!validateBirthdate.isEmpty( ) && isDateValid(validateBirthdate)) {
            int age = calculateAge(validateBirthdate);
            if (age < 16) {
                birthdate.setError("Your age must be at least 16");
                valid = false;
            } else {
                birthdate.setError("Good", tickDone);
            }

        }
        if(!checkPhoneNumber(validatePhoneNo)||validatePhoneNo.isEmpty()){
            contactNo.setError("Please enter a valid phone number with your country code");
            valid = false;
        }
        else{
            contactNo.setError("Good",tickDone);
        }

        return valid;
    }

    private void initList() {
        mCountryList = new ArrayList<>( );
        mCountryList.add(new CountryItem("Singapore", R.drawable.singapore));
        mCountryList.add(new CountryItem("Malaysia", R.drawable.malaysia));


        mTeamList = new ArrayList<>( );
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
        View selectedView = spinner.getSelectedView( );
        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus( );
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error"); // any name of the error will do
            selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView.setText(error); // actual error message
            spinner.performClick( ); // to open the spinner list if error is found.

        }
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer( );
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
        return buf.toString( );
    }

    public static String SHA1(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length( ));
        sha1hash = md.digest( );
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

    private boolean isStrong(String password) {
        return password.matches("^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$");
    }

    public static int calculateAge(String birthDay) {
        if ((birthDay != null)) {
            DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthDay, formatter_1);
            LocalDate today = LocalDate.now( );
            return Period.between(birthDate, today).getYears( );
        } else {
            return 0;
        }
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        return phoneNumber.matches(regex);
    }

    public void validationOnTextView(){
        name.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel( );
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
                timer = new Timer( );
                timer.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        Signup.this.runOnUiThread(new Runnable( ) {
                            @Override
                            public void run() {
                                if (s.toString( ).isEmpty( ) || s.toString( ).length( ) < 3) {
                                    name.setError("at least 3 characters for your name");

                                } else {
                                    name.setError("Good", tickDone);
                                }
                            }
                        });
                    }
                }, 600); // 600ms delay before the timer executes the „run“ method from TimerTask

            }
        });
        username.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel( );
                }

            }

            @Override
            public void afterTextChanged(final Editable s) {

                timer = new Timer( );
                timer.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        Signup.this.runOnUiThread(new Runnable( ) {
                            @Override
                            public void run() {
                                if (s.toString( ).isEmpty( ) || s.toString( ).length( ) < 5) {
                                    username.setError("at least 5 characters");

                                } else {
                                    username.setError("Good", tickDone);
                                }
                            }
                        });
                    }
                }, 600); // 600ms delay before the timer executes the „run“ method from TimerTask


            }
        });
        password.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (isStrong(s.toString( ))) {

                    password.setError("Good", tickDone);


                } else {
                    password.setError("8 characters length\n" +
                            "2 letters in Upper Case\n" +
                            "1 Special Character (!@#$&*)\n" +
                            "2 numerals (0-9)\n" +
                            "3 letters in Lower Case");

                }

            }
        });
        email.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel( );
                }

            }

            @Override
            public void afterTextChanged(final Editable s) {
                timer = new Timer( );
                timer.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        Signup.this.runOnUiThread(new Runnable( ) {
                            @Override
                            public void run() {
                                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString( )).matches( )) {
                                    email.setError("enter a valid email address");
                                } else {
                                    email.setError("Good", tickDone);
                                }
                            }
                        });
                    }
                }, 600); // 600ms delay before the timer executes the „run“ method from TimerTask

            }
        });
        birthdateButton = findViewById(R.id.birthdateButton);
        birthdateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Signup.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,
                        dateSetListener,year,month,day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.blue(0b1449)));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateset: yyyy-mm-dd: "+year+"-"+month+"-"+day);
                String date = year+"-"+month+"-"+day;
                birthdate.setText(date);
            }
        };

        birthdate.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel( );
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
                timer = new Timer( );
                timer.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        Signup.this.runOnUiThread(new Runnable( ) {
                            @Override
                            public void run() {
                                if (!isDateValid(s.toString( ))) {
                                    birthdate.setError("Please enter the date as yyyy-MM-dd format");
                                }
                                if (isDateValid(s.toString( ))) {
                                    int age = calculateAge(s.toString( ));
                                    if (age < 16) {
                                        birthdate.setError("You are under the age of 16");
                                    } else {
                                        birthdate.setError("Good", tickDone);
                                    }

                                }
                            }
                        });
                    }
                }, 600); // 600ms delay before the timer executes the „run“ method from TimerTask
            }
        });


        contactNo.addTextChangedListener(new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel( );
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
                timer = new Timer( );
                timer.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        Signup.this.runOnUiThread(new Runnable( ) {
                            @Override
                            public void run() {
                                if (!checkPhoneNumber(s.toString())) {
                                    contactNo.setError("Please enter a valid phone number with your country code");

                                } else {
                                    contactNo.setError("Good", tickDone);
                                }
                            }
                        });
                    }
                }, 600); // 600ms delay before the timer executes the „run“ method from TimerTask
            }
        });
    }
}
