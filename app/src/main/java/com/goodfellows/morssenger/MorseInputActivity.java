package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MorseInputActivity extends AppCompatActivity {

    String morseMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_input);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xfafafa));
        setTitle("");
    }

//    A = .-    B = -...  C = -.-.
//    D = -..   E = .     F = ..-.
//    G = --.   H = ....  I = ..
//    J = .---  K = -.-   L = .-..
//    M = --    N = -.    O = ---
//    P = .--.  Q = --.-  R = .-.
//    S = ...   T = -     U = ..-
//    V = ...-  W = .--   X = -..-
//    Y = -.--  Z = --..

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_glossary_menu, menu);
        return true;
    }

    /*
    DISPLAYS . OR - DEPENDING ON BUTTON PRESS OR HOLD
    */
    public void displayMessage(View view) {
        final TextView tv = findViewById(R.id.tv_morse_input);

        // Long Press
        Button button = findViewById(R.id.btn_morse_input_dot);
        button.setOnLongClickListener(
                 (v)-> {
                     morseMessage = morseMessage + "-";
                     tv.setText(morseMessage);

                     return true;
                    }
        );

        // Short Press
        morseMessage = morseMessage + '.';
        tv.setText(morseMessage);
    }

    /*
    DISPLAYS SLASH
    */
    public void displaySlash(View view) {

        // Calls function to translate character that was just typed into English
        translateString(view);

        TextView tv = findViewById(R.id.tv_morse_input);
        morseMessage = morseMessage + " / ";
        tv.setText(morseMessage);
    }

    /*
    DISPLAYS SPACE FOR NEXT CHARACTER
    */
    public void displaySpace(View view) {

        // Calls function to translate character that was just typed into English
        translateString(view);

        TextView tv = findViewById(R.id.tv_morse_input);
        morseMessage = morseMessage + " ";
        tv.setText(morseMessage);
    }

    /*
    CLEARS MESSAGE
    */
    public void clearMessage (View view) {

        morseMessage = "";

        // Calls function to translate character that was just typed into English
        translateString(view);

        TextView tv = findViewById(R.id.tv_morse_input);
        tv.setText(morseMessage);
    }

    /*
    Deletes last character
    */
    public void backspace(View view) {

        // Calls function to translate character that was just typed into English
        translateString(view);

        TextView tv = findViewById(R.id.tv_morse_input);

        String newMessage = "";

        // Add capability to delete " / " and " "

        if (morseMessage != null && morseMessage.length() > 0) {
            newMessage = morseMessage.substring(0, morseMessage.length() - 1);
        }

        morseMessage = newMessage;
        tv.setText(morseMessage);
    }

    /*
    Translates the character that was just typed into English
    */
    public void translateString(View view) {

        String englishMessage;
        TextView tv = findViewById(R.id.tv_translated_mesasge);

        // Convert the character to English
        Translator translator = new Translator();
        englishMessage = translator.ConvertToEnglish(morseMessage);

        tv.setAllCaps(true);
        tv.setText(englishMessage);
    }

    /*
    Sends english message to MessagesActivity editText
    */
    public void sendTranslatedMessage(View view) {

        // Makes sure the user's last character is included
        displaySpace(view);

        TextView message = findViewById(R.id.tv_translated_mesasge);
        Intent i = new Intent();

        // Convert TextView to a string and capitalize it
        String translatedMessage = message.getText().toString();
        String capTranslatedMessage = translatedMessage.substring(0, 1).toUpperCase() + translatedMessage.substring(1);

        // Send result back to MessagesActivity
        i.putExtra("MESSAGE", capTranslatedMessage);
        setResult(0, i);

        // Kill MorseInputActivity
        finish();
    }
}



