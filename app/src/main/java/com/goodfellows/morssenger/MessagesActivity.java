package com.goodfellows.morssenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    boolean myMessage = true;
    private List<MessageBubble> MessageBubbles;
    private ArrayAdapter<MessageBubble> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        // Layout Stuff
        Utils.greenStatusBar(this, R.color.colorMorseGreen);
        setTitle("Contact Name");

        MessageBubbles = new ArrayList<>();

        ListView messagesListView = (ListView) findViewById(R.id.messages_list_view);
        View buttonSend = findViewById(R.id.btn_send_message);
        EditText et = (EditText) findViewById(R.id.et_enter_message);

        // Set ListView adapter
        adapter = new MessageAdapter(this, R.layout.their_message, MessageBubbles);
        messagesListView.setAdapter(adapter);

        // Send button
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().trim().equals("")) {
                    Toast.makeText(MessagesActivity.this, "Input some text!", Toast.LENGTH_SHORT).show();
                } else {
                    // Add message to list
                    MessageBubble messageBubble = new MessageBubble(et.getText().toString(), myMessage);
                    MessageBubbles.add(messageBubble);
                    adapter.notifyDataSetChanged();
                    et.setText("");
                    if (myMessage) {
                        myMessage = true;
                    }
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    public void getTranslatedMessage(String translatedMessage) {
        EditText et = findViewById(R.id.et_enter_message);
        et.setText(translatedMessage);
    }

    public void displayMorseInputActivity(View view) {
            Intent i = new Intent(this, MorseInputActivity.class);
            startActivityForResult(i, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText et = findViewById(R.id.et_enter_message);
        et.setText(data.getStringExtra("MESSAGE"));
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Sound selected", Toast.LENGTH_SHORT).show();
//                Button playBeepSound = (Button) this.findViewById(R.id.play);
//                Button playBeepSound = this.findViewById(R.id.button);
//                playBeepSound.setOnClickListener(
////                        view on click listener;
//                        (v) -> {
//                            EditText morseText = findViewById(R.id.morse);
//                            String morseCode = morseText.getText().toString();
//                            MorseMediaPlayer player = new MorseMediaPlayer(morseCode, this);
//                            player.note();
//                        });
                return true;

            case R.id.item2:
                Toast.makeText(this, "Vibraton selected", Toast.LENGTH_SHORT).show();

//                Button playVibration = this.findViewById(R.id.button);
//                playVibration.setOnClickListener(
////                view on click listener;
//                        (v) -> {
//                            EditText morseText = findViewById(R.id.morse);
//                            String morseCode = morseText.getText().toString();
//                            MorseVibrationPlayer player = new MorseVibrationPlayer(morseCode, this);
//                            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//                            player.vibration();
//                        });

                return true;

            case R.id.item3:
//                both sound and vibration
                Toast.makeText(this, "Sound and Vibration selected", Toast.LENGTH_SHORT).show();
//
//                Button playSoundAndVibration = this.findViewById(R.id.button);
//                playSoundAndVibration.setOnClickListener(
////                        view on click listener;
//                        (v) -> {
//                            EditText morseText = findViewById(R.id.morse);
//                            String morseCode = morseText.getText().toString();
//                            MorseMediaPlayer player = new MorseMediaPlayer(morseCode, this);
//                            player.note();
//
//                            MorseVibrationPlayer player = new MorseVibrationPlayer(morseCode, this);
//                            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//                            player.vibration();
//                        });
                return true;

            case R.id.item4:
//                Meant to make it so that the button will go back to doing nothing
                Toast.makeText(this, "None selected", Toast.LENGTH_SHORT).show();

//                 Button nothing = (Button) this.findViewById(R.id.play);
//
//                nothing.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                });

                return true;

            case R.id.item5:
                //Toast.makeText(this, "Glossery selected", Toast.LENGTH_SHORT).show();

                // Create an Intent to start the second activity
                Intent glossaryIntent = new Intent(this, GlossaryActivity.class);

                // Start the new activity.
                startActivity(glossaryIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
