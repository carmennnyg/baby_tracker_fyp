package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FeedingActivity extends AppCompatActivity {
    private Button mBreastFeedingButton, mBottleFeedingButton, mMealFeedingButton;
    private ImageButton mBackImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding);

        mBreastFeedingButton = (Button) findViewById(R.id.breastFButton);
        mBottleFeedingButton = (Button) findViewById(R.id.bottleFButton);
        mMealFeedingButton = (Button) findViewById(R.id.mealFButton);
        mBackImageButton = findViewById(R.id.backButton);

        mBreastFeedingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breastfeeding = new Intent(FeedingActivity.this, breastFeedingActivity.class);
                startActivity(breastfeeding);
            }
        });

        mBottleFeedingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bottlefeeding = new Intent(FeedingActivity.this, bottleFeedingActivity.class);
                startActivity(bottlefeeding);
            }
        });

        mMealFeedingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mealfeeding = new Intent(FeedingActivity.this, mealFeedingActivity.class);
                startActivity(mealfeeding);
            }
        });

        mBackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(FeedingActivity.this, HomeActivity.class);
                startActivity(back);
            }
        });

    }
}