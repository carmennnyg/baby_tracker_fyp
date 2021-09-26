package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class HealthActivity extends AppCompatActivity {
    private Button mTemperatureButton, mMedicationButton, mSymptomsButton;
    private ImageButton mBack7Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        mTemperatureButton = (Button) findViewById(R.id.temperatureButton);
        mMedicationButton = (Button) findViewById(R.id.medicationButton);
        mSymptomsButton = (Button) findViewById(R.id.symptomsButton);
        mBack7Button = (ImageButton) findViewById(R.id.back7Button);

        mTemperatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temperature = new Intent(HealthActivity.this, TemperatureActivity.class);
                startActivity(temperature);
            }
        });

        mMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medication = new Intent(HealthActivity.this, MedicationActivity.class);
                startActivity(medication);
            }
        });


        mSymptomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent symptoms = new Intent(HealthActivity.this, SymptomsActivity.class);
                startActivity(symptoms);
            }
        });

        mBack7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(HealthActivity.this, HomeActivity.class);
                startActivity(back);
            }
        });

    }


}
