package com.privatebankltd.supbank;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {
    TextView fullname,balance;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    FirebaseUser user;
    String userID,name;

    long Tbalance;
    ImageView home,cards,wallet,contacts,refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullname = findViewById(R.id.textView);
        balance = findViewById(R.id.balance);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        userID = fAuth.getCurrentUser().getUid();

        home = findViewById(R.id.home);
        cards = findViewById(R.id.cards);
        wallet = findViewById(R.id.wallet);
        contacts = findViewById(R.id.contact);
        refresh = findViewById(R.id.refresh);


        DocumentReference documentReferencename77 = fstore.collection("user").document(userID);
        documentReferencename77.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                name = String.valueOf(value.getString("fname"));
                fullname.setText(name);
            }
        });

        DocumentReference documentReferencename7 = fstore.collection("user").document(userID);
        documentReferencename7.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                Tbalance= value.getLong("Balance");
                balance.setText(String.valueOf(Tbalance));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You Are Already Here", Toast.LENGTH_SHORT).show();
            }
        });

        cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            }
        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity3.class));
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity4.class));

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Refreshed Successful ", Toast.LENGTH_SHORT).show();
            }
        });



    }
}