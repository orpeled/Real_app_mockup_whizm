package com.mockup.mockup_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    Button configButton;
    Button statsButton;

    private SeekBar seekBar;
    private TextView textView;
    int progress = 0;

    private ScreenSlidePagerAdapter pagerAdapter;
    ViewPager pager;
    private static final int NUM_PAGES = 4;


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
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

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
                case 3:
                    layoutId = R.layout.activity_add;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    layoutId, container, false);
            // Main
            if (layoutId == R.layout.activity_main) {
                Button btnConfig;
                Button btnStats;
                Button btnAdd;

                btnConfig = (Button)rootView.findViewById(R.id.config_button);
                btnStats = (Button)rootView.findViewById(R.id.stats_button);
                btnAdd = (Button)rootView.findViewById(R.id.add_button);

                // Config from home
                View.OnClickListener configHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentConfig();
                    }
                };
                btnConfig.setOnClickListener(configHandler);

                // stats from home
                View.OnClickListener statsHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentStats();
                    }
                };
                btnStats.setOnClickListener(statsHandler);

                // add from home
                View.OnClickListener addHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentAdd();
                    }
                };
                btnAdd.setOnClickListener(addHandler);

            }
            if (layoutId == R.layout.activity_stats) {
                Button btnConfig;
                Button btnHome;
                Button btnAdd;


                btnConfig = (Button)rootView.findViewById(R.id.config_button);
                btnHome = (Button)rootView.findViewById(R.id.home_button);
                btnAdd = (Button)rootView.findViewById(R.id.add_button);


                // Config from stats
                View.OnClickListener configHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentConfig();
                    }
                };
                btnConfig.setOnClickListener(configHandler);

                // Home from stats
                View.OnClickListener homeHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentMain();
                    }
                };
                btnHome.setOnClickListener(homeHandler);

                // add from stats
                View.OnClickListener addHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentAdd();
                    }
                };
                btnAdd.setOnClickListener(addHandler);
            }
            if (layoutId == R.layout.activity_config) {
                Button btnStats;
                Button btnHome;
                Button btnAdd;


                // Stats from config
                btnStats = (Button)rootView.findViewById(R.id.stats_button);
                View.OnClickListener statsHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentStats();
                    }
                };
                btnStats.setOnClickListener(statsHandler);

                // Home form Config
                btnHome = (Button)rootView.findViewById(R.id.home_button);
                View.OnClickListener homeHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentMain();
                    }
                };
                btnHome.setOnClickListener(homeHandler);

                // add from stats
                btnAdd = (Button)rootView.findViewById(R.id.add_button);
                View.OnClickListener addHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentAdd();
                    }
                };
                btnAdd.setOnClickListener(addHandler);


                seekBar = (SeekBar) rootView.findViewById(R.id.seekBar1);
                textView = (TextView) rootView.findViewById(R.id.textView1);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                String amountShared = preferences.getString("amountShared","");
                if(amountShared.equalsIgnoreCase("")) {
                    amountShared = progress + "";  /* Edit the value here*/
                }
                // Initialize the textview with '0'
                seekBar.setProgress(Integer.parseInt(amountShared));
                textView.setText(amountShared + "MB");
                seekBar.setOnSeekBarChangeListener(
                        new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar,
                                                          int progressValue, boolean fromUser) {
                                progress = progressValue;
                                seekBar.setProgress(progress);
                                textView.setText(progress + "MB");
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {
                                // Do something here,
                                //if you want to do anything at the start of
                                // touching the seekbar
                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                // Display the value in textview
                                //textView.setText(progress + "MB");
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("amountShared", progress + "");
                                editor.commit();
                            }
                        });


            }
            // Main
            if (layoutId == R.layout.activity_add) {
                Button btnConfig;
                Button btnStats;
                Button btnHome;

                btnConfig = (Button)rootView.findViewById(R.id.config_button);
                btnStats = (Button)rootView.findViewById(R.id.stats_button);
                btnHome = (Button)rootView.findViewById(R.id.home_button);


                // Config from add
                View.OnClickListener configHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentConfig();
                    }
                };
                btnConfig.setOnClickListener(configHandler);

                // Home form add
                View.OnClickListener homeHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentMain();
                    }
                };
                btnHome.setOnClickListener(homeHandler);

                // stats from add
                View.OnClickListener statsHandler = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).switchToFragmentStats();
                    }
                };
                btnStats.setOnClickListener(statsHandler);

            }
            return rootView;
        }
    }

    public void switchToFragmentMain(){
        pager.setCurrentItem(0);
    }

    public void switchToFragmentStats(){
        pager.setCurrentItem(1);
    }

    public void switchToFragmentConfig(){
        pager.setCurrentItem(2);
    }

    public void switchToFragmentAdd(){
        pager.setCurrentItem(3);
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
