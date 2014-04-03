package com.mockup.mockup_app;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Button configButton;
    Button statsButton;
    private ScreenSlidePagerAdapter mPagerAdapter;
    ViewPager mPager;
    private static final int NUM_PAGES = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        // Instantiate a ViewPager and a PagerAdapter.
        //TODO set vars according conventions
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        // Hide action bar in order to stretch the image on full screen.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Screen image for main screen.
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setImageResource(R.drawable.image1);

        // Create settings button and ability to move to settings screen from main screen.
        configButton = (Button) findViewById(R.id.config_button);
        View.OnClickListener configHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent configIntent = new Intent(MainActivity.this, ConfigActivity.class);
                startActivity(configIntent);
            }
        };
        configButton.setOnClickListener(configHandler);

        // Create stats button and ability to move to stats screen from main screen.
        statsButton = (Button) findViewById(R.id.stats_button);
        View.OnClickListener statsHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statsIntent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(statsIntent);
            }
        };
        statsButton.setOnClickListener(statsHandler);

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return new ScreenSlidePageFragment(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }
    }

    public class ScreenSlidePageFragment extends Fragment {

        int layoutId;

        //ctor
        public ScreenSlidePageFragment(int index) {
            switch (index) {
                case 0:
                    layoutId = R.layout.activity_main;
                    break;
                case 1:
                    layoutId = R.layout.activity_stats;
                    break;
                case 2:
                    layoutId = R.layout.activity_config;
                    break;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final String ARG_OBJECT = "object";

            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    layoutId, container, false);
            try {

                Bundle args = getArguments();
                assert rootView != null;
                configButton = (Button) findViewById(R.id.config_button);

                ViewGroup.OnClickListener handler = new ViewGroup.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent configIntent = new Intent(MainActivity.this, ConfigActivity.class);
                        startActivity(configIntent);
                    }
                };

                configButton.setOnClickListener(handler);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.print("Reuven: " + e);
            }

            return rootView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
