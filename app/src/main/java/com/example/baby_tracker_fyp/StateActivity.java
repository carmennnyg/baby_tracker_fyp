package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.baby_tracker_fyp.state.KedahCity;
import com.example.baby_tracker_fyp.state.MalaccaCity;
import com.example.baby_tracker_fyp.state.PenangCity;
import com.example.baby_tracker_fyp.state.PerakCity;
import com.example.baby_tracker_fyp.state.SabahCity;
import com.example.baby_tracker_fyp.state.SarawakCity;
import com.example.baby_tracker_fyp.state.SelangorCity;

public class StateActivity extends AppCompatActivity {
    private ImageButton mStateBackIB, mPenangIB, mPerakIB, mSelangorIB, mSabahIB,
            mSarawakIB, mKedahIB, mMalaccaIB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        mStateBackIB = (ImageButton) findViewById(R.id.stateBackIB);
        mPenangIB = (ImageButton) findViewById(R.id.penangIB);
        mPerakIB = (ImageButton) findViewById(R.id.perakIB);
        mSelangorIB = (ImageButton) findViewById(R.id.selangorIB);
        mSabahIB = (ImageButton) findViewById(R.id.sabahIB);
        mSarawakIB = (ImageButton) findViewById(R.id.sarawakIB);
        mKedahIB = (ImageButton) findViewById(R.id.kedahIB);
        mMalaccaIB = (ImageButton) findViewById(R.id.malaccaIB);

        mStateBackIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(StateActivity.this, HomeActivity.class);
                startActivity(back);
            }
        });

        mPenangIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent penang = new Intent(StateActivity.this, PenangCity.class);
                startActivity(penang);
            }
        });

        mPerakIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perak = new Intent(StateActivity.this, PerakCity.class);
                startActivity(perak);
            }
        });

        mSelangorIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selangor = new Intent(StateActivity.this, SelangorCity.class);
                startActivity(selangor);
            }
        });

        mSabahIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sabah = new Intent(StateActivity.this, SabahCity.class);
                startActivity(sabah);
            }
        });

        mSarawakIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sarawak = new Intent(StateActivity.this, SarawakCity.class);
                startActivity(sarawak);
            }
        });

        mKedahIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kedah = new Intent(StateActivity.this, KedahCity.class);
                startActivity(kedah);
            }
        });

        mMalaccaIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent malacca = new Intent(StateActivity.this, MalaccaCity.class);
                startActivity(malacca);
            }
        });

    }
}