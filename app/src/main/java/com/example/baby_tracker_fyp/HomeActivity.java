package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {
    private Button mFeedingButton, mDiaperingButton, mSleepingButton, mHealthButton, mMapButton;
    private ImageButton mSummaryButton, mChartButton, mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFeedingButton = (Button) findViewById(R.id.feedingButton);
        mDiaperingButton = (Button) findViewById(R.id.diaperingButton);
        mSleepingButton = (Button) findViewById(R.id.sleepingButton);
        mHealthButton = (Button) findViewById(R.id.healthButton);
        mMapButton = (Button) findViewById(R.id.mapButton);
        mSummaryButton = (ImageButton) findViewById(R.id.summaryButton);
        mChartButton = (ImageButton) findViewById(R.id.chartButton);
        mLogoutButton = (ImageButton) findViewById(R.id.logoutButton);

        mFeedingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feeding = new Intent(HomeActivity.this, FeedingActivity.class);
                startActivity(feeding);
            }
        });

        mDiaperingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diapering = new Intent(HomeActivity.this, DiaperingActivity.class);
                startActivity(diapering);
            }
        });

        mSleepingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sleeping = new Intent(HomeActivity.this, SleepingActivity.class);
                startActivity(sleeping);
            }
        });

        mHealthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent health = new Intent(HomeActivity.this, HealthActivity.class);
                startActivity(health);
            }
        });

        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(HomeActivity.this, StateActivity.class);
                startActivity(map);
            }
        });

        mSummaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent summary = new Intent(HomeActivity.this, Summary1Activity.class);
                startActivity(summary);
            }
        });

        mChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chart = new Intent(HomeActivity.this, ChartActivity.class);
                startActivity(chart);
            }
        });


        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(HomeActivity.this, LogoutActivity.class);
                startActivity(logout);
            }
        });
    }
}