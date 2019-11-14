package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ConversationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        Utils.greenStatusBar(this, R.color.colorPrimary); // Green status bar
    }
}
