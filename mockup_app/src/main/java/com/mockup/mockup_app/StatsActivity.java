package com.mockup.mockup_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class StatsActivity extends ActionBarActivity {

    Button homeButton;
    Button configButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_stats, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        // Hide action bar in order to stretch the image on full screen.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Screen image for stats screen.
        ImageView statsView = (ImageView) findViewById(R.id.statsView);
        statsView.setImageResource(R.drawable.stats);

        // Create Home button and ability to move to home screen from stats screen.
        homeButton = (Button) findViewById(R.id.home_button);
        View.OnClickListener mainHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(StatsActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        };
        homeButton.setOnClickListener(mainHandler);

        // Create settings button and ability to move to settings screen from stats screen.
        configButton = (Button) findViewById(R.id.config_button);
        View.OnClickListener configHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent configIntent = new Intent(StatsActivity.this, ConfigActivity.class);
                startActivity(configIntent);
            }
        };
        configButton.setOnClickListener(configHandler);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stats, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_stats, container, false);
            return rootView;
        }
    }

}
