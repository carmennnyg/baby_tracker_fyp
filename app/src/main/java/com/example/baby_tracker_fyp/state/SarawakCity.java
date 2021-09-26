package com.example.baby_tracker_fyp.state;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.baby_tracker_fyp.sarawakCity.KuchingMapsActivity;
import com.example.baby_tracker_fyp.sarawakCity.MiriMapsActivity;
import com.example.baby_tracker_fyp.R;
import com.example.baby_tracker_fyp.sarawakCity.SibuMapsActivity;
import com.example.baby_tracker_fyp.sarawakCity.SriAmanMapsActivity;
import com.example.baby_tracker_fyp.StateActivity;


public class SarawakCity extends AppCompatActivity {
    private ImageButton mCityBackIB, mKuchingIB, mMiriIB, mSriAmanIB, mSibuIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarawak_city);

        mCityBackIB = (ImageButton) findViewById(R.id.cityBackIB);
        mKuchingIB = (ImageButton) findViewById(R.id.kuchingIB);
        mMiriIB = (ImageButton) findViewById(R.id.miriIB);
        mSriAmanIB = (ImageButton) findViewById(R.id.sriAmanIB);
        mSibuIB = (ImageButton) findViewById(R.id.sibuIB);

        mCityBackIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SarawakCity.this, StateActivity.class);
                startActivity(back);
            }
        });

        mKuchingIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kuching = new Intent(SarawakCity.this, KuchingMapsActivity.class);
                startActivity(kuching);
            }
        });

        mMiriIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miri = new Intent(SarawakCity.this, MiriMapsActivity.class);
                startActivity(miri);
            }
        });

        mSriAmanIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sriAman = new Intent(SarawakCity.this, SriAmanMapsActivity.class);
                startActivity(sriAman);
            }
        });

        mSibuIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sibu = new Intent(SarawakCity.this, SibuMapsActivity.class);
                startActivity(sibu);
            }
        });

    }
}