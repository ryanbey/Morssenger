package com.goodfellows.morssenger;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
RECYCLER VIEW ADAPTER
This class adapts the list item layout to something the recycler view can read and use
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    // Tag for log messages
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> contactNames = new ArrayList<>();
    private ArrayList<String> newestMessage = new ArrayList<>();
    private Context context;

    // Default Constructor
    public RecyclerViewAdapter(ArrayList<String> contactNames, ArrayList<String> newestMessage, Context context) {
        this.contactNames = contactNames;
        this.newestMessage = newestMessage;
        this.context = context;
    }


    // Recycles the ViewHolders and puts them into the positions they are supposed to be in
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Handles different views with their descriptions
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contactName;
        TextView newestMessage;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View view) {
            super(view);
            contactName = view.findViewById(R.id.tv_contact_name);
            newestMessage = view.findViewById(R.id.tv_newest_message);
            parentLayout = view.findViewById(R.id.parent_layout);

        }
    }

    // Handles populating recycler view and handles onClicks
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Log.d(TAG, "onBindViewHolder: called");

        // Populates the top line in the recycler view
        holder.contactName.setText(contactNames.get(position));
        holder.newestMessage.setText(newestMessage.get(position));

        // Starts messages activity
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + contactNames.get(position));
                //Toast.makeText(context, contactNames.get(position), Toast.LENGTH_SHORT).show();

                // Start messages activity
                Intent intent = new Intent(context, MessagesActivity.class);
                context.startActivity(intent);
            }
        });
    }


    // Tells RecyclerViewAdapter how many items are in the list so it knows how many to display
    @Override
    public int getItemCount() {
        return contactNames.size();
    }
}
