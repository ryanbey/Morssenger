package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    public void getTranslatedMessage(String translatedMessage) {
        EditText et = findViewById(R.id.et_enter_message);
        et.setText(translatedMessage);
    }

    public void displayMorseInputActivity(View view) {

        Intent iMorseInput = new Intent(this, MorseInputActivity.class);
        startActivity(iMorseInput);
    }
}
