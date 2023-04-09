package com.privatebankltd.supbank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ContactFragment extends Fragment {

    public static final String ARG_NUMBER = "ARG_NUMBER";
    public static final String FRAGMENT_TAG = "CONTACT_FRAGMENT";
    private FrameLayout fragmentContainer;
    private TextView mNameTextView;
    private TextView mNumberTextView;
    private EditText mAmountEditText;
    private Button mPayButton;

    private String mName;
    private String mNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString("CONTACT_FRAGMENT");
            mNumber = getArguments().getString("ARG_NUMBER");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        mNameTextView = view.findViewById(R.id.contact_name);
        mNumberTextView = view.findViewById(R.id.contact_number);
        mAmountEditText = view.findViewById(R.id.amount_edit_text);
        mPayButton = view.findViewById(R.id.pay_button);

        mNameTextView.setText(mName);
        mNumberTextView.setText(mNumber);

        mPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = mAmountEditText.getText().toString().trim();
                if (TextUtils.isEmpty(amount)) {
                    Toast.makeText(getActivity(), "Please enter amount", Toast.LENGTH_SHORT).show();
                } else {
                    Uri uri = new Uri.Builder()
                            .scheme("upi")
                            .authority("pay")
                            .appendQueryParameter("pa", mNumber)
                            .appendQueryParameter("pn", mName != null ? mName : "")
                            .appendQueryParameter("tn", "Payment")
                            .appendQueryParameter("am", amount)
                            .appendQueryParameter("cu", "INR")
                            .build();

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(uri);
                    intent.setPackage("com.google.android.apps.nbu.paisa.user");

                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }
        });

        return view;
    }
}

