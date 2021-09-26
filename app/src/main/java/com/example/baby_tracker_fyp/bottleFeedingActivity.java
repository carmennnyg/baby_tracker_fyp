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

public class bottleFeedingActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {

    private Button mStart1Button, mSave2Button;
    private EditText mDateAndTime, mAmtInOZ, mBottleFeedingNotes;
    private FirebaseAuth mAuth;
    private String mDate;
    private String mTime;
    private ImageButton mBack1TV;

    private int dayFinal, monthFinal, yearFinal;

    private DatabaseReference current_user_db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_feeding);

        current_user_db = FirebaseDatabase.getInstance().getReference();

        mSave2Button =(Button) findViewById(R.id.save2Button);
        mStart1Button =(Button) findViewById(R.id.start1button);
        mDateAndTime =(EditText) findViewById(R.id.DateTimeBottleFeeding);
        mAmtInOZ =(EditText) findViewById(R.id.amountInOZET);
        mBottleFeedingNotes = (EditText) findViewById(R.id.bottle_feeding_notes);
        mBack1TV = (ImageButton) findViewById(R.id.back1Button);

        mBack1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(bottleFeedingActivity.this, FeedingActivity.class);
                startActivity(back);
            }
        });

        mSave2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDateAndTime.getText().toString().equals("")) {
                    Toast.makeText(bottleFeedingActivity.this, "Store Data Unsuccessfully!",
                            Toast.LENGTH_SHORT).show();

                } else {

                    String AmtInOZ = mAmtInOZ.getText().toString();
                    String bfNotes = mBottleFeedingNotes.getText().toString();
                    String dateAndTime = mDateAndTime.getText().toString();

                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users")
                            .child(user_id).child("Feeding").child("Bottle Feeding").child("Time Stamp");

                    Map bottlefeeding = new HashMap();

                    bottlefeeding.put("Amount_Milk_In_OZ", AmtInOZ);
                    bottlefeeding.put("Bottle_Feeding_Note", bfNotes);
                    bottlefeeding.put("Bottle_Feeding_Date_and_Time", dateAndTime);

                    current_user_db.child("Latest").child("Bottle Feeding").setValue(bottlefeeding);

                    db.child(mDate).child(mTime).setValue(bottlefeeding);

                    Toast.makeText(bottleFeedingActivity.this, "Store Data Successfully!",
                            Toast.LENGTH_SHORT).show();

                    Intent feeding = new Intent(bottleFeedingActivity.this, FeedingActivity.class);
                    startActivity(feeding);
                }
            }
        });

        mStart1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day =c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(bottleFeedingActivity.this, bottleFeedingActivity.this,
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

        TimePickerDialog timePickerDialog =new TimePickerDialog(bottleFeedingActivity.this, bottleFeedingActivity.this,
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