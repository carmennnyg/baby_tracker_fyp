package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Summary1Activity extends AppCompatActivity {

    private TextView mLastBottleFeedingTime, mLastAmountInOz, mLastBottleFeedingNote;
    private TextView mLastBreastFeedingTime, mLeftBreastFeed, mRightBreastFeed, mLastBreastFeedingNote;
    private TextView mLastMealFeedingTime, mLastMealTaking, mLastSupplementTaking, mLastMealNote;
    private TextView mLastDiaperingTime, mLastDiaperingStatus, mLastDiaperingNote;
    private TextView mLastSleepingTime, mLastSleepDuration;
    private TextView mLastTemperatureTime, mLastDegreeCelsius, mLastTempNote;
    private TextView mLastMedicationTime, mLastMedDetails, mLastMedNote;


    private ValueEventListener valueEventListener, valueEventListener2, valueEventListener3,
            valueEventListener4, valueEventListener5, valueEventListener6, valueEventListener7;

    private DatabaseReference current_user_db, current_user_db2, current_user_db3,
            current_user_db4, current_user_db5, current_user_db6, current_user_db7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary1);

        mLastBottleFeedingTime = findViewById(R.id.lastDateAndTimeTV);
        mLastAmountInOz = findViewById(R.id.amtInOz);
        mLastBottleFeedingNote = findViewById(R.id.lastBottleFNoteTV);
        mLastBreastFeedingTime = findViewById(R.id.last_date_and_time_breast_feed);
        mLeftBreastFeed = findViewById(R.id.left_breast_feed_time);
        mRightBreastFeed = findViewById(R.id.right_breast_feed_time);
        mLastBreastFeedingNote = findViewById(R.id.txt_note_breast_feed);
        mLastMealFeedingTime = findViewById(R.id.last_time_stamp_meals);
        mLastMealTaking = findViewById(R.id.last_meal_taking);
        mLastSupplementTaking = findViewById(R.id.last_supplement_taking);
        mLastMealNote = findViewById(R.id.txt_note_meal);
        mLastDiaperingTime = findViewById(R.id.diaper_last_time_stamp);
        mLastDiaperingStatus = findViewById(R.id.edt_diaper_status);
        mLastDiaperingNote = findViewById(R.id.edt_diaper_note);
        mLastSleepingTime = findViewById(R.id.sleep_last_time_stamp);
        mLastSleepDuration = findViewById(R.id.edt_sleep_duration);
        mLastTemperatureTime = findViewById(R.id.temp_last_time_stamp);
        mLastDegreeCelsius = findViewById(R.id.edt_degree_celsius);
        mLastTempNote = findViewById(R.id.edt_temp_note);
        mLastMedicationTime = findViewById(R.id.med_last_time_stamp);
        mLastMedDetails = findViewById(R.id.edt_med_details);
        mLastMedNote = findViewById(R.id.edt_med_note);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();

        //Bottle Feeding Summary
        current_user_db = FirebaseDatabase.getInstance().getReference().child("Latest").child("Bottle Feeding");

        //This method will be called with a snapshot of the data at this location.
        valueEventListener = current_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getKey().equals("Amount_Milk_In_OZ")) {
                        String AmtInOZ = child.getValue().toString();
                        mLastAmountInOz.setText(AmtInOZ);
                    }
                    if (child.getKey().equals("Bottle_Feeding_Date_and_Time")) {
                        String dateAndTime = child.getValue().toString();
                        mLastBottleFeedingTime.setText(dateAndTime);
                    }
                    if (child.getKey().equals("Bottle_Feeding_Note")) {
                        String bfNotes = child.getValue().toString();
                        mLastBottleFeedingNote.setText(bfNotes);
                    }
                }
            }

            //This method will be triggered in the event that this listener either failed at the server,
            // or is removed as a result of the security and Firebase Database rules.
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // Breast Feeding Summary
        current_user_db2 = FirebaseDatabase.getInstance().getReference().child("Latest").child("Breast Feeding");

        valueEventListener2 = current_user_db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getKey().equals("Breast_Feeding_Date_and_Time")) {
                        String dateAndTime = child.getValue().toString();
                        mLastBreastFeedingTime.setText(dateAndTime);
                    }
                    if (child.getKey().equals("Left_Breast_Feeding_Time")) {
                        String leftBreastFeedTime = child.getValue().toString();
                        mLeftBreastFeed.setText(leftBreastFeedTime);
                    }
                    if (child.getKey().equals("Right_Breast_Feeding_Time")) {
                        String rightBreastFeedTime = child.getValue().toString();
                        mRightBreastFeed.setText(rightBreastFeedTime);
                    }

                    if (child.getKey().equals("Breast_Feeding_Note")) {
                        String bfNotes = child.getValue().toString();
                        mLastBreastFeedingNote.setText(bfNotes);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // Meal Feeding Summary
        current_user_db3 = FirebaseDatabase.getInstance().getReference().child("Latest").child("Meal Feeding");

        valueEventListener3 = current_user_db3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getKey().equals("Meal_Feeding_Date_and_Time")) {
                        String dateAndTime = child.getValue().toString();
                        mLastMealFeedingTime.setText(dateAndTime);
                    }
                    if (child.getKey().equals("Meal_Feeding")) {
                        String mealSpinner = child.getValue().toString();
                        mLastMealTaking.setText(mealSpinner);
                    }
                    if (child.getKey().equals("Supplement_Feeding")) {
                        String supplementSpinner = child.getValue().toString();
                        mLastSupplementTaking.setText(supplementSpinner);
                    }

                    if (child.getKey().equals("Meal_Feeding_Note")) {
                        String bfNotes = child.getValue().toString();
                        mLastMealNote.setText(bfNotes);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // Diapering Summary
        current_user_db4 = FirebaseDatabase.getInstance().getReference().child("Latest").child("Diaper Status");

        valueEventListener4 = current_user_db4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getKey().equals("Diaper_Date_and_Time")) {
                        String dateAndTime = child.getValue().toString();
                        mLastDiaperingTime.setText(dateAndTime);
                    }
                    if (child.getKey().equals("Diaper_Status")) {
                        String diaperStatus = child.getValue().toString();
                        mLastDiaperingStatus.setText(diaperStatus);
                    }
                    if (child.getKey().equals("Diaper_Note")) {
                        String bfNotes = child.getValue().toString();
                        mLastDiaperingNote.setText(bfNotes);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // Sleeping Summary
        current_user_db5 = FirebaseDatabase.getInstance().getReference().child("Latest").child("Sleep Duration");

        valueEventListener5 = current_user_db5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getKey().equals("Sleep_Date_and_Time")) {
                        String dateAndTime = child.getValue().toString();
                        mLastSleepingTime.setText(dateAndTime);
                    }
                    if (child.getKey().equals("Sleep_Duration")) {
                        String sleepMin = child.getValue().toString();
                        mLastSleepDuration.setText(sleepMin);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // Temperature Summary
        current_user_db6 = FirebaseDatabase.getInstance().getReference().child("Latest").child("Temperature");

        valueEventListener6 = current_user_db6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getKey().equals("Temperature_Date_and_Time")) {
                        String dateAndTime = child.getValue().toString();
                        mLastTemperatureTime.setText(dateAndTime);
                    }
                    if (child.getKey().equals("Degree_Celsius")) {
                        String degreeCelsius = child.getValue().toString();
                        mLastDegreeCelsius.setText(degreeCelsius);
                    }
                    if (child.getKey().equals("Temperature_Note")) {
                        String temperatureNotes = child.getValue().toString();
                        mLastTempNote.setText(temperatureNotes);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // Medication Summary
        current_user_db7 = FirebaseDatabase.getInstance().getReference().child("Latest").child("Medication");

        valueEventListener7 = current_user_db7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getKey().equals("Medication_Date_and_Time")) {
                        String dateAndTime = child.getValue().toString();
                        mLastMedicationTime.setText(dateAndTime);
                    }
                    if (child.getKey().equals("Med_Details")) {
                        String medDetails = child.getValue().toString();
                        mLastMedDetails.setText(medDetails);
                    }
                    if (child.getKey().equals("Med_Notes")) {
                        String medNotes = child.getValue().toString();
                        mLastMedNote.setText(medNotes);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        current_user_db.removeEventListener(valueEventListener);
        current_user_db2.removeEventListener(valueEventListener2);
        current_user_db3.removeEventListener(valueEventListener3);
        current_user_db4.removeEventListener(valueEventListener4);
        current_user_db5.removeEventListener(valueEventListener5);
        current_user_db6.removeEventListener(valueEventListener6);
        current_user_db7.removeEventListener(valueEventListener7);


    }

}