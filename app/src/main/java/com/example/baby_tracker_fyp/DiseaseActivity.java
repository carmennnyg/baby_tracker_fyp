package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DiseaseActivity extends AppCompatActivity {
    private TextView info[] = new TextView[2];
    private CardView dis_content;
    private ImageView im[] = new ImageView[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);

        dis_content = findViewById(R.id.cardView);
        im[0] = findViewById(R.id.content_disease1);
        im[1] = findViewById(R.id.content_disease2);
        info[0] = findViewById(R.id.info1);
        info[1] = findViewById(R.id.info2);


        int max = getIntent().getIntExtra("max",0);
        int[] c = getIntent().getIntArrayExtra("c");

        int i=0;

        if (max == c[0]) {
            im[i].setImageDrawable(getResources().getDrawable(R.drawable.d1));

            info[i].setVisibility(View.VISIBLE);
            info[i].setMovementMethod(LinkMovementMethod.getInstance());
            info[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cold = new Intent(Intent.ACTION_VIEW);
                    cold.setData(Uri.parse
                            ("https://www.mayoclinic.org/diseases-conditions/common-cold-in-babies/symptoms-causes/syc-20351651?p=1"));
                    startActivity(cold);
                }
            });

            i++;
        }

        if (max == c[1]) {
            im[i].setImageDrawable(getResources().getDrawable(R.drawable.d2));

            info[i].setVisibility(View.VISIBLE);
            info[i].setMovementMethod(LinkMovementMethod.getInstance());
            info[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent diaper_rash = new Intent(Intent.ACTION_VIEW);
                    diaper_rash.setData(Uri.parse("https://kidshealth.org/en/parents/diaper-rash.html"));
                    startActivity(diaper_rash);
                }
            });

            i++;
        }

        if (max == c[2]) {
            im[i].setImageDrawable(getResources().getDrawable(R.drawable.d3));

            info[i].setVisibility(View.VISIBLE);
            info[i].setMovementMethod(LinkMovementMethod.getInstance());
            info[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent colic = new Intent(Intent.ACTION_VIEW);
                    colic.setData(Uri.parse("https://familydoctor.org/condition/colic/?adfree=true"));
                    startActivity(colic);
                }
            });

            i++;
        }
        if (max == c[3]) {
            im[i].setImageDrawable(getResources().getDrawable(R.drawable.d4));

            info[i].setVisibility(View.VISIBLE);
            info[i].setMovementMethod(LinkMovementMethod.getInstance());
            info[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent baby_acne = new Intent(Intent.ACTION_VIEW);
                    baby_acne.setData(Uri.parse
                            ("https://www.parents.com/baby/care/american-baby-how-tos/how-to-treat-baby-acne/"));
                    startActivity(baby_acne);
                }
            });

            i++;
        }
        if (max == c[4]) {
            im[i].setImageDrawable(getResources().getDrawable(R.drawable.d5));

            info[i].setVisibility(View.VISIBLE);
            info[i].setMovementMethod(LinkMovementMethod.getInstance());
            info[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent constipation = new Intent(Intent.ACTION_VIEW);
                    constipation.setData(Uri.parse
                            ("https://www.parents.com/baby/health/constipation/constipation-in-babies-signs-causes-and-cures/"));
                    startActivity(constipation);
                }
            });

            i++;
        }

        if (max == c[5]) {
            im[i].setImageDrawable(getResources().getDrawable(R.drawable.d6));

            info[i].setVisibility(View.VISIBLE);
            info[i].setMovementMethod(LinkMovementMethod.getInstance());
            info[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent diarrhea = new Intent(Intent.ACTION_VIEW);
                    diarrhea.setData(Uri.parse
                            ("https://www.parents.com/baby/health/diarrhea/diarrhea-101/"));
                    startActivity(diarrhea);
                }
            });

            i++;
        }
    }
}

