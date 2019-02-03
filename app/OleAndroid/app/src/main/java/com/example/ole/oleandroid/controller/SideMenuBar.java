package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.pageController.FAQ;
import com.example.ole.oleandroid.pageController.FAQPrivate;
import com.example.ole.oleandroid.pageController.FAQPublic;

public class SideMenuBar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    CardView pubLeague;
    CardView privLeague;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidemenubar);
        pubLeague = findViewById(R.id.publicCard);
        privLeague = findViewById(R.id.privateCard);

        mDrawerlayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();

        pubLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SideMenuBar.this, PublicLeagueList.class);
                startActivity(intent);
            }
        });

        privLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SideMenuBar.this, PrivateLeagueHome.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_list_profile:
                Intent intent1 = new Intent(SideMenuBar.this, Profile.class);
                startActivity(intent1);
                break;
            case R.id.menu_list_leagues:
                Intent intent2 = new Intent(SideMenuBar.this, SideMenuBar.class);
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
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
