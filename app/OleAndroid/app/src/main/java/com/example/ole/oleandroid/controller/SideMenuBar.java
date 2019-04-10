package com.example.ole.oleandroid.controller;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.LoginDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.controller.Leaderboard.Leaderboard;
import com.example.ole.oleandroid.controller.FAQ.FAQ;

import static com.example.ole.oleandroid.R.layout.sidemenuheader;

public class SideMenuBar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout mDrawerlayout;
    ActionBarDrawerToggle mToggle;
    CardView pubLeague;
    CardView privLeague;
    TextView sidebarusername;
    NavigationView sidemenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidemenubar);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Hi");
        sidemenu = findViewById(R.id.sidemenu);
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        sidebarusername = findViewById(R.id.sidebarusername);
        //sidebarusername.setText(UserDAO.getLoginUser().getName());

        //set sidebar name
        View headerLayout = sidemenu.getHeaderView(0);
        sidebarusername = headerLayout.findViewById(R.id.sidebarusername);
        sidebarusername.setText(UserDAO.getLoginUser().getUsername());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        pubLeague.setVisibility(View.GONE);
//        privLeague.setVisibility(View.GONE);
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.menu_list_profile:
                Intent intent1 = new Intent(SideMenuBar.this, Profile.class);
                startActivity(intent1);
                break;
            case R.id.menu_list_leagues:
                Intent intent2 = new Intent(SideMenuBar.this, HomeLeague.class);
                startActivity(intent2);
                break;
            case R.id.menu_list_faq:
                Intent intent4 = new Intent(SideMenuBar.this, FAQ.class);
                startActivity(intent4);
                break;
            case R.id.menu_list_logout:
                UserDAO.setLoginUser(null);
                SharedPreferences preferences = getSharedPreferences("logged", MODE_PRIVATE);
                preferences.edit().remove("username").apply(); //clear all stored data
                preferences.edit().remove("password").apply();
                preferences.edit().remove("login").apply();
                successfulAlertDialog();
                break;
            case R.id.about_app:
                Intent intent6 = new Intent(SideMenuBar.this, OnBoardingActivity.class);
                intent6.putExtra("from", "sideMenu");
                startActivity(intent6);
                break;
        }

        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.sidemenu);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void successfulAlertDialog() {
        final Dialog dialog = new Dialog(SideMenuBar.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.successful_pop_up);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        TextView close = dialog.findViewById(R.id.confirm);
        close.setText("Ok");
        close.setEnabled(true);

        TextView text = dialog.findViewById(R.id.successfulMessage);
        text.setText("Successfully Logged Out!");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Intent intent5 = new Intent(SideMenuBar.this, Login.class);
                startActivity(intent5);
            }
        });
        dialog.show();
    }
}
