package com.example.baby_tracker_fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LogoutActivity extends AppCompatActivity {
    private Button mBackHomeButton, mBackLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        mBackHomeButton = (Button) findViewById(R.id.backHomeButton);
        mBackLoginButton = (Button) findViewById(R.id.backLoginButton);

        mBackHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(LogoutActivity.this, HomeActivity.class);
                startActivity(home);
            }
        });

        mBackLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login1 = new Intent(LogoutActivity.this, LoginActivity.class);
                startActivity(login1);
            }
        });
    }
}