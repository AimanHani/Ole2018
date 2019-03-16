package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.MatchesTabs;
import com.example.ole.oleandroid.controller.PublicLeague.PastMatchesFragment;
import com.example.ole.oleandroid.controller.PublicLeague.UpcomingMatchesFragment;
import com.example.ole.oleandroid.controller.SideMenuBar;
import java.util.ArrayList;
import java.util.List;

public class PrivateLeagueMatchesMain extends SideMenuBar {
    TabLayout leagueTabLayout;
    ViewPager viewPager;
    Adapter adapter;
    int logId;
    int leagueId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_matches_main, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        Bundle bundle = getIntent().getExtras();
        logId = bundle.getInt("logId");
        leagueId = bundle.getInt("leagueId");

        viewPager = findViewById(R.id.viewpagerId);
        setupViewPager(viewPager);

        leagueTabLayout = findViewById(R.id.leagueTabLayout);
        leagueTabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // reload the fragments, in case of the deletion of event by user
        if(viewPager != null) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:"
                    + R.id.viewpagerId + ":" + viewPager.getCurrentItem());
            if (fragment != null && viewPager.getCurrentItem() == 0) {
                setupViewPager(viewPager);
            } else {
                int position = viewPager.getCurrentItem();
                setupViewPager(viewPager);
                viewPager.setCurrentItem(position);
            }
        }
    }

    //add the child fragments
    private void setupViewPager(ViewPager viewPager){
        adapter = new Adapter(getSupportFragmentManager());
        // upcomingMatches now has a logId which u can set (so i can pass this logId on. logid is needed for specials list
        PrivateLeagueUpcomingMatchesList upcomingMatches = new PrivateLeagueUpcomingMatchesList();
        PrivateLeagueUpcomingMatchesList.setLogId(logId);
        PrivateLeagueUpcomingMatchesList.setLeagueId(leagueId);
        System.out.println(leagueId + " " + logId);
        adapter.addFragment(upcomingMatches, "Upcoming");

        PrivateLeaguePastMatchesList pastMatches = new PrivateLeaguePastMatchesList();
        PrivateLeaguePastMatchesList.setLogId(logId);
        PrivateLeaguePastMatchesList.setLeagueId(leagueId);
        adapter.addFragment(new PrivateLeaguePastMatchesList(),"Past");
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
