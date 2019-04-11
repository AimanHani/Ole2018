package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.controller.Signup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

public class PrivateLeagueCreate extends SideMenuBar {
    EditText leaguename, prize, password;
    Button selectleague;
    private Timer timer;
    Drawable tickDone;
    ArrayList<String> allLeague = null;
    boolean nameOk = false;
    boolean prizeOk = false;
    boolean passwordOk = false;
    boolean pointsAllocatedOk = false;
    ArrayList<String> keywords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_create, null, false);
        super.mDrawerlayout.addView(contentView, 0);
        restrictionList();
        replace(keywords);
        tickDone = getResources().getDrawable(R.drawable.ic_done_black_24dp);
        tickDone.setBounds(0, 0, tickDone.getIntrinsicWidth(), tickDone.getIntrinsicHeight());


        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            allLeague = extras.getStringArrayList("allLeague");
            // and get whatever type user account id is
        }
        //System.out.println(allLeague.size() + "in here");

        leaguename = (EditText) findViewById(R.id.leaguename);
        prize = (EditText) findViewById(R.id.prize);
        password = (EditText) findViewById(R.id.password);
        //pointsAllocated = (EditText) findViewById(R.id.points);

        validationOnTextView();

        selectleague = findViewById(R.id.selectleague);
        selectleague.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (allLeague.contains(leaguename.getEditableText().toString())) {
                    leaguename.setError("name is taken");
                    Toast.makeText(PrivateLeagueCreate.this,
                            "League name is already taken ", Toast.LENGTH_LONG).show();

                } else {

                    if (!leaguename.getEditableText().toString().isEmpty() && !prize.getEditableText().toString().isEmpty() && !password.getEditableText().toString().isEmpty() && nameOk && prizeOk && passwordOk) {
                        //if (leaguename!=null && prize!=null && password!=null) {
                        Intent intent = new Intent(PrivateLeagueCreate.this, PrivateLeagueTournamentList.class);
                        intent.putExtra("leaguename", leaguename.getText().toString());
                        intent.putExtra("prize", prize.getText().toString());
                        intent.putExtra("password", password.getText().toString());
                        intent.putExtra("pointsAllocated", "1");
                        startActivity(intent);
                    } else {
                        Toast.makeText(PrivateLeagueCreate.this,
                                "Please Complete the form", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }

    public void validationOnTextView() {
        leaguename.addTextChangedListener(new TextWatcher() {
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
                        PrivateLeagueCreate.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (s.toString().isEmpty() || s.toString().length() < 3) {
                                    leaguename.setError("minimum 3 characters");
                                    nameOk = false;

                                } else {
                                    leaguename.setError("Good", tickDone);
                                    nameOk = true;
                                }

                            /*
                            * if (lista.contains(conta1)) {
    System.out.println("Account found");
} else {
    System.out.println("Account not found");
}
                            * */


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
                        PrivateLeagueCreate.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (s.toString().isEmpty() || s.toString().length() < 3) {
                                    password.setError("minimum 3 characters");
                                    passwordOk = false;

                                } else {
                                    password.setError("Good", tickDone);
                                    passwordOk = true;
                                }
                            }
                        });
                    }
                }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask
            }
        });

        prize.addTextChangedListener(new TextWatcher() {
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
                        PrivateLeagueCreate.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    Integer.parseInt(s.toString().trim());
                                    prize.setError("Invalid input");
                                    prizeOk = false;
                                } catch (Exception e) {
                                    if (s.toString().isEmpty() || s.toString().length() < 3 || keywords.contains(s.toString().toLowerCase())) {
                                        prize.setError("Invalid input");
                                        prizeOk = false;
                                    } else {
                                        prize.setError("Good", tickDone);
                                        prizeOk = true;
                                    }
                                }
                            }
                        });
                    }
                }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask
            }
        });
    }

    public void restrictionList() {
        keywords.add("$");
        keywords.add("money");
        keywords.add("dollar");
        keywords.add("thousand");
        keywords.add("bucks");
        keywords.add("euro");
        keywords.add("rupee");
        keywords.add("rupiah");
        keywords.add("million");
        keywords.add("rial");
        keywords.add("ringgit");
        keywords.add("rupee");
        keywords.add("rupiah");
        keywords.add("cent");
        keywords.add("won");
        keywords.add("peso");
        keywords.add("baht");
        keywords.add("dong");
        keywords.add("bdt");
        keywords.add("USD");
        keywords.add("BND");
        keywords.add("CNY");
        keywords.add("AUD");
        keywords.add("EUR");
        keywords.add("HKD");
        keywords.add("INR");
        keywords.add("IDR");
        keywords.add("JPY");
        keywords.add("MYR");
        keywords.add("MMK");
        keywords.add("KPW");
        keywords.add("PKR");
        keywords.add("PHP");
        keywords.add("QAR");
        keywords.add("THB");
        keywords.add("USD");
        keywords.add("SGD");
        keywords.add("KRW");
        keywords.add("TWD");
        keywords.add("฿");
        keywords.add("VND");
        keywords.add("€");
        keywords.add("£");
        keywords.add("Rp");
        keywords.add("₩");
        keywords.add("¥");
    }

    public static void replace(List<String> strings) {
        ListIterator<String> iterator = strings.listIterator();
        while (iterator.hasNext()) {
            iterator.set(iterator.next().toLowerCase());
        }
    }
}