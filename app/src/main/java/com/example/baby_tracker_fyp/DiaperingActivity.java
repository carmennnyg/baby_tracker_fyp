package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

public class DiaperingActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Button mStart3Button, mSave4Button;
    private EditText mDateAndTime, mDiaperingNotes;
    private Spinner mDiaperStatusSpinner;
    private FirebaseAuth mAuth;
    private ImageButton mBack3TV;
    private String mDate;
    private String mTime;

    private int dayFinal, monthFinal, yearFinal;

    private DatabaseReference current_user_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diapering);

        current_user_db = FirebaseDatabase.getInstance().getReference();

        mSave4Button =(Button) findViewById(R.id.save4Button);
        mStart3Button =(Button) findViewById(R.id.start3button);
        mDateAndTime =(EditText) findViewById(R.id.DateTimeDiapering);
        mDiaperStatusSpinner = (Spinner) findViewById(R.id.diaperSpinner);
        mDiaperingNotes = (EditText) findViewById(R.id.diapering_notes);
        mBack3TV = (ImageButton) findViewById(R.id.back3Button);

        mBack3TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(DiaperingActivity.this, HomeActivity.class);
                startActivity(back);
            }
        });

        mSave4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDateAndTime.getText().toString().equals("")) {
                    Toast.makeText(DiaperingActivity.this, "Store Data Unsuccessfully!",
                            Toast.LENGTH_SHORT).show();

                } else {

                    String dateAndTime = mDateAndTime.getText().toString();
                    String bfNotes = mDiaperingNotes.getText().toString();
                    String diaperStatus = mDiaperStatusSpinner.getSelectedItem().toString();

                    //spinner for diaper status
                    mDiaperStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String diaper_status = adapterView.getItemAtPosition(i).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    //Store Data into the Database
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users")
                            .child(user_id).child("Diaper Status").child("Time Stamp");

                    Map diapering = new HashMap();

                    // Adding elements to the Map
                    // using standard add() method
                    diapering.put("Diaper_Date_and_Time", dateAndTime);
                    diapering.put("Diaper_Status", diaperStatus);
                    diapering.put("Diaper_Note", bfNotes);

                    current_user_db.child("Latest").child("Diaper Status").setValue(diapering);

                    db.child(mDate).child(mTime).setValue(diapering);

                    Toast.makeText(DiaperingActivity.this, "Store Data Successfully!",
                            Toast.LENGTH_SHORT).show();

                    Intent diaper = new Intent(DiaperingActivity.this, HomeActivity.class);
                    startActivity(diaper);

                }
            }

        });

        mStart3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day =c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(DiaperingActivity.this, DiaperingActivity.this,
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

        TimePickerDialog timePickerDialog =new TimePickerDialog(DiaperingActivity.this, DiaperingActivity.this,
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