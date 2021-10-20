package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SymptomsActivity extends AppCompatActivity {

    private Button mDiseaseButton;
    private Spinner s1,s2,s3,s4,s5,s6,s7;

    String d[] = new String[7];

    String cold[] = {"Fever","Sneezing","Coughing","Lack of Appetite","Difficulty in Sleeping"};
    String diaper_rash[] = {"Skin Sore", "Skin Red", "Skin Scaly", "Skin Tender"};
    String colic[] = {"Crying for no obvious reason", "Pain Cry",
            "Reddening of the Face", "Crying lasts for three hours or more", "Stomach Pains"};
    String baby_acne[] = {"Small red or white bumps on cheeks, nose, and forehead"};
    String constipation[] = {"Hard small pebbles", "Infrequent stools that are difficult to pass", "Liquid Stools"};
    String diarrhea[] = {"Loose, Yellow, and Seedy Stool"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        mDiseaseButton = findViewById(R.id.disease);

        s1 = findViewById(R.id.syp1);
        s2 = findViewById(R.id.syp2);
        s3 = findViewById(R.id.syp3);
        s4 = findViewById(R.id.syp4);
        s5 = findViewById(R.id.syp5);
        s6 = findViewById(R.id.syp6);
        s7 = findViewById(R.id.syp7);

        mDiseaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s1.getSelectedItem().toString().equals("SELECT SYMPTOM")
                        && s2.getSelectedItem().toString().equals("SELECT SYMPTOM")
                        && s3.getSelectedItem().toString().equals("SELECT SYMPTOM")
                        && s4.getSelectedItem().toString().equals("SELECT SYMPTOM")
                        && s5.getSelectedItem().toString().equals("SELECT SYMPTOM")
                        && s6.getSelectedItem().toString().equals("SELECT SYMPTOM")
                        && s7.getSelectedItem().toString().equals("SELECT SYMPTOM")){
                    Toast.makeText(SymptomsActivity.this, "Please Select Symptoms Before Click The Button!",
                            Toast.LENGTH_SHORT).show();

                } else {

                    int c[] = new int[6];
                    d[0] = s1.getSelectedItem().toString();
                    d[1] = s2.getSelectedItem().toString();
                    d[2] = s3.getSelectedItem().toString();
                    d[3] = s4.getSelectedItem().toString();
                    d[4] = s5.getSelectedItem().toString();
                    d[5] = s6.getSelectedItem().toString();
                    d[6] = s7.getSelectedItem().toString();


                    for (int i = 0; i < 7; i++) {
                        for (int j = 1; j <= 6; j++) {
                            switch (j) {
                                case 1: {
                                    int l = 0;
                                    l = cold.length;
                                    for (int k = 0; k < l; k++) {
                                        if (d[i].equals(cold[k])) {
                                            c[0]++;
                                        }
                                    }
                                    break;
                                }

                                case 2: {
                                    int l = 0;
                                    l = diaper_rash.length;
                                    for (int k = 0; k < l; k++) {
                                        if (d[i].equals(diaper_rash[k])) {
                                            c[1]++;
                                        }
                                    }
                                    break;
                                }

                                case 3: {
                                    int l = 0;
                                    l = colic.length;
                                    for (int k = 0; k < l; k++) {
                                        if (d[i].equals(colic[k])) {
                                            c[2]++;
                                        }
                                    }
                                    break;
                                }

                                case 4: {
                                    int l = 0;
                                    l = baby_acne.length;
                                    for (int k = 0; k < l; k++) {
                                        if (d[i].equals(baby_acne[k])) {
                                            c[3]++;
                                        }
                                    }
                                    break;
                                }

                                case 5: {
                                    int l = 0;
                                    l = constipation.length;
                                    for (int k = 0; k < l; k++) {
                                        if (d[i].equals(constipation[k])) {
                                            c[4]++;
                                        }
                                    }
                                    break;
                                }

                                case 6: {
                                    int l = 0;
                                    l = diarrhea.length;
                                    for (int k = 0; k < l; k++) {
                                        if (d[i].equals(diarrhea[k])) {
                                            c[5]++;
                                        }
                                    }
                                    break;
                                }


                            }
                        }
                    }


                    int max = c[0];
                    for (int m = 0; m < 6; m++) {
                        if (c[m] > max)
                            max = c[m];
                    }

                    Intent disease_screen = new Intent(SymptomsActivity.this, DiseaseActivity.class);
                    disease_screen.putExtra("max", max);
                    disease_screen.putExtra("c", c);
                    startActivity(disease_screen);
                }

            }

        });
    }

}
