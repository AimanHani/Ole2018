package com.example.ole.oleandroid.controller;

import android.content.Intent;
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

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.controller.Leaderboard.Leaderboard;
import com.example.ole.oleandroid.controller.FAQ.FAQ;

public class SideMenuBar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout mDrawerlayout;
    ActionBarDrawerToggle mToggle;
    CardView pubLeague;
    CardView privLeague;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidemenubar);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Hi");

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();


//        pubLeague = findViewById(R.id.publicCard);
//        privLeague = findViewById(R.id.privateCard);
//        pubLeague.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
////                pubLeague.setVisibility(View.GONE);
////                privLeague.setVisibility(View.GONE);
//                Intent intent = new Intent(SideMenuBar.this, PublicLeagueList.class);
//                startActivity(intent);
//            }
//        });
//
//        privLeague.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
////                pubLeague.setVisibility(View.GONE);
////                privLeague.setVisibility(View.GONE);
//                Intent intent = new Intent(SideMenuBar.this, PrivateLeagueMain.class);
//                startActivity(intent);
//            }
//        });
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
            case R.id.menu_list_leaderboard:
                Intent intent3 = new Intent(SideMenuBar.this, Leaderboard.class);
                startActivity(intent3);
                break;
            case R.id.menu_list_faq:
                Intent intent4 = new Intent(SideMenuBar.this, FAQ.class);
                startActivity(intent4);
                break;
            case R.id.menu_list_logout:
                UserDAO.setLoginUser(null);
                Intent intent5 = new Intent(SideMenuBar.this, Login.class);
                startActivity(intent5);
                break;
        }

        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.sidemenu);
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
}
