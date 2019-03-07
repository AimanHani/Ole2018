package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.controller.Signup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.provider.Settings.System.DATE_FORMAT;
import static com.example.ole.oleandroid.controller.Signup.isDateValid;

public class PrivateLeagueSelectDateActivity extends SideMenuBar {
    private static final String TAG = "PrivateLeagueSelectDateActivity";
    private Button startdateButton;
    private Button enddateButton;
    private DatePickerDialog.OnDateSetListener dateSetListenerStart;
    private DatePickerDialog.OnDateSetListener dateSetListenerEnd;
    private long endDateInLong;
    private Timer timer;

    EditText startdate, enddate;
    Button selectSpecialsButton;

    boolean startOk = false;
    boolean endOk = false;

    private Button startDateButton;
    private Button endDateButton;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    //final static String DATE_FORMAT = "dd-MM-yyyy";
    final static String DATE_FORMAT = "yyyy-MM-dd";
    private String eDate, sDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_select_date, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        startdate = (EditText) findViewById(R.id.startDate);
        enddate = (EditText) findViewById(R.id.endDate);

        //Calendar code
        startdateButton = findViewById(R.id.startDateButton);
        startdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startdate.setFocusable(true);
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PrivateLeagueSelectDateActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,
                        dateSetListenerStart, year, month, day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.blue(0b1449)));
                dialog.show();



            }
        });

        dateSetListenerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String mth = month + "";
                if (month < 10) {
                    mth = "0" + mth;
                }

                String dayS = day + "";
                if (day < 10) {
                    dayS = "0" + day;
                }
                //Log.d(TAG, DATE_FORMAT + year + "-" + month + "-" + day);
                String showDate = dayS + "-" + mth + "-" + year;
                sDate = year + "-" + mth + "-" + dayS;
                //Log.d(TAG, DATE_FORMAT + date);
                //changing the format to sql one
                try {
                    DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                    df.setLenient(false);
                    //df.parse(date);
                    startdate.setText(showDate);
                } catch (Exception e) {
                }
                startOk=true;
            }
        };

        endDateButton = findViewById(R.id.endDateButton);
        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enddate.setFocusable(true);
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PrivateLeagueSelectDateActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,
                        dateSetListenerEnd, year, month, day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.blue(0b1449)));
                dialog.show();

            }
        });

        dateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String mth = month + "";
                if (month < 10) {
                    mth = "0" + mth;
                }

                String dayS = day + "";
                if (day < 10) {
                    dayS = "0" + day;
                }

                //Log.d(TAG, DATE_FORMAT + year + "-" + month + "-" + day);
                String showDate = dayS + "-" + mth + "-" + year;
                eDate = year + "-" + mth + "-" + dayS;
                //Log.d(TAG, DATE_FORMAT + date);

                //changing the format to sql one
                try {
                    DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                    df.setLenient(false);
                    enddate.setText(showDate);

                } catch (Exception e) {
                }
                endOk=true;
            }
        };


        validationOnTextView();



        selectSpecialsButton = findViewById(R.id.selectSpecialsButton);
        selectSpecialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = getIntent().getExtras();
                if (!startdate.getEditableText().toString().isEmpty() && !enddate.getEditableText().toString().isEmpty()  && startOk && endOk ) {

                    if (extras != null) {
                        Intent intent = new Intent(PrivateLeagueSelectDateActivity.this, SpecialListPrivate.class);
                        intent.putExtra("leaguename", extras.getString("leaguename"));
                        intent.putExtra("prize", extras.getString("prize"));
                        intent.putExtra("password", extras.getString("password"));
                        intent.putExtra("pointsAllocated", extras.getString("pointsAllocated"));
                        intent.putExtra("leagueid", extras.getString("leagueid"));
                        intent.putExtra("startdate", sDate);
                        intent.putExtra("enddate", eDate);
                        startActivity(intent);
                    }
                }else {
                    Toast.makeText(PrivateLeagueSelectDateActivity.this,
                            "Please Complete the form", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void validationOnTextView() {

        startdate.addTextChangedListener(new TextWatcher() {
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
                        PrivateLeagueSelectDateActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!isDateValid(s.toString())) {
                                    startdate.setError("Please enter the date as dd-MM-yyyy format");
                                }

                            }
                        });
                    }
                }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask
            }
        });

        enddate.addTextChangedListener(new TextWatcher() {
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
                        PrivateLeagueSelectDateActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!isDateValid(s.toString())) {
                                    enddate.setError("Please enter the date as dd-MM-yyyy format");
                                }

                            }
                        });
                    }
                }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask
            }
        });



    }


}
