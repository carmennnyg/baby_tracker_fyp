package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SleepingActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {

    private Button mStart4Button, mSave5Button;
    private TextView mSleepingTimeMin;
    private EditText mDateAndTime;
    private NumberPicker mSleepingTime;
    private ImageButton mBack5IV;
    private FirebaseAuth mAuth;
    private String mDate;
    private String mTime;

    private int dayFinal, monthFinal, yearFinal;

    private DatabaseReference current_user_db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleeping);

        current_user_db = FirebaseDatabase.getInstance().getReference();

        mSave5Button = (Button) findViewById(R.id.save5Button);
        mStart4Button = (Button) findViewById(R.id.start4button);
        mDateAndTime = (EditText) findViewById(R.id.DateTimeSleeping);
        mBack5IV = (ImageButton) findViewById(R.id.back5Button);
        mSleepingTime = findViewById(R.id.sleepingPicker);
        mSleepingTimeMin = (TextView) findViewById(R.id.sleepTV);

        mSleepingTime.setMaxValue(60);
        mSleepingTime.setMinValue(0);
        mSleepingTime.setWrapSelectorWheel(true);

        //Functioning Number Picker
        mSleepingTime.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mSleepingTimeMin.setText(String.valueOf(newVal));
            }
        });

        mBack5IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SleepingActivity.this, HomeActivity.class);
                startActivity(back);
            }
        });

        mSave5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDateAndTime.getText().toString().equals("")) {
                    Toast.makeText(SleepingActivity.this, "Store Data Unsuccessfully!",
                            Toast.LENGTH_SHORT).show();

                } else {

                    String dateAndTime = mDateAndTime.getText().toString();
                    String sleepMin = mSleepingTimeMin.getText().toString();

                    // Store Data into the Database
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users")
                            .child(user_id).child("Sleep Duration").child("Time Stamp");

                    Map sleeping = new HashMap();

                    // Adding elements to the Map
                    // using standard add() method
                    sleeping.put("Sleep_Date_and_Time", dateAndTime);
                    sleeping.put("Sleep_Duration", sleepMin);

                    current_user_db.child("Latest").child("Sleep Duration").setValue(sleeping);

                    db.child(mDate).child(mTime).setValue(sleeping);

                    Toast.makeText(SleepingActivity.this, "Store Data Successfully!",
                            Toast.LENGTH_SHORT).show();

                    Intent sleep = new Intent(SleepingActivity.this, HomeActivity.class);
                    startActivity(sleep);
                }
            }
        });


        mStart4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SleepingActivity.this, SleepingActivity.this,
                        year, month, day);
                datePickerDialog.show();

            }
        });
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        yearFinal =year;
        monthFinal =month + 1;
        dayFinal =day;

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog =new TimePickerDialog(SleepingActivity.this, SleepingActivity.this,
                hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

        mDate = dayFinal + "-" + monthFinal + "-" + yearFinal;

    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        String updateTime = java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT).format(calendar.getTime());
        mDateAndTime.setText(dayFinal + "/"+ monthFinal + "/"+ yearFinal + " ("+ updateTime+")");

        mTime = " ("+ updateTime+")";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            startActivity(new Intent(this, HomeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}