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
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class breastFeedingActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Button mStartButton, mSave1Button;
    private EditText mDateandTime, mBreastFeedingNotes;
    private Spinner leftBreastFeedSpinner, rightBreastFeedSpinner;
    private ImageButton mBack2TV;
    private int dayFinal, monthFinal, yearFinal;
    private FirebaseAuth mAuth;
    private String mDate;
    private String mTime;

    private DatabaseReference current_user_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breast_feeding);

        current_user_db = FirebaseDatabase.getInstance().getReference();

        mSave1Button =(Button) findViewById(R.id.save1Button);
        mStartButton =(Button) findViewById(R.id.startbutton);
        mDateandTime =(EditText) findViewById(R.id.DateTimeBreastFeeding);
        leftBreastFeedSpinner = (Spinner) findViewById(R.id.left_breast_feed_spinner);
        rightBreastFeedSpinner = (Spinner) findViewById(R.id.right_breast_feed_spinner);
        mBreastFeedingNotes = (EditText) findViewById(R.id.breast_feeding_notes);
        mBack2TV = (ImageButton) findViewById(R.id.back2Button);

        mBack2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(breastFeedingActivity.this, FeedingActivity.class);
                startActivity(back);
            }
        });

        mSave1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDateandTime.getText().toString().equals("")) {
                    Toast.makeText(breastFeedingActivity.this, "Store Data Unsuccessfully!",
                            Toast.LENGTH_SHORT).show();

                } else {

                    String dateAndTime = mDateandTime.getText().toString();
                    String bfNotes = mBreastFeedingNotes.getText().toString();
                    String leftBreastFeedTime = leftBreastFeedSpinner.getSelectedItem().toString();
                    String rightBreastFeedTime = rightBreastFeedSpinner.getSelectedItem().toString();

                    //spinner for left breast feeding
                    leftBreastFeedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String left_time = adapterView.getItemAtPosition(i).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    //spinner for right breast feeding
                    rightBreastFeedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String right_time = adapterView.getItemAtPosition(i).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    // Save data into the database
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users")
                            .child(user_id).child("Feeding").child("Breast Feeding").child("Time Stamp");

                    Map breastfeeding = new HashMap();

                    // Adding elements to the Map
                    // using standard add() method
                    breastfeeding.put("Breast_Feeding_Date_and_Time", dateAndTime);
                    breastfeeding.put("Left_Breast_Feeding_Time", leftBreastFeedTime);
                    breastfeeding.put("Right_Breast_Feeding_Time", rightBreastFeedTime);
                    breastfeeding.put("Breast_Feeding_Note", bfNotes);

                    current_user_db.child("Latest").child("Breast Feeding").setValue(breastfeeding);

                    db.child(mDate).child(mTime).setValue(breastfeeding);

                    Toast.makeText(breastFeedingActivity.this, "Store Data Successfully!",
                            Toast.LENGTH_SHORT).show();

                    Intent feeding = new Intent(breastFeedingActivity.this, FeedingActivity.class);
                    startActivity(feeding);
                }
            }
        });

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day =c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(breastFeedingActivity.this, breastFeedingActivity.this,
                        year,month,day);
                datePickerDialog.show();


            }
        });
    }

    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        yearFinal =year;
        monthFinal =month + 1;
        dayFinal =day;

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog =new TimePickerDialog(breastFeedingActivity.this, breastFeedingActivity.this,
                hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

        mDate = dayFinal + "-" + monthFinal + "-" + yearFinal;

    }

    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        String updateTime = java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT).format(calendar.getTime());
        mDateandTime.setText(dayFinal + "/"+ monthFinal + "/"+ yearFinal + " ("+ updateTime+")");

        mTime = " ("+ updateTime+")";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            startActivity(new Intent(this, FeedingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}