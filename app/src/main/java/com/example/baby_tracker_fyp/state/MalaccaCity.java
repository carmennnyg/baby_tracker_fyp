package com.example.baby_tracker_fyp.state;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.baby_tracker_fyp.malaccaCity.AlorGajahMapsActivity;
import com.example.baby_tracker_fyp.malaccaCity.AyerKerohMapsActivity;
import com.example.baby_tracker_fyp.malaccaCity.MalaccaMapsActivity;
import com.example.baby_tracker_fyp.malaccaCity.MerlimauMapsActivity;
import com.example.baby_tracker_fyp.R;
import com.example.baby_tracker_fyp.StateActivity;


public class MalaccaCity extends AppCompatActivity {
    private ImageButton mCityBackIB, mAlorGajahIB, mMalaccaIB, mAyerKeroh, mMerlimauIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malacca_city);

        mCityBackIB = (ImageButton) findViewById(R.id.cityBackIB1);
        mAlorGajahIB = (ImageButton) findViewById(R.id.alorGajahIB);
        mMalaccaIB = (ImageButton) findViewById(R.id.melakaIB);
        mAyerKeroh = (ImageButton) findViewById(R.id.ayerKerohIB);
        mMerlimauIB = (ImageButton) findViewById(R.id.merlimauIB);

        mCityBackIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(MalaccaCity.this, StateActivity.class);
                startActivity(back);
            }
        });

        mAlorGajahIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ag = new Intent(MalaccaCity.this, AlorGajahMapsActivity.class);
                startActivity(ag);
            }
        });

        mMalaccaIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent melaka = new Intent(MalaccaCity.this, MalaccaMapsActivity.class);
                startActivity(melaka);
            }
        });

        mAyerKeroh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ak = new Intent(MalaccaCity.this, AyerKerohMapsActivity.class);
                startActivity(ak);
            }
        });

        mMerlimauIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent merlimau = new Intent(MalaccaCity.this, MerlimauMapsActivity.class);
                startActivity(merlimau);
            }
        });

    }
}