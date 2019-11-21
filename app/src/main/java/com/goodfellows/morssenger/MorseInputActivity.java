package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MorseInputActivity extends AppCompatActivity {

    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_input);
        getSupportActionBar().hide();
    }

    /*
    DISPLAYS . OR - DEPENDING ON BUTTON PRESS OR HOLD
    */
    public void displayMessage(View view) {
        final TextView tv = findViewById(R.id.tv_morse_input);

        // Long Press
        Button button = findViewById(R.id.btn_morse_input_dot);
        button.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick (View v) {
                        message = message + "-";
                        tv.setText(message);
                        return true;
                    }
                }
        );

        // Short Press
        message = message + '.';
        tv.setText(message);
    }

    /*
    DISPLAYS SLASH
    */
    public void displaySlash(View view) {
        TextView tv = findViewById(R.id.tv_morse_input);
        message = message + " / ";
        tv.setText(message);
    }

    /*
    DISPLAYS SPACE FOR NEXT CHARACTER
    */
    public void displaySpace(View view) {
        TextView tv = findViewById(R.id.tv_morse_input);
        message = message + " ";
        tv.setText(message);
    }

    /*
    CLEARS MESSAGE
    */
    public void clearMessage (View view) {
        TextView tv = findViewById(R.id.tv_morse_input);
        message = "";
        tv.setText(message);
    }

    /*
    Deletes last character
    */
    public void backspace(View view) {

        TextView tv = findViewById(R.id.tv_morse_input);

        String newMessage = "";

        // TODO: Add capability to delete " / " and " "

        if (message != null && message.length() > 0) {
            newMessage = message.substring(0, message.length() - 1);
        }

        message = newMessage;
        tv.setText(message);
    }

    public void translateCharacter(View view) {

    }
}



