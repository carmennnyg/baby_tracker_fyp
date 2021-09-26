package com.example.baby_tracker_fyp.state;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.baby_tracker_fyp.R;
import com.example.baby_tracker_fyp.StateActivity;
import com.example.baby_tracker_fyp.penangCity.BayanLepasMapsActivity;
import com.example.baby_tracker_fyp.penangCity.ButterworthMapsActivity;
import com.example.baby_tracker_fyp.penangCity.GeorgetownMapsActivity;
import com.example.baby_tracker_fyp.penangCity.NibongTebalMapsActivity;

public class PenangCity extends AppCompatActivity {
    private ImageButton mCityBackIB, mGeorgeTownIB, mButterworthIB, mBayanLepasIB, mNibongTebalIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penang_city);

        mCityBackIB = (ImageButton) findViewById(R.id.cityBackIB);
        mGeorgeTownIB = (ImageButton) findViewById(R.id.georgetownIB);
        mButterworthIB = (ImageButton) findViewById(R.id.butterworthIB);
        mBayanLepasIB = (ImageButton) findViewById(R.id.bayanLepasIB);
        mNibongTebalIB = (ImageButton) findViewById(R.id.nibongTebalIB);

        mCityBackIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(PenangCity.this, StateActivity.class);
                startActivity(back);
            }
        });

        mGeorgeTownIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GT = new Intent(PenangCity.this, GeorgetownMapsActivity.class);
                startActivity(GT);
            }
        });

        mButterworthIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BW = new Intent(PenangCity.this, ButterworthMapsActivity.class);
                startActivity(BW);
            }
        });

        mBayanLepasIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BL = new Intent(PenangCity.this, BayanLepasMapsActivity.class);
                startActivity(BL);
            }
        });

        mNibongTebalIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NT = new Intent(PenangCity.this, NibongTebalMapsActivity.class);
                startActivity(NT);
            }
        });

    }
}

