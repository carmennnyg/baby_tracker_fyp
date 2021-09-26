package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
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

public class mealFeedingActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {

    private Button mStart2Button, mSave3Button;
    private EditText mDateAndTime, mMealFeedingNotes;
    private Spinner mMealSpinner, mSupplementSpinner;
    private ImageButton mBack4IV;
    private FirebaseAuth mAuth;
    private String mDate;
    private String mTime;

    private int dayFinal, monthFinal, yearFinal;

    private DatabaseReference current_user_db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_feeding);

        current_user_db = FirebaseDatabase.getInstance().getReference();
        mSave3Button =(Button) findViewById(R.id.save3Button);
        mStart2Button =(Button) findViewById(R.id.start2button);
        mDateAndTime =(EditText) findViewById(R.id.DateTimeMealFeeding);
        mMealSpinner = (Spinner) findViewById(R.id.meal_spinner);
        mSupplementSpinner = (Spinner) findViewById(R.id.supplement_spinner);
        mMealFeedingNotes = (EditText) findViewById(R.id.meal_feeding_notes);
        mBack4IV = (ImageButton) findViewById(R.id. back4Button);

        mBack4IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(mealFeedingActivity.this, FeedingActivity.class);
                startActivity(back);
            }
        });


        mSave3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDateAndTime.getText().toString().equals("")) {
                    Toast.makeText(mealFeedingActivity.this, "Store Data Unsuccessfully!",
                            Toast.LENGTH_SHORT).show();

                } else {

                    String dateAndTime = mDateAndTime.getText().toString();
                    String bfNotes = mMealFeedingNotes.getText().toString();
                    String mealSpinner = mMealSpinner.getSelectedItem().toString();
                    String supplementSpinner = mSupplementSpinner.getSelectedItem().toString();

                    //spinner for meal feeding
                    mMealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String meal_feeding = adapterView.getItemAtPosition(i).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    //spinner for supplement feeding
                    mSupplementSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String supplement_feeding = adapterView.getItemAtPosition(i).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    //Store Data in the Database
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users")
                            .child(user_id).child("Feeding").child("Meal Feeding").child("Time Stamp");

                    Map mealfeeding = new HashMap();

                    // Adding elements to the Map using standard add() method
                    mealfeeding.put("Meal_Feeding_Date_and_Time", dateAndTime);
                    mealfeeding.put("Meal_Feeding", mealSpinner);
                    mealfeeding.put("Supplement_Feeding", supplementSpinner);
                    mealfeeding.put("Meal_Feeding_Note", bfNotes);

                    current_user_db.child("Latest").child("Meal Feeding").setValue(mealfeeding);

                    db.child(mDate).child(mTime).setValue(mealfeeding);

                    Toast.makeText(mealFeedingActivity.this, "Store Data Successfully!",
                            Toast.LENGTH_SHORT).show();

                    Intent feeding = new Intent(mealFeedingActivity.this, FeedingActivity.class);
                    startActivity(feeding);
                }
            }
        });

        mStart2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day =c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(mealFeedingActivity.this, mealFeedingActivity.this,
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

        TimePickerDialog timePickerDialog =new TimePickerDialog(mealFeedingActivity.this, mealFeedingActivity.this,
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
            startActivity(new Intent(this, FeedingActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}