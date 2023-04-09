package com.privatebankltd.supbank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity3 extends AppCompatActivity {

    TextView name,balance,firstcardname,secondcardname;
    ImageView home,cards,wallet,contacts,refresh;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID,sname,scard1,scard2;

    Long Tbalance;

    Long l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        name = findViewById(R.id.textView2);
        firstcardname = findViewById(R.id.card1text);
        secondcardname = findViewById(R.id.card1text2);
        balance = findViewById(R.id.balance);

        home = findViewById(R.id.home);
        cards = findViewById(R.id.cards);
        wallet = findViewById(R.id.wallet);
        contacts = findViewById(R.id.contact);
        refresh = findViewById(R.id.refresh);



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                Toast.makeText(MainActivity3.this, "You Are Already Here", Toast.LENGTH_SHORT).show();
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity3.this, "Working On It", Toast.LENGTH_SHORT).show();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity3.this, "Refreshed Successful ", Toast.LENGTH_SHORT).show();
            }
        });


        DocumentReference documentReferencenamename = fstore.collection("user").document(userID);
        documentReferencenamename.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                sname = String.valueOf(value.getString("fname"));
                name.setText(sname);
            }
        });

        DocumentReference documentReferencecard1 = fstore.collection("user").document(userID);
        documentReferencecard1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                scard1 = String.valueOf(value.getString("Firstcard"));
                firstcardname.setText(scard1);
            }
        });

        DocumentReference documentReferencecard2 = fstore.collection("user").document(userID);
        documentReferencecard2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                scard2 = String.valueOf(value.getString("Secondcard"));
                secondcardname.setText(scard2);
            }
        });

        DocumentReference documentReferencebalance = fstore.collection("user").document(userID);
        documentReferencebalance.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                Tbalance = value.getLong("Balance");
                balance.setText(String.valueOf(Tbalance));
            }
        });
    }
}