package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
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
    private TextView mBreastFeedingTV, mBottleFeedingTV, mSleepingTV, mTemperatureTV;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mAuth = FirebaseAuth.getInstance();

        mBreastFeedingTV = (TextView) findViewById(R.id.lastBreastFeedingTime);
        mBottleFeedingTV = (TextView) findViewById(R.id.lastBottleFeedingTime);
        mSleepingTV = (TextView) findViewById(R.id.lastSleepTime);
        mTemperatureTV = (TextView) findViewById(R.id.lastTemperatureTime);


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
                            mBreastFeedingTV.setText(dateAndTime);

                            String[] x_value = {};
                            float[] y_value = {};
                            float[] y_value1 = {};

                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                String time1 = snapshot.getKey();

                                // Loop all time stamps in the database
                                int current_time_size = x_value.length;
                                int new_time_size = current_time_size+1;
                                String[] temp_array = new String[new_time_size];
                                for(int i = 0; i < current_time_size; i++)

                                {
                                    temp_array[i] = x_value[i];
                                }

                                temp_array[new_time_size-1] = time1;
                                x_value = temp_array;
                            }

                            //// Loop the time of left & right breastfeeding in database
                            //Gives access to all of the immediate children of this snapshot
                            for(DataSnapshot child: dataSnapshot.getChildren()) {
                                //This method is used to present the data contained in this snapshot into a class of I choosing.
                                String leftBreastFeedingTime = child.child("Left_Breast_Feeding_Time").getValue(String.class);
                                float lBreastTime = Float.parseFloat(leftBreastFeedingTime); //return a new float of the lBreastTime
                                String rightBreastFeedingTime = child.child("Right_Breast_Feeding_Time").getValue(String.class);
                                float rBreastTime = Float.parseFloat(rightBreastFeedingTime); //return a new float of the rBreastTime

                                int current_size = y_value.length;
                                int newSize = current_size + 1;
                                float[] temp_array_1 = new float[newSize];
                                float[] temp_array_2 = new float[newSize];
                                for (int i = 0; i < current_size; i++) {
                                    temp_array_1[i] = y_value[i];
                                    temp_array_2[i] = y_value1[i];
                                }
                                temp_array_1[newSize-1] = lBreastTime;
                                temp_array_2[newSize-1] = rBreastTime;
                                y_value = temp_array_1;
                                y_value1 = temp_array_2;
                            }

                            //Change chart features and settings
                            XAxis xAxis = mBreastFeedingChart.getXAxis(); //get the x axis from chart
                            //Sets the minimum interval between the y-axis values.
                            //Used to avoid value duplicating when zooming in to a point where the number of decimals set for the axis
                            // no longer allow to distinguish between two axis values.
                            xAxis.setGranularity(1f);
                            //Enables the granularity feature that limits the interval of the y-axis when zooming in
                            xAxis.setGranularityEnabled(true);
                            drawMultiLineGraph(y_value, y_value1, x_value); //Create the drawMultiLineGraph method

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
                            mBottleFeedingTV.setText(dateAndTime);


                            String[] x_value_bar_graph= {};
                            int[] y_value_bar_graph={};

                            // Loop all time stamps in the database
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String time1 = snapshot.getKey();

                                int current_time_size = x_value_bar_graph.length;
                                int new_size = current_time_size + 1;
                                String[] temp_array = new String[new_size];
                                for (int i=0; i<current_time_size; i++){
                                    temp_array[i] = x_value_bar_graph[i];
                                }
                                temp_array[new_size-1] = time1;
                                x_value_bar_graph = temp_array;

                            }



                            // Loop all amount of milk in database
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String AmtInOZ  = child.child("Amount_Milk_In_OZ").getValue(String.class);
                                int AmtInOZ2  = Integer.parseInt(AmtInOZ);

                                int current_size = y_value_bar_graph.length;
                                int new_size = current_size+1;
                                int[] temp_array = new int[new_size];
                                for (int i=0; i<current_size; i++){
                                    temp_array[i] = y_value_bar_graph[i];
                                }
                                temp_array[new_size-1] = AmtInOZ2 ;
                                y_value_bar_graph = temp_array;

                            }

                            // Change graph features and settings
                            XAxis xAxis = mBottleFeedingChart.getXAxis();
                            xAxis.setGranularity(1f);
                            xAxis.setGranularityEnabled(true);
                            drawBarGraph4(y_value_bar_graph,x_value_bar_graph);

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
                           mSleepingTV.setText(date);

                            String[] x_value_bar_graph={};
                            int[] y_value_bar_graph={};

                            // Loop all time stamps in the database
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String time1 = snapshot.getKey();

                                int current_size = x_value_bar_graph.length;
                                int new_size = current_size+1;
                                String[] temp_array = new String[new_size];
                                for (int i=0; i<current_size; i++){
                                    temp_array[i] = x_value_bar_graph[i];
                                }
                                temp_array[new_size-1] = time1;
                                x_value_bar_graph = temp_array;

                            }



                            // Loop all sleep duration in database
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String sleepMin = child.child("Sleep_Duration").getValue(String.class);
                                int sleepMin2 = Integer.parseInt(sleepMin);

                                int current_size = y_value_bar_graph.length;
                                int new_size = current_size+1;
                                int[] temp_array = new int[new_size];
                                for (int i=0; i<current_size; i++){
                                    temp_array[i] = y_value_bar_graph[i];
                                }
                                temp_array[new_size-1] = sleepMin2;
                                y_value_bar_graph = temp_array;

                            }

                            // Change graph features and settings
                            XAxis xAxis = mSleepingChart.getXAxis();
                            xAxis.setGranularity(1f);
                            xAxis.setGranularityEnabled(true);
                            drawBarGraph(y_value_bar_graph,x_value_bar_graph);

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
                            mTemperatureTV.setText(date);


                            String[] x_value_bar_graph={};
                            int[] y_value_bar_graph={};

                            // Loop all the time stamps in the database
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String time1 = snapshot.getKey();

                                int current_size = x_value_bar_graph.length;
                                int new_size = current_size + 1;
                                String[] temp_array = new String[new_size];
                                for (int i=0; i<current_size; i++){
                                    temp_array[i] = x_value_bar_graph[i];
                                }
                                temp_array[new_size-1] = time1;
                                x_value_bar_graph = temp_array;

                            }



                            // Loop all the degree celsius in the database
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String degreeCelsius = child.child("Degree_Celsius").getValue(String.class);
                                int degreeCelsius1 = Integer.parseInt(degreeCelsius);

                                int current_size = y_value_bar_graph.length;
                                int new_size = current_size+1;
                                int[] temp_array = new int[new_size];
                                for (int i=0; i<current_size; i++){
                                    temp_array[i] = y_value_bar_graph[i];
                                }
                                temp_array[new_size-1] = degreeCelsius1;
                                y_value_bar_graph = temp_array;

                            }

                            // Change graph features and settings
                            XAxis xAxis = mTemperatureChart.getXAxis();
                            xAxis.setGranularity(1f);
                            xAxis.setGranularityEnabled(true);
                            drawBarGraph1(y_value_bar_graph,x_value_bar_graph);
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
    private void drawMultiLineGraph(float[] y_value, float[] y_value1, String[] x_value) {
        ArrayList<Entry> yData = new ArrayList<>(); //create a list of entry of yData
        ArrayList<Entry> yData1 = new ArrayList<>(); //create a list of entry of yData2
        for (int i = 0; i < y_value.length; i++) {
            yData.add(new Entry(i, y_value[i])); //Add yData to the entry list by creating an object of Entry and passing x and y to its constructor
            yData1.add(new Entry(i, y_value1[i]));
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>(); ////values for data input lineDataSets
        LineDataSet setleft, setright; //Create a reference variable of the Multi Line chart, LineDataSet setl, setr
        setleft = new LineDataSet(yData, "Left Breast Feeding Time in Mins"); //create a dataset and give it a type
        setleft.setColor(Color.BLUE); // to set the color
        setright = new LineDataSet(yData1, "Right Breast Feeding Time in Mins"); //create a dataset and give it a type
        setright.setColor(Color.GREEN); // to set the color
        setleft.setValueTextSize(20f);
        setright.setValueTextSize(20f);
        lineDataSets.add(setleft); //Add setl data to the entry list (lineDataSets)
        lineDataSets.add(setright); //Add setr data to the entry list (lineDataSets)
        LineData data = new LineData(lineDataSets);
        //LineData data2 = new LineData(setr);
        XAxis xAxis = mBreastFeedingChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(x_value));
        mBreastFeedingChart.setData(data); //sets the data
        mBreastFeedingChart.invalidate(); //update the values and it automatically changes the values in the multi line chart.
        //Animates the charts values on the vertical axis, means that the chart will build up within the specified time from bottom to top.
        mBreastFeedingChart.animateY(2000); //animate vertical 2000 milliseconds
    }

    // Bottle Feeding Bar Graph
    private void drawBarGraph4(int [] y_value_bargraph, String [] x_value_bargraph){
        List<BarEntry> yData = new ArrayList<>();
        for (int i = 0; i<y_value_bargraph.length;i++){
            yData.add(new BarEntry(i,y_value_bargraph[i]));
        }

        BarDataSet set1bar;
        set1bar = new BarDataSet(yData,"Amount In OZ");
        set1bar.setColors(ColorTemplate.JOYFUL_COLORS);
        set1bar.setDrawValues(true);
        set1bar.setValueTextSize(20f);
        BarData data5 = new BarData(set1bar);
        XAxis xAxis = mBottleFeedingChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(x_value_bargraph));
        mBottleFeedingChart.setData(data5);
        mBottleFeedingChart.invalidate();
        mBottleFeedingChart.animateY(5000); ////animate vertical 5000 milliseconds

    }

    // Sleep Bar Chart
    private void drawBarGraph(int [] y_value_bargraph, String [] x_value_bargraph){
        List<BarEntry> yData = new ArrayList<>();
        for (int i = 0; i<y_value_bargraph.length;i++){
            yData.add(new BarEntry(i,y_value_bargraph[i]));
        }

        BarDataSet set2bar;
        set2bar = new BarDataSet(yData,"Sleep Duration in Mins");
        set2bar.setColors(ColorTemplate.MATERIAL_COLORS);
        set2bar.setDrawValues(true);
        set2bar.setValueTextSize(20f);
        BarData data3 = new BarData(set2bar);
        XAxis xAxis = mSleepingChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(x_value_bargraph));
        mSleepingChart.setData(data3);
        mSleepingChart.invalidate();
        mSleepingChart.animateY(5000); ////animate vertical 5000 milliseconds

    }

    // Temperature Bar Chart
    private void drawBarGraph1(int [] y_value_bargraph, String [] x_value_bargraph){
        List<BarEntry> yData = new ArrayList<>();
        for (int i = 0; i<y_value_bargraph.length;i++){
            yData.add(new BarEntry(i,y_value_bargraph[i]));
        }

        BarDataSet set3bar;
        set3bar = new BarDataSet(yData,"Degree Celsius");
        set3bar.setColors(ColorTemplate.COLORFUL_COLORS);
        set3bar.setDrawValues(true);
        set3bar.setValueTextSize(20f);
        BarData data4 = new BarData(set3bar);
        XAxis xAxis = mTemperatureChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(x_value_bargraph));
        mTemperatureChart.setData(data4);
        mTemperatureChart.invalidate();
        mTemperatureChart.animateY(5000); ////animate vertical 5000 milliseconds

    }

}