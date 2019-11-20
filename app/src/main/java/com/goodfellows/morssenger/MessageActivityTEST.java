package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MessageActivityTEST extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_test);
        Utils.greenStatusBar(this, R.color.colorMorseGreen);
        getSupportActionBar().hide();
    }
}
