package com.goodfellows.morssenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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
                // Open new message activity
                Intent intent = new Intent(ConversationsActivity.this, AddContactActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        initListItems();
    }

    /*
    Prepares data for list items
     */
    private void initListItems() {
        Log.d(TAG, "initImageListItems: preparing list items");

        contactNames.add("Test Conversation");

        initRecyclerView();
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
    Gets result from AddContactActivity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        newContact.setName(data.toString());
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
