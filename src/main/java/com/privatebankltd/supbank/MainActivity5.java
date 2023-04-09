package com.privatebankltd.supbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {
    TextView mobileNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        mobileNumberTextView = findViewById(R.id.textView);

        String mobileNumber = getIntent().getStringExtra("MOBILE_NUMBER");
        mobileNumberTextView.setText(mobileNumber);
    }
}


