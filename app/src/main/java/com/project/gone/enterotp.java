package com.project.gone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class enterotp extends AppCompatActivity {
    private EditText otp;
    private Button submit_btn;
    private TextView counter;
    private TextView resend;
    private FirebaseAuth mAuth;
    String easyPuzzle;
    String sendotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterotp);

        mAuth = FirebaseAuth.getInstance();
        otp = findViewById(R.id.enterotp);
        submit_btn = findViewById(R.id.okbtn);
        counter = findViewById(R.id.edittextResendOtp);
        resend = findViewById(R.id.otpresendbtn);
        Intent intent = getIntent();
        easyPuzzle = intent.getExtras().getString("epuzzle");
        sendVerificationCode();


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = otp.getText().toString();
                if(value.isEmpty())
                {
                    Toast.makeText(enterotp.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }
                else if(value.length()!=6)
                {
                    Toast.makeText(enterotp.this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                }
                else {

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(sendotp, value);
                    signInWithPhoneAuthCredential(credential);
                }

            }
        });

    }

    private void sendVerificationCode() {

        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                counter.setText("Time Remaining :" + millisUntilFinished / 1000);
                resend.setEnabled(false);
                resend.setVisibility(View.GONE);

            }

            @Override
            public void onFinish() {

                counter.setVisibility(View.GONE);
                resend.setVisibility(View.VISIBLE);
                resend.setEnabled(true);
                resend.setText("Resend OTP");
                resend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendVerificationCode();
                        counter.setVisibility(View.VISIBLE);

                    }
                });

            }
        }.start();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                easyPuzzle,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        sendotp = s;
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);


                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(enterotp.this, "Verification Failed", Toast.LENGTH_SHORT).show();


                    }
                });        // OnVerificationStateChangedCallbacks
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(enterotp.this, dashboard.class));

                            //FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid

                                Toast.makeText(enterotp.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(enterotp.this,MainActivity.class));

                            }
                        }
                    }
                });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(enterotp.this,MainActivity.class));
        super.onBackPressed();
    }
}