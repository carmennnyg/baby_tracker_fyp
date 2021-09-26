package com.example.baby_tracker_fyp.state;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.baby_tracker_fyp.sabahCity.KotaKinabaluMapsActivity;
import com.example.baby_tracker_fyp.sabahCity.KudatMapsActivity;
import com.example.baby_tracker_fyp.R;
import com.example.baby_tracker_fyp.sabahCity.SandakanMapsActivity;
import com.example.baby_tracker_fyp.StateActivity;
import com.example.baby_tracker_fyp.sabahCity.TawauMapsActivity;


public class SabahCity extends AppCompatActivity {
    private ImageButton mCityBackIB, mKKIB, mSandakanIB, mTawauIB, mKudatIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabah_city);

        mCityBackIB = (ImageButton) findViewById(R.id.cityBackIB);
        mKKIB = (ImageButton) findViewById(R.id.kkIB);
        mSandakanIB = (ImageButton) findViewById(R.id.sandakanIB);
        mTawauIB = (ImageButton) findViewById(R.id.tawauIB);
        mKudatIB = (ImageButton) findViewById(R.id.kudatIB);

        mCityBackIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SabahCity.this, StateActivity.class);
                startActivity(back);
            }
        });

        mKKIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent KK = new Intent(SabahCity.this, KotaKinabaluMapsActivity.class);
                startActivity(KK);
            }
        });

        mSandakanIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent S = new Intent(SabahCity.this, SandakanMapsActivity.class);
                startActivity(S);
            }
        });

        mTawauIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent T = new Intent(SabahCity.this, TawauMapsActivity.class);
                startActivity(T);
            }
        });

        mKudatIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent K = new Intent(SabahCity.this, KudatMapsActivity.class);
                startActivity(K);
            }
        });

    }
}