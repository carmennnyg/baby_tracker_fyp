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

        //get an instance and access a location in the database
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
                        public void onDataChange(DataSnapshot dataSnapshot) { //This method will be called with a snapshot of the current data at the location

                            String[] xValues = {};
                            float[] yValues = {};
                            float[] yValues2 = {};

                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                String time2 = snapshot.getKey();

                                // Loop all time stamps in the database
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

                            //// Loop the time of left & right breastfeeding in database
                            //Gives access to all of the immediate children of this snapshot
                            for(DataSnapshot child: dataSnapshot.getChildren()) {
                                //This method is used to present the data contained in this snapshot into a class of I choosing.
                                String leftBreastFeedTime = child.child("Left_Breast_Feeding_Time").getValue(String.class);
                                float lBreastTime = Float.parseFloat(leftBreastFeedTime); //return a new float of the lBreastTime
                                String rightBreastFeedTime = child.child("Right_Breast_Feeding_Time").getValue(String.class);
                                float rBreastTime = Float.parseFloat(rightBreastFeedTime); //return a new float of the rBreastTime

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

                            //Change chart features and settings
                            XAxis xAxis = mBreastFeedingChart.getXAxis(); //get the x axis from chart
                            //Sets the minimum interval between the y-axis values.
                            //Used to avoid value duplicating when zooming in to a point where the number of decimals set for the axis
                            // no longer allow to distinguish between two axis values.
                            xAxis.setGranularity(1f);
                            //Enables the granularity feature that limits the interval of the y-axis when zooming in
                            xAxis.setGranularityEnabled(true);
                            drawMultiLineGraph(yValues, yValues2, xValues); //Create the drawMultiLineGraph method

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) { //This method will be triggered in the event that this listener either failed at the server or been removed
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
    // Pass data in the Multi Line Chart
    private void drawMultiLineGraph(float[] yValues, float[] yValues2, String[] xValues) {
        ArrayList<Entry> yData = new ArrayList<>(); //create a list of entry of yData
        ArrayList<Entry> yData2 = new ArrayList<>(); //create a list of entry of yData2
        for (int i = 0; i < yValues.length; i++) {
            yData.add(new Entry(i, yValues[i])); //Add yData to the entry list by creating an object of Entry and passing x and y to its constructor
            yData2.add(new Entry(i, yValues2[i]));
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>(); ////values for data input lineDataSets
        LineDataSet setl, setr; //Create a reference variable of the Multi Line chart, LineDataSet setl, setr
        setl = new LineDataSet(yData, "Left Breast Feeding Time in Mins"); //create a dataset and give it a type
        setl.setColor(Color.BLUE); // to set the color
        setr = new LineDataSet(yData2, "Right Breast Feeding Time in Mins"); //create a dataset and give it a type
        setr.setColor(Color.GREEN); // to set the color
        setl.setValueTextSize(20f);
        setr.setValueTextSize(20f);
        lineDataSets.add(setl); //Add setl data to the entry list (lineDataSets)
        lineDataSets.add(setr); //Add setr data to the entry list (lineDataSets)
        LineData data = new LineData(lineDataSets);
        LineData data2 = new LineData(setr);
        mBreastFeedingChart.setData(data); //sets the data
        mBreastFeedingChart.invalidate(); //update the values and it automatically changes the values in the multi line chart.
        //Animates the charts values on the vertical axis, means that the chart will build up within the specified time from bottom to top.
        mBreastFeedingChart.animateY(2000); //animate vertical 2000 milliseconds
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
        XAxis xAxis = mBottleFeedingChart.getXAxis();
        mBottleFeedingChart.setData(data5);
        mBottleFeedingChart.invalidate();
        mBottleFeedingChart.animateY(5000); ////animate vertical 5000 milliseconds

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
        XAxis xAxis = mSleepingChart.getXAxis();
        mSleepingChart.setData(data3);
        mSleepingChart.invalidate();
        mSleepingChart.animateY(5000); ////animate vertical 5000 milliseconds

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
        XAxis xAxis = mTemperatureChart.getXAxis();
        mTemperatureChart.setData(data4);
        mTemperatureChart.invalidate();
        mTemperatureChart.animateY(5000); ////animate vertical 5000 milliseconds

    }

}