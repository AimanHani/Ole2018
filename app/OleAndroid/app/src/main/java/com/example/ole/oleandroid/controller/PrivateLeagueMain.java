package com.example.ole.oleandroid.controller;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.design.widget.TabLayout;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;

        import com.example.ole.oleandroid.R;
        import com.example.ole.oleandroid.model.PublicLeague;

        import java.util.ArrayList;
        import java.util.List;

public class PrivateLeagueMain extends AppCompatActivity {

    TabLayout privateLeagueTabLayout;
    ViewPager viewPager;
    Adapter adapter;
    //int logId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_main);

        //Bundle bundle = getIntent().getExtras();
        //logId = bundle.getInt("logId");

        viewPager = findViewById(R.id.viewpagerId);
        setupViewPager(viewPager);

        privateLeagueTabLayout = findViewById(R.id.privateLeagueTabLayout);
        privateLeagueTabLayout.setupWithViewPager(viewPager);
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
        adapter.addFragment(new Mine(),"Mine");
        // upcomingMatches now has a logId which u can set (so i can pass this logId on. logid is needed for specials list
        //UpcomingMatches upcomingMatches = new UpcomingMatches();
        //upcomingMatches.setLogId(logId);
        adapter.addFragment(new Discover(), "Discover");

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
