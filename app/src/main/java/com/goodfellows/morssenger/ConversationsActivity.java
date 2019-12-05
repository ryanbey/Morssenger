package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ConversationsActivity extends AppCompatActivity {

    private static final String TAG = "ConversationsActivity";

    // Variables
    private ArrayList<String> contactNames = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);
        Utils.greenStatusBar(this, R.color.colorMorseGreen); // Green status bar
        Log.d(TAG, "onCreate: started");

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

        contactNames.add("Test");
        contactNames.add("Contacasdt Name");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(contactNames, messages, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    protected void onActivityResult()
    {

    }
}
