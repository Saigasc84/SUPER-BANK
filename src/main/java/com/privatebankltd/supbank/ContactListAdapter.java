package com.privatebankltd.supbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mContactNames;
    private OnContactClickListener mListener;

    public interface OnContactClickListener {
        void onContactClick(int position);
    }

    public ContactListAdapter(Context context, ArrayList<String> contactNames, OnContactClickListener listener) {
        mContext = context;
        mContactNames = contactNames;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.contact_item_layout, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = mContactNames.get(position);
        holder.nameTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        return mContactNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        private OnContactClickListener mListener;

        public ViewHolder(View itemView, OnContactClickListener listener) {
            super(itemView);
            mListener = listener;
            nameTextView = itemView.findViewById(R.id.nameTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onContactClick(getAdapterPosition());
            }
        }
    }
}

