package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slidein, R.anim.slideout);
        setContentView(R.layout.activity_messages);

        // Layout Stuff
        Utils.greenStatusBar(this, R.color.colorMorseGreen);
        setTitle("Contact Name");

        Intent intent = getIntent();
    }

    public void displayMorseInputActivity(View view) {

        Intent iMorseInput = new Intent(this, MorseInputActivity.class);
        startActivity(iMorseInput);
    }
}
