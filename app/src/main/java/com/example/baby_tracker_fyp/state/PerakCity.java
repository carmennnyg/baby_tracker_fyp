package com.example.baby_tracker_fyp.state;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.baby_tracker_fyp.R;
import com.example.baby_tracker_fyp.StateActivity;
import com.example.baby_tracker_fyp.perakCity.IpohMapsActivity;
import com.example.baby_tracker_fyp.perakCity.KamparMapsActivity;
import com.example.baby_tracker_fyp.perakCity.SungaiSiputMapsActivity;
import com.example.baby_tracker_fyp.perakCity.TaipingMapsActivity;


public class PerakCity extends AppCompatActivity {
    private ImageButton mCityBackIB1, mIpohIB, mTaipingIB, mSungaiSiputIB, mKamparIB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perak_city);

        mCityBackIB1 = (ImageButton) findViewById(R.id.cityBackIB1);
        mIpohIB = (ImageButton) findViewById(R.id.ipohIB);
        mTaipingIB = (ImageButton) findViewById(R.id.taipingIB);
        mSungaiSiputIB = (ImageButton) findViewById(R.id.sungaiSiputIB);
        mKamparIB = (ImageButton) findViewById(R.id.kamparIB);

        mCityBackIB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(PerakCity.this, StateActivity.class);
                startActivity(back);
            }
        });

        mIpohIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ipoh = new Intent(PerakCity.this, IpohMapsActivity.class);
                startActivity(ipoh);
            }
        });

        mTaipingIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent taiping = new Intent(PerakCity.this, TaipingMapsActivity.class);
                startActivity(taiping);
            }
        });

        mSungaiSiputIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss = new Intent(PerakCity.this, SungaiSiputMapsActivity.class);
                startActivity(ss);
            }
        });

        mKamparIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kampar = new Intent(PerakCity.this, KamparMapsActivity.class);
                startActivity(kampar);
            }
        });

    }
}