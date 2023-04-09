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

public class MainActivity2 extends AppCompatActivity {

    TextView name,firstcardname,secondcardname,adnewcardtxt;
    ImageView card1,card2,adnewcard,home,cards,wallet,contacts,refresh;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID,sname,scard1,scard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        name = findViewById(R.id.textView2);
        userID = fAuth.getCurrentUser().getUid();
        firstcardname = findViewById(R.id.card1text);
        secondcardname = findViewById(R.id.card1text2);
        adnewcardtxt = findViewById(R.id.adnewcards);
        card1 = findViewById(R.id.card2);
        card2 = findViewById(R.id.card1);
        adnewcard = findViewById(R.id.imageView36);
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
                Toast.makeText(MainActivity2.this, "You Are Already Here", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity2.this, "Working On It", Toast.LENGTH_SHORT).show();

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Refreshed Successful ", Toast.LENGTH_SHORT).show();
            }
        });

        adnewcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Coming Soon ", Toast.LENGTH_SHORT).show();
            }
        });

        adnewcardtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Coming Soon ", Toast.LENGTH_SHORT).show();
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




    }
}