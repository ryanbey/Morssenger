package com.goodfellows.morssenger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ConversationsActivity extends AppCompatActivity {
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener authListener;
    private ArrayList<String> contactNames = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();
    private Contact newContact;
    private Button logout;
    private static final String TAG = "ConversationsActivity";
    private TextView userName;
    private TextView timestamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);
        Utils.greenStatusBar(this, R.color.colorMorseGreen); // Green status bar
        Log.d(TAG, "onCreate: started");

        userName = (TextView) findViewById(R.id.tv_userName);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
        {
            String email = user.getEmail();
            userName.setText(email);
        }
        else
        {
            userName.setText("Not signed in");
        }

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(ConversationsActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();
            }
        });

        // Floating action button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make a toast
                Toast.makeText(getApplicationContext(), "Feature not available", Toast.LENGTH_SHORT).show();
            }
        });


        initListItems();
    }

    /*
    Prepares data for list items
     */
    private void initListItems() {
        Log.d(TAG, "initImageListItems: preparing list items");

        // First line
        contactNames.add("All Chat");

        // Second line
        messages.add(getMostRecentMessage());

        initRecyclerView();
    }

    private String getMostRecentMessage() {
        String mostRecentMessage = "";

        return mostRecentMessage;
    }

    /*
    Creates RecyclerView using the adapter
     */
    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(contactNames, messages, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    /*
    Sets timestamp for latest message received
    */
    public void setTimestamp() {
        Message message = new Message();
        String textTime = message.getTextTime();
        // SET TEXT SHOULD USE FIREBASE INFORMATION
        if (textTime != null) {
            timestamp.setText(textTime);
        }
        else {
            timestamp.setText("ERROR");
        }
    }
}
