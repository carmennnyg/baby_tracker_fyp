package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MedicationActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {

    private Button mStart9Button, mSave9Button;
    private EditText mDateAndTime, mMedDetails, mMedNotes;
    private FirebaseAuth mAuth;
    private String mDate;
    private String mTime;
    private ImageButton mBack9TV;

    private int dayFinal, monthFinal, yearFinal;

    private DatabaseReference current_user_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        current_user_db = FirebaseDatabase.getInstance().getReference();

        mSave9Button =(Button) findViewById(R.id.save9Button);
        mStart9Button =(Button) findViewById(R.id.start9button);
        mDateAndTime =(EditText) findViewById(R.id.DateTimeMedication);
        mMedDetails =(EditText) findViewById(R.id.medDetailsET);
        mMedNotes = (EditText) findViewById(R.id.med_notes);
        mBack9TV = (ImageButton) findViewById(R.id.back9Button);

        mBack9TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(MedicationActivity.this, HealthActivity.class);
                startActivity(back);
            }
        });

        mSave9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDateAndTime.getText().toString().equals("")) {
                    Toast.makeText(MedicationActivity.this, "Store Data Unsuccessfully!",
                            Toast.LENGTH_SHORT).show();

                } else {

                    String medDetails = mMedDetails.getText().toString();
                    String medNotes = mMedNotes.getText().toString();
                    String dateAndTime = mDateAndTime.getText().toString();

                    // Store Data into the Database
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users")
                            .child(user_id).child("Health").child("Medication").child("Time Stamp");

                    Map medication = new HashMap();

                    // Adding elements to the Map
                    // using standard add() method
                    medication.put("Medication_Date_and_Time", dateAndTime);
                    medication.put("Med_Details", medDetails);
                    medication.put("Med_Notes", medNotes);

                    current_user_db.child("Latest").child("Medication").setValue(medication);

                    db.child(mDate).child(mTime).setValue(medication);

                    Toast.makeText(MedicationActivity.this, "Store Data Successfully!",
                            Toast.LENGTH_SHORT).show();

                    Intent health = new Intent(MedicationActivity.this, HealthActivity.class);
                    startActivity(health);
                }
            }
        });

        mStart9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day =c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MedicationActivity.this, MedicationActivity.this,
                        year,month,day);
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

        TimePickerDialog timePickerDialog =new TimePickerDialog(MedicationActivity.this, MedicationActivity.this,
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
            startActivity(new Intent(this, HealthActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}