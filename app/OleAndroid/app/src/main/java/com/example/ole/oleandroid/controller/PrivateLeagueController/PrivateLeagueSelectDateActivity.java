package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.controller.Signup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.provider.Settings.System.DATE_FORMAT;

public class PrivateLeagueSelectDateActivity extends SideMenuBar {
    private static final String TAG = "PrivateLeagueSelectDateActivity";
    private Button startdateButton;
    private Button enddateButton;
    private DatePickerDialog.OnDateSetListener dateSetListenerStart;
    private DatePickerDialog.OnDateSetListener dateSetListenerEnd;
    private long endDateInLong;

    EditText startdate, enddate;
    Button selectSpecialsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_select_date, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        /*
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String leaguename = extras.getString("prize");
            System.out.println("hello" + leaguename);
            //The key argument here must match that used in the other activity
        }
        */
        startdate = (EditText) findViewById(R.id.startDate);
        enddate = (EditText) findViewById(R.id.endDate);

        selectSpecialsButton = findViewById(R.id.selectSpecialsButton);
        selectSpecialsButton.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view){
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    Intent intent = new Intent(PrivateLeagueSelectDateActivity.this, SpecialListPrivate.class);
                    intent.putExtra("leaguename", extras.getString("leaguename"));
                    intent.putExtra("prize", extras.getString("prize"));
                    intent.putExtra("password", extras.getString("password"));
                    intent.putExtra("pointsAllocated", extras.getString("pointsAllocated"));
                    intent.putExtra("leagueid", extras.getString("leagueid"));
                    intent.putExtra("startdate", startdate.getText().toString());
                    intent.putExtra("enddate", enddate.getText().toString());
                    startActivity(intent);
                }
            }
        });

        startdateButton = findViewById(R.id.startDateButton);
        startdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PrivateLeagueSelectDateActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,
                        dateSetListenerStart, year, month, day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
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
                Log.d(TAG, DATE_FORMAT + year + "-" + month + "-" + day);
                String date = day + "-" + mth + "-" + year;
                Log.d(TAG, DATE_FORMAT + date);
                startdate.setText(date);
            }
        };

        enddateButton = findViewById(R.id.endDateButton);
        enddateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                try {
                    String EPLEndDate = "12/05/2019";
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(EPLEndDate);
                    endDateInLong = date.getTime();
                }
                catch (Exception e) {
                    System.out.println("line 113 parse exception");
                }

                DatePickerDialog dialog = new DatePickerDialog(
                        PrivateLeagueSelectDateActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,
                        dateSetListenerEnd, year, month, day);
                dialog.getDatePicker().setMaxDate(endDateInLong);
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
                Log.d(TAG, DATE_FORMAT + year + "-" + month + "-" + day);
                String date = day + "-" + mth + "-" + year;
                Log.d(TAG, DATE_FORMAT + date);
                enddate.setText(date);
            }
        };
    }
}
