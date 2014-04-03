package com.mockup.mockup_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

public class ConfigActivity extends ActionBarActivity {

    Button homeButton;
    Button statsButton;

    private SeekBar seekBar;
    private TextView textView;
    int progress = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_config, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        // Hide action bar in order to stretch the image on full screen.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Screen image for settings screen.
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.settings);

        /*
        Create Home button and ability to move to home screen from settings screen.
         */
        homeButton = (Button) findViewById(R.id.home_button);
        View.OnClickListener mainHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ConfigActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        };
        homeButton.setOnClickListener(mainHandler);

        /*
        Create stats button and ability to move to stats screen from settings screen.
         */
        statsButton = (Button) findViewById(R.id.stats_button);
        View.OnClickListener statsHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statsIntent = new Intent(ConfigActivity.this, StatsActivity.class);
                startActivity(statsIntent);
            }
        };
        statsButton.setOnClickListener(statsHandler);

        // Seek bar
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView = (TextView) findViewById(R.id.textView1);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
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
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ConfigActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("amountShared", progress + "");
                        editor.commit();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.config, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_config, container, false);
            return rootView;
        }
    }

}
