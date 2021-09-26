package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    LineChart mBreastFeedingChart;
    BarChart mSleepingChart;
    BarChart mTemperatureChart;
    BarChart mBottleFeedingChart;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mAuth = FirebaseAuth.getInstance();

        // Breast Feeding
        //a LineChart is initialized from xml
        mBreastFeedingChart = (LineChart) findViewById(R.id.line_chart_breast_feeding);

        // __________________Start for retrieving breast feeding database
        // _________________(time of left and right breast) information_________________________________

        final DatabaseReference current_user_db2 = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Feeding").child("Breast Feeding").child("Time Stamp");

        current_user_db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    final String dateAndTime = child.getKey();

                    current_user_db2.child(dateAndTime).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            //String time1 = "0";
                            String[] xValues = {};
                            //float lFeedingDuration = 0;
                            float[] yValues = {};
                            //float rFeedingDuration = 0;
                            float[] yValues2 = {};

                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                String time2 = snapshot.getKey();

                                int currentSize = xValues.length;
                                int newSize = currentSize + 1;
                                String[] tempArray = new String[newSize];
                                for(int i = 0; i < currentSize; i++)
                                {
                                    tempArray[i] = xValues[i];
                                }
                                tempArray[newSize - 1] = time2;
                                xValues = tempArray;
                            }

                            for(DataSnapshot child: dataSnapshot.getChildren()) {
                                String leftBreastFeedTime = child.child("Left_Breast_Feeding_Time").getValue(String.class);
                                float lBreastTime = Float.parseFloat(leftBreastFeedTime);
                                String rightBreastFeedTime = child.child("Right_Breast_Feeding_Time").getValue(String.class);
                                float rBreastTime = Float.parseFloat(rightBreastFeedTime);

                                int currentSize = yValues.length;
                                int newSize = currentSize + 1;
                                float[] tempArray = new float[newSize];
                                float[] tempArray2 = new float[newSize];
                                for (int i = 0; i < currentSize; i++) {
                                    tempArray[i] = yValues[i];
                                    tempArray2[i] = yValues2[i];
                                }
                                tempArray[newSize - 1] = lBreastTime;
                                tempArray2[newSize - 1] = rBreastTime;
                                yValues = tempArray;
                                yValues2 = tempArray2;
                            }

                            XAxis xAxis = mBreastFeedingChart.getXAxis();
                            xAxis.setGranularity(1f);
                            xAxis.setGranularityEnabled(true);
                            drawMultiLineGraph(yValues, yValues2, xValues);
                            //drawLineGraph(yValues, xValues);
                            //drawLineGraph(yValues2, xValues);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError){
            }
        });

        // Bottle Feeding
        //a BarChart is initialized from xml
        mBottleFeedingChart = (BarChart) findViewById(R.id.bar_chart_bottle_feeding);

        // __________________Start for retrieving bottle feeding database(amount of milk) information_________________________________

        final DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Feeding").child("Bottle Feeding").child("Time Stamp");

        current_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    final String dateAndTime = child.getKey();

                    current_user_db.child(dateAndTime).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String[] xValues_bargraph={};
                            int[] yValues_bargraph={};

                            // Loop all time stamps in the database
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String time2 = snapshot.getKey();

                                int currentSize = xValues_bargraph.length;
                                int newSize = currentSize+1;
                                String[] tempArray = new String[newSize];
                                for (int i=0; i<currentSize; i++){
                                    tempArray[i] = xValues_bargraph[i];
                                }
                                tempArray[newSize-1] = time2;
                                xValues_bargraph = tempArray;

                            }



                            // Loop all amount of milk in database
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String AmtInOZ  = child.child("Amount_Milk_In_OZ").getValue(String.class);
                                int AmtInOZ2  = Integer.parseInt(AmtInOZ );

                                int currentSize = yValues_bargraph.length;
                                int newSize = currentSize+1;
                                int[] tempArray = new int[newSize];
                                for (int i=0; i<currentSize; i++){
                                    tempArray[i] = yValues_bargraph[i];
                                }
                                tempArray[newSize-1] = AmtInOZ2 ;
                                yValues_bargraph = tempArray;

                            }

                            // Change graph features and settings
                            XAxis xAxis = mBottleFeedingChart.getXAxis();
                            xAxis.setGranularity(1f);
                            xAxis.setGranularityEnabled(true);
                            drawBarGraph4(yValues_bargraph,xValues_bargraph);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        // Sleeping
        //a BarChart is initialized from xml
        mSleepingChart = (BarChart) findViewById(R.id.bar_chart_sleeping);

        // __________________Start for retrieving sleeping database(amount of sleep) information_________________________________

        final DatabaseReference current_user_db5 = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Sleep Duration").child("Time Stamp");

        current_user_db5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    final String date = child.getKey();

                    current_user_db5.child(date).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String[] xValues_bargraph={};
                            int[] yValues_bargraph={};

                            // Loop all time stamps in the database
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String time2 = snapshot.getKey();

                                int currentSize = xValues_bargraph.length;
                                int newSize = currentSize+1;
                                String[] tempArray = new String[newSize];
                                for (int i=0; i<currentSize; i++){
                                    tempArray[i] = xValues_bargraph[i];
                                }
                                tempArray[newSize-1] = time2;
                                xValues_bargraph = tempArray;

                            }



                            // Loop all sleep duration in database
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String sleepMin = child.child("Sleep_Duration").getValue(String.class);
                                int sleepMin2 = Integer.parseInt(sleepMin);

                                int currentSize = yValues_bargraph.length;
                                int newSize = currentSize+1;
                                int[] tempArray = new int[newSize];
                                for (int i=0; i<currentSize; i++){
                                    tempArray[i] = yValues_bargraph[i];
                                }
                                tempArray[newSize-1] = sleepMin2;
                                yValues_bargraph = tempArray;

                            }

                            // Change graph features and settings
                            XAxis xAxis = mSleepingChart.getXAxis();
                            xAxis.setGranularity(1f);
                            xAxis.setGranularityEnabled(true);
                            drawBarGraph(yValues_bargraph,xValues_bargraph);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // Temperature
        //a BarChart is initialized from xml
        mTemperatureChart = (BarChart) findViewById(R.id.bar_chart_temperature);

        // __________________Start for retrieving temperature database(degree celsius) information_________________________________

        final DatabaseReference current_user_db6 = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(mAuth.getCurrentUser().getUid())
                .child("Health").child("Temperature").child("Time Stamp");

        current_user_db6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    final String date = child.getKey();

                    current_user_db6.child(date).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String[] xValues_bargraph={};
                            int[] yValues_bargraph={};

                            // Loop all the time stamps in the database
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String time2 = snapshot.getKey();

                                int currentSize = xValues_bargraph.length;
                                int newSize = currentSize+1;
                                String[] tempArray = new String[newSize];
                                for (int i=0; i<currentSize; i++){
                                    tempArray[i] = xValues_bargraph[i];
                                }
                                tempArray[newSize-1] = time2;
                                xValues_bargraph = tempArray;

                            }



                            // Loop all the degree celsius in the database
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String degreeCelsius = child.child("Degree_Celsius").getValue(String.class);
                                int degreeCelsius2 = Integer.parseInt(degreeCelsius);

                                int currentSize = yValues_bargraph.length;
                                int newSize = currentSize+1;
                                int[] tempArray = new int[newSize];
                                for (int i=0; i<currentSize; i++){
                                    tempArray[i] = yValues_bargraph[i];
                                }
                                tempArray[newSize-1] = degreeCelsius2;
                                yValues_bargraph = tempArray;

                            }

                            // Change graph features and settings
                            XAxis xAxis = mTemperatureChart.getXAxis();
                            xAxis.setGranularity(1f);
                            xAxis.setGranularityEnabled(true);
                            drawBarGraph1(yValues_bargraph,xValues_bargraph);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    // __________________Start to create each chart___________________


    // Breast Feeding Multi Line Graph
    private void drawMultiLineGraph(float[] yValues, float[] yValues2, String[] xValues) {
        ArrayList<Entry> yData = new ArrayList<>();
        ArrayList<Entry> yData2 = new ArrayList<>();
        for (int i = 0; i < yValues.length; i++) {
            yData.add(new Entry(i, yValues[i]));
            yData2.add(new Entry(i, yValues2[i]));
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        LineDataSet setl, setr;
        setl = new LineDataSet(yData, "Left Breast Feeding Time in Mins");
        setl.setColor(Color.BLUE);
        setr = new LineDataSet(yData2, "Right Breast Feeding Time in Mins");
        setr.setColor(Color.GREEN);
        setl.setValueTextSize(20f);
        setr.setValueTextSize(20f);
        lineDataSets.add(setl);
        lineDataSets.add(setr);
        LineData data = new LineData(lineDataSets);
        LineData data2 = new LineData(setr);
        //mBreastFeedingChart.getXAxis().setValueFormatter(new LabelFormatter(xValues));
        mBreastFeedingChart.setData(data);
        mBreastFeedingChart.invalidate();
        mBreastFeedingChart.animateY(2000);
        //mBreastFeedingChart.setData(data2);
    }

    // Bottle Feeding Bar Graph
    private void drawBarGraph4(int [] yValues_bargraph, String [] xValues_bargraph){
        List<BarEntry> yData = new ArrayList<>();
        for (int i = 0; i<yValues_bargraph.length;i++){
            yData.add(new BarEntry(i,yValues_bargraph[i]));
        }

        BarDataSet set4;
        set4 = new BarDataSet(yData,"Amount In OZ");
        set4.setColors(ColorTemplate.JOYFUL_COLORS);
        set4.setDrawValues(true);
        set4.setValueTextSize(20f);
        BarData data5 = new BarData(set4);
        //IAxisValueFormatter xAxisFormatter = new LabelFormatter(xValues_bargraph);
        XAxis xAxis = mBottleFeedingChart.getXAxis();
        //xAxis.setValueFormatter((ValueFormatter) xAxisFormatter);
        mBottleFeedingChart.setData(data5);
        mBottleFeedingChart.invalidate();
        mBottleFeedingChart.animateY(5000);

    }

    // Sleep Bar Chart
    private void drawBarGraph(int [] yValues_bargraph, String [] xValues_bargraph){
        List<BarEntry> yData = new ArrayList<>();
        for (int i = 0; i<yValues_bargraph.length;i++){
            yData.add(new BarEntry(i,yValues_bargraph[i]));
        }

        BarDataSet set2;
        set2 = new BarDataSet(yData,"Sleep Duration in Mins");
        set2.setColors(ColorTemplate.MATERIAL_COLORS);
        set2.setDrawValues(true);
        set2.setValueTextSize(20f);
        BarData data3 = new BarData(set2);
        //IAxisValueFormatter xAxisFormatter = new LabelFormatter(xValues_bargraph);
        XAxis xAxis = mSleepingChart.getXAxis();
        //xAxis.setValueFormatter((ValueFormatter) xAxisFormatter);
        mSleepingChart.setData(data3);
        mSleepingChart.invalidate();
        mSleepingChart.animateY(5000);

    }

    // Temperature Bar Chart
    private void drawBarGraph1(int [] yValues_bargraph, String [] xValues_bargraph){
        List<BarEntry> yData = new ArrayList<>();
        for (int i = 0; i<yValues_bargraph.length;i++){
            yData.add(new BarEntry(i,yValues_bargraph[i]));
        }

        BarDataSet set3;
        set3 = new BarDataSet(yData,"Degree Celsius");
        set3.setColors(ColorTemplate.COLORFUL_COLORS);
        set3.setDrawValues(true);
        set3.setValueTextSize(20f);
        BarData data4 = new BarData(set3);
        //IAxisValueFormatter xAxisFormatter = new LabelFormatter(xValues_bargraph);
        XAxis xAxis = mTemperatureChart.getXAxis();
        //xAxis.setValueFormatter((ValueFormatter) xAxisFormatter);
        mTemperatureChart.setData(data4);
        mTemperatureChart.invalidate();
        mTemperatureChart.animateY(5000);

    }

}