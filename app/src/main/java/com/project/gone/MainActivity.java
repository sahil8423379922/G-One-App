package com.project.gone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
    private EditText phonenumber;
    private CountryCodePicker countryCodePicker;
    private Button nextBtn;
    FirebaseAuth firebaseAuth;
    String completenumber;
    String getphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        firebaseAuth = FirebaseAuth.getInstance();
        nextBtn = findViewById(R.id.submitbtn);
        phonenumber = findViewById(R.id.edittextphone);
        countryCodePicker = findViewById(R.id.ccp);

        countryCodePicker.registerCarrierNumberEditText(phonenumber);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getphone = phonenumber.getText().toString();
                if (TextUtils.isEmpty(getphone)) {
                    phonenumber.setError("Phone number is Required");

                } else if (getphone.replace(" ", "").length() != 10) {
                    phonenumber.setError("Enter Valid Phone Number");
                } else {

                    startActivity(new Intent(MainActivity.this, enterotp.class));
                    completenumber = "+" + countryCodePicker.getSelectedCountryCode().replace(" ", "") + getphone.replace(" ", "");

                    Intent i = new Intent(MainActivity.this, enterotp.class);
                    i.putExtra("epuzzle", completenumber);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        if(firebaseAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(MainActivity.this,dashboard.class));
        }
        super.onStart();
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}