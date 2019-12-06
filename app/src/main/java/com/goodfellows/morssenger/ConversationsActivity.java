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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ConversationsActivity extends AppCompatActivity {
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener authListener;
    private static final String TAG = "ConversationsActivity";
    private Contact newContact;
    // Variables
    private ArrayList<String> contactNames = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);
        Utils.greenStatusBar(this, R.color.colorMorseGreen); // Green status bar
        Log.d(TAG, "onCreate: started");


        // Get current user
        user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // launch Login activity if user is NULL
                    Intent notLoggedIn = new Intent(ConversationsActivity.this, LoginActivity.class);
                    startActivity(notLoggedIn);
                    finish();
                }
            }
        };

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

    private void initListItems() {
        Log.d(TAG, "initImageListItems: preparing list items");

        contactNames.add("test");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(contactNames, messages, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        newContact.setName(data.toString());
    }
}
