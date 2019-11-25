package com.goodfellows.morssenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Sound selected", Toast.LENGTH_SHORT).show();;
//                Button playBeepSound = (Button) this.findViewById(R.id.play);

                return true;

            case R.id.item2:
                Toast.makeText(this, "Vibraton selected", Toast.LENGTH_SHORT).show();

//                Button buzz = (Button) this.findViewById(R.id.play);

                return true;

            case R.id.item3:
//                both sound and vibration
                Toast.makeText(this, "Sound and Vibration selected", Toast.LENGTH_SHORT).show();

//                Button play = (Button) this.findViewById(R.id.play);

                return true;

            case R.id.item4:
//                Meant to make it so that the button will go back to doing nothing
                Toast.makeText(this, "None selected", Toast.LENGTH_SHORT).show();

//                Button nothing = (Button) this.findViewById(R.id.play);

                return true;

            case R.id.item5:
                Toast.makeText(this, "Glossery selected", Toast.LENGTH_SHORT).show();
//
//                // Create an Intent to start the second activity
//                Intent glosseryIntent = new Intent(this, GlossaryMenu.class);
//
//                // Start the new activity.
//                startActivity(glosseryIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
