package com.privatebankltd.supbank;


import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity implements ContactListAdapter.OnContactClickListener {



    private RecyclerView mRecyclerView;
    private ContactListAdapter mAdapter;
    private final ArrayList<String> contactNames = new ArrayList<>();
    private final ArrayList<String> contactNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mRecyclerView = findViewById(R.id.contact_list);
        mAdapter = new ContactListAdapter(this, contactNames, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadContacts();
    }

    private void loadContacts() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if (cursor != null) {
            int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameColumnIndex);
                String number = cursor.getString(numberColumnIndex);
                contactNames.add(name);
                contactNumbers.add(number);
            }

            cursor.close();
        }

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onContactClick(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ContactFragment fragment = new ContactFragment();
        String number = contactNumbers.get(position);
        String name = contactNames.get(position);
        Bundle bundle = new Bundle();
        bundle.putString(ContactFragment.ARG_NUMBER, number);
        bundle.putString(ContactFragment.FRAGMENT_TAG, name);
        fragment.setArguments(bundle);
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}


