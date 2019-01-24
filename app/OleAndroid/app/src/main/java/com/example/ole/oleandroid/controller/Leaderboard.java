package com.example.ole.oleandroid.controller;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Adapter;

import com.example.ole.oleandroid.R;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard extends AppCompatActivity {

    TabLayout leaderboardFrame;
    ViewPager viewPager;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        viewPager = findViewById(R.id.viewPagerId);
        setupViewPager(viewPager);

        leaderboardFrame = findViewById(R.id.leaderboardFrame);
        leaderboardFrame.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if(!Util.isNetworkAvailable(getApplicationContext())) {
            Intent toNoConnectionActivity = new Intent(CurrentEvents.this, NoConnection.class);
            startActivity(toNoConnectionActivity);
            return;
        }*/

        // reload the fragments, in case of the deletion of event by user
        if(viewPager != null) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:"
                    + R.id.viewPagerId + ":" + viewPager.getCurrentItem());
            if (fragment != null && viewPager.getCurrentItem() == 0) {
                setupViewPager(viewPager);
            } else {
                int position = viewPager.getCurrentItem();

                setupViewPager(viewPager);
                viewPager.setCurrentItem(position);
            }
        }
    }

    private void setupViewPager(ViewPager viewPager){
        adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new PublicLeaderboard(),"Public League");
        adapter.addFragment(new PrivateLeaderboard(), "Private League");
        viewPager.setAdapter(adapter);
    }

    //ViewPagerAdapter class
    class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public int getItemPosition(Object object) { return POSITION_NONE; }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
