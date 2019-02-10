package com.example.ole.oleandroid.controller.Leaderboard;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.controller.DAO.ScoreBoardDAO;
import com.example.ole.oleandroid.model.PrivateLeagueProfile;
import com.example.ole.oleandroid.model.PublicLeagueProfile;


import java.util.ArrayList;
import java.util.List;

public class Leaderboard extends SideMenuBar {

    ArrayList<PrivateLeagueProfile> privateLeagueProfiles = new ArrayList<>();
    TabLayout leaderboardFrame;
    ViewPager viewPager;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_leaderboard, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        viewPager = findViewById(R.id.viewPagerId);
        setupViewPager(viewPager);

        leaderboardFrame = findViewById(R.id.leaderboardFrame);
        leaderboardFrame.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
        privateLeagueProfiles = ScoreBoardDAO.getPrivateLeagueProfiles();

        adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new LeaderboardPublic(),"Public League");
        if (privateLeagueProfiles == null) {
            adapter.addFragment(new LeaderboardPrivateNo(), "Private League");
        } else {
            adapter.addFragment(new LeaderboardPrivate(), "Private League");
        }
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
