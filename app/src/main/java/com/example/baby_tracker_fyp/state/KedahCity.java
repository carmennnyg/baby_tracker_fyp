package com.example.baby_tracker_fyp.state;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.baby_tracker_fyp.kedahCity.AlorSetarMapsActivity;
import com.example.baby_tracker_fyp.kedahCity.AyerHitamMapsActivity;
import com.example.baby_tracker_fyp.kedahCity.BalingMapsActivity;
import com.example.baby_tracker_fyp.kedahCity.GurunMapsActivity;
import com.example.baby_tracker_fyp.R;
import com.example.baby_tracker_fyp.StateActivity;


public class KedahCity extends AppCompatActivity {
    private ImageButton mCityBackIB, mAlorSetarIB, mAyerHitamIB, mGurunIB, mBalingIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kedah_city);

        mCityBackIB = (ImageButton) findViewById(R.id.cityBackIB1);
        mAlorSetarIB = (ImageButton) findViewById(R.id.alorSetarIB);
        mAyerHitamIB = (ImageButton) findViewById(R.id.ayerHitamIB);
        mGurunIB = (ImageButton) findViewById(R.id.gurunIB);
        mBalingIB = (ImageButton) findViewById(R.id.balingIB);

        mCityBackIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(KedahCity.this, StateActivity.class);
                startActivity(back);
            }
        });

        mAlorSetarIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent as = new Intent(KedahCity.this, AlorSetarMapsActivity.class);
                startActivity(as);
            }
        });

        mAyerHitamIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ah = new Intent(KedahCity.this, AyerHitamMapsActivity.class);
                startActivity(ah);
            }
        });

        mGurunIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gurun = new Intent(KedahCity.this, GurunMapsActivity.class);
                startActivity(gurun);
            }
        });

        mBalingIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent baling = new Intent(KedahCity.this, BalingMapsActivity.class);
                startActivity(baling);
            }
        });

    }
}