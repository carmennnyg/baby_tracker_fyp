package com.example.baby_tracker_fyp.state;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.baby_tracker_fyp.selangorCity.CyberjayaMapsActivity;
import com.example.baby_tracker_fyp.selangorCity.KlangMapsActivity;
import com.example.baby_tracker_fyp.R;
import com.example.baby_tracker_fyp.selangorCity.ShahAlamMapsActivity;
import com.example.baby_tracker_fyp.StateActivity;
import com.example.baby_tracker_fyp.selangorCity.SubangJayaMapsActivity;


public class SelangorCity extends AppCompatActivity {
    private ImageButton mCityBackIB2, mKlangIB, mShahAlamIB, mCyberjayaIB, mSubangJayaIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selangor_city);

        mCityBackIB2 = (ImageButton) findViewById(R.id.cityBackIB2);
        mKlangIB = (ImageButton) findViewById(R.id.klangIB);
        mShahAlamIB = (ImageButton) findViewById(R.id.shahAlamIB);
        mCyberjayaIB = (ImageButton) findViewById(R.id.cyberjayaIB);
        mSubangJayaIB = (ImageButton) findViewById(R.id.subangJayaIB);

        mCityBackIB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SelangorCity.this, StateActivity.class);
                startActivity(back);
            }
        });

        mKlangIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent klang = new Intent(SelangorCity.this, KlangMapsActivity.class);
                startActivity(klang);
            }
        });

        mShahAlamIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sa = new Intent(SelangorCity.this, ShahAlamMapsActivity.class);
                startActivity(sa);
            }
        });

        mCyberjayaIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cyber = new Intent(SelangorCity.this, CyberjayaMapsActivity.class);
                startActivity(cyber);
            }
        });

        mSubangJayaIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subang = new Intent(SelangorCity.this, SubangJayaMapsActivity.class);
                startActivity(subang);
            }
        });
    }
}