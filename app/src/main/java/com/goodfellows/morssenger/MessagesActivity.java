package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slidein, R.anim.slideout);
        setContentView(R.layout.activity_messages);

        // Layout Stuff
        Utils.greenStatusBar(this, R.color.colorMorseGreen);
        setTitle("Messages");

        Intent intent = getIntent();
    }
}
