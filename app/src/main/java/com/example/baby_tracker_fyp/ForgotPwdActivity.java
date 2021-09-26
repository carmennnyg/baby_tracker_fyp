package com.example.baby_tracker_fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPwdActivity extends AppCompatActivity {
    private Button mSendEmailButton;
    private EditText mResetEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);

        mAuth = FirebaseAuth.getInstance();

        mResetEmail = (EditText) findViewById(R.id.resetEmail);
        mSendEmailButton = (Button) findViewById(R.id.sendEmailButton);

        mSendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = mResetEmail.getText().toString();

                if (TextUtils.isEmpty(userEmail))
                {
                    Toast.makeText(ForgotPwdActivity.this, "Please Enter Your Email Address.",
                            Toast.LENGTH_SHORT).show();
                }

                else
                {
                    // send an email link to reset password
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPwdActivity.this, "Please Check Your Email",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotPwdActivity.this, LoginActivity.class));
                            }

                            else
                            {
                                String message = task.getException().getMessage();
                                Toast.makeText(ForgotPwdActivity.this, "Error Occured" + message,
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }
}