package com.example.baby_tracker_fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private Button mSignUp1Button;
    private TextView mSignIn1TV;
    private EditText mEmailField, mPassword1Field;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSignUp1Button =(Button) findViewById(R.id.signUp1_button);
        mSignIn1TV = (TextView) findViewById(R.id.signInTV);
        mEmailField =(EditText) findViewById(R.id.email_edit_text);
        mPassword1Field =(EditText) findViewById(R.id.password1_edit_text);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    finish();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        };

        mSignIn1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        mSignUp1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailField.getText().toString().trim();
                String password = mPassword1Field.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmailField.setError("Please enter an email address");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword1Field.setError("Please enter a password");
                    return;
                }

                if (password.length() < 11) {
                    mPassword1Field.setError("Password too short, Please Enter Minimum 11 Characters!");
                    return;
                }

                //create user
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Register Unsuccessfully, Please Try Again.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Register Successfully",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

}

