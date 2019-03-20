package com.example.ole.oleandroid.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
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

import com.android.volley.RequestQueue;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.SignupDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.model.CountryItem;
import com.example.ole.oleandroid.model.TeamItems;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

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
    String clickedTeamName, clickedCountryName;
    RequestQueue requestQueue;
    HashMap<String, String> countryCodes;
    ArrayList<String> usernames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initList();

        //to hide the action bar
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Spinner spinnerCountries = findViewById(R.id.countries);
        Spinner spinnerTeams = findViewById(R.id.favouriteTeam);
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        birthdate = findViewById(R.id.birthdate);
        email = findViewById(R.id.email);
        contactNo = findViewById(R.id.contactNo);
        signupBtn = findViewById(R.id.signupBtn);
        tickDone = getResources().getDrawable(R.drawable.ic_done_black_24dp);
        tickDone.setBounds(0, 0, tickDone.getIntrinsicWidth(), tickDone.getIntrinsicHeight());
        //result = findViewById(R.id.result);
        validationOnTextView();//method used to vaidate when user input see the method at the buttom

        mAdapter = new CountryAdapter(this, mCountryList);
        spinnerCountries.setAdapter(mAdapter);

        mAdapter2 = new TeamAdapter(this, mTeamList);
        spinnerTeams.setAdapter(mAdapter2);

        //UserDAO.getUsernames();

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                clickedCountryName = clickedItem.getmCountryName();
                Toast.makeText(Signup.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
                contactNo.setText(countryCodes.get(clickedCountryName));
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
                System.out.println("signup");
                final String usernameStr = username.getText().toString();
                final String nameStr = name.getText().toString();
                final String emailStr = email.getText().toString();

                boolean valid = validate();
                String checkUsernameEmail = SignupDAO.checkEmailPassword(usernameStr, emailStr);

                if (!valid || !checkUsernameEmail.equals("success")) {
                    onSignupFailed(checkUsernameEmail);
                    return;
                } else {

                    final Dialog load = loadingDialog();

                    final String passwordStr = password.getText().toString();
                    final String birthdateStr = birthdate.getText().toString();
                    final String contactNoStr = contactNo.getText().toString();
                    final String teamStr = clickedTeamName;
                    final String countryStr = clickedCountryName;

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    System.out.println("signup2");

                                    final String verificationNumber = SignupDAO.verify(contactNoStr, emailStr);
                                    System.out.println("verificationNumber " + verificationNumber);
                                    final Dialog verified = verificationDialog(verificationNumber);

                                    final EditText smsInput = verified.findViewById(R.id.smsInput);
                                    TextView cancel = verified.findViewById(R.id.cancel);
                                    TextView confirm = verified.findViewById(R.id.confirm);

                                    confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //if success,
                                            if (verificationNumber.equals(smsInput.getText().toString())) {
                                                System.out.println("contact " + contactNoStr);
                                                String send = concatParams(usernameStr, nameStr, passwordStr, birthdateStr, countryStr, contactNoStr, emailStr, teamStr);

                                                Boolean signUp = SignupDAO.validate(send, usernameStr, passwordStr);
                                                load.cancel();
                                                verified.cancel();
                                                if (signUp) {
                                                    signupBtn.setEnabled(false);
                                                    onSignupSuccess();
                                                } else {
                                                    onSignupFailed("");
                                                    return;
                                                }
                                            }
                                            return;
                                        }
                                    });

                                    cancel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.cancel();
                                        }
                                    });

                                    System.out.println("ver " + verified);
                                }
                            }, 2000);
                }
            }
        });
    }

    private String concatParams(String username, String name, String password, String birthdate, String country, String contactNo, String email, String team) {
        String params = "username=" + username;
        params += "&name=" + name;
        params += "&password=" + password;
        params += "&birthdate=" + birthdate;
        params += "&country=" + country;
        params += "&contactNo=" + contactNo;
        params += "&email=" + email;
        params += "&team=" + team;

        return params;
    }


    public void onSignupSuccess() {
        signupBtn.setEnabled(true);
        setResult(RESULT_OK, null);
        //finish();
        //smsVerification();
        successfulAlertDialog();
    }

    public Dialog loadingDialog() {
        System.out.println("loading pop");
        Dialog dialog2 = new Dialog(Signup.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.loading_popup);

        dialog2.show();
        return dialog2;
    }

    public Dialog verificationDialog(final String verificationNumber) {
        final boolean[] verified = {false};
        dialog = new Dialog(Signup.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_sms_verification);
        dialog.show();

        final EditText smsInput = dialog.findViewById(R.id.smsInput);
        TextView cancel = dialog.findViewById(R.id.cancel);
        TextView confirm = dialog.findViewById(R.id.confirm);

        return dialog;
    }

    public void successfulAlertDialog() {
        dialog = new Dialog(Signup.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.successful_pop_up);

        TextView confirm = dialog.findViewById(R.id.confirm);
        TextView caption = dialog.findViewById(R.id.successfulMessage);

        caption.setText("Successfully Signed Up! Press confirm to proceed to app!");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, HomeLeague.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", username.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void onSignupFailed(String message) {
        String errorMsg = "Signup failed";
        if (!message.equals("success") || message.equals("")) {
            errorMsg += " - " + message;
        }
        Toast.makeText(getBaseContext(), errorMsg, Toast.LENGTH_LONG).show();
        signupBtn.setEnabled(true);
    }


    //methods used for above UI

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

        if (validateuserName.isEmpty() || validateuserName.length() < 5) {
            username.setError("at least 5 characters");
            valid = false;
        } else if (validateName.contains(" ")) {
            username.setError("Username cannot contain space");
        } else {
            username.setError("Good", tickDone);
        }

        if (validateName.isEmpty() || validateName.length() < 1) {
            name.setError("Please enter name");

        } else {
            name.setError("Good", tickDone);
        }

        if (validateEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(validateEmail).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError("Good", tickDone);
        }

        if (isStrong(validatePassword)) {
            password.setError("Good", tickDone);

        } else {
            password.setError("8 or more characters length\n" +
                    "Min 1 letters in Upper Case\n" +
                    "Min 1 numerals (0-9)\n" +
                    "Min 1 letters in Lower Case");
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
        if (validateBirthdate.isEmpty() || !isDateValid(validateBirthdate)) {
            birthdate.setError("Please enter the date as dd-mm-yyyy format");
            valid = false;
        }
        if (!validateBirthdate.isEmpty() && validateBirthdate.length() == 10) {
            if (isDateValid(validateBirthdate)) {
//                int age = getAge(validateBirthdate);
//                System.out.println("age "+ age);
//                if (age < 13) {
//                    birthdate.setError("Your age must be at least 13");
//                    valid = false;
//                } else {
//                    birthdate.setError("Good", tickDone);
//                }
            }
        }
        if (!checkPhoneNumber(validatePhoneNo, validateCountries) || validatePhoneNo.isEmpty()) {
            contactNo.setError("Please enter a valid phone number with your country code");
            valid = false;
        } else {
            contactNo.setError("Good", tickDone);
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

        countryCodes = new HashMap<>();
        countryCodes.put("Singapore", "+65");
        countryCodes.put("Malaysia", "+60");

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

    //final static String DATE_FORMAT = "yyyy-MM-dd";
    final static String DATE_FORMAT = "dd-MM-yyyy";

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isStrong(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,20}$");
        //return password.matches("^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,20}$");
    }

    //    public static int calculateAge(String birthDay) {
//        if (birthDay != null) {
//            DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern(DATE_FORMAT);
//            LocalDate birthDate = null;
//            try {
//                birthDate = LocalDate.parse(birthDay, formatter_1);
//            } catch (Exception e) {
//                return 0;
//            }
//            LocalDate today = LocalDate.now();
//            int intervalYears = (int) ChronoUnit.YEARS.between(birthDate, today);
//            return intervalYears;
//        } else {
//            return 0;
//        }
//    }
    //dd-mm-yyyy
    private int getAge(String birthday) {
        System.out.println("getage");
        String[] parts = birthday.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        System.out.println("mth: " + month);
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

//        Integer ageInt = new Integer(age);
//        String ageS = ageInt.toString();
        System.out.println(age + "");
        return age;
    }

    private boolean checkPhoneNumber(String phoneNumber, String validateCountries) {
        //phoneNumber = countryCodes.get(validateCountries) + phoneNumber;
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        return phoneNumber.matches(regex);
    }

    public void validationOnTextView() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Signup.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (s.toString().isEmpty() || s.toString().length() < 3) {
                                    name.setError("at least 3 characters for your name");

                                } else {
                                    name.setError("Good", tickDone);
                                }
                            }
                        });
                    }
                }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask

            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }

            }

            @Override
            public void afterTextChanged(final Editable s) {

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Signup.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (s.toString().isEmpty() || s.toString().length() < 5) {
                                    username.setError("at least 5 characters");
                                } else if (UserDAO.checkUsernameExist(s.toString())) {
                                    username.setError("Username Exist");
                                } else if (s.toString().contains(" ")) {
                                    username.setError("Username cannot contain space");
                                } else {
                                    username.setError("Good", tickDone);
                                }
                            }
                        });
                    }
                }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask


            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (isStrong(s.toString())) {

                    password.setError("Good", tickDone);


                } else {
                    password.setError("8 or more characters \n" +
                            "Min 1 Upper Case\n" +
                            "Min 1 numeral (0-9)\n" +
                            "Min 1 letter in Lower Case");

                }

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }

            }

            @Override
            public void afterTextChanged(final Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Signup.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                                    email.setError("enter a valid email address");
                                } else {
                                    email.setError("Good", tickDone);
                                }
                            }
                        });
                    }
                }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask

            }
        });
        //Calendar code
        birthdateButton = findViewById(R.id.birthdateButton);
        birthdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Signup.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,
                        dateSetListener, year, month, day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.blue(0b1449)));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String mth = month + "";
                if (month < 10) {
                    mth = "0" + mth;
                }
                Log.d(TAG, DATE_FORMAT + year + "-" + month + "-" + day);
                String date = day + "-" + mth + "-" + year;
                Log.d(TAG, DATE_FORMAT + date);
                birthdate.setText(date);
            }
        };

        birthdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Signup.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!isDateValid(s.toString())) {
                                    birthdate.setError("Please enter the date as dd-MM-yyyy format");
                                }
//                                if (isDateValid(s.toString())) {
//                                    int age = getAge(s.toString());
//                                    if (age < 13) {
//                                        birthdate.setError("You are under the age of 13");
//                                    } else {
//                                        birthdate.setError("Good", tickDone);
//                                    }
//
//                                }
                            }
                        });
                    }
                }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask
            }
        });


        contactNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Signup.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!checkPhoneNumber(s.toString(), clickedCountryName)) {
                                    contactNo.setError("Please enter a valid phone number with your country code");

                                } else {
                                    contactNo.setError("Good", tickDone);
                                }
                            }
                        });
                    }
                }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask
            }
        });
    }
}
