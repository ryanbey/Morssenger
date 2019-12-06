package com.goodfellows.morssenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    boolean myMessage = true;
    private List<MessageBubble> MessageBubbles;
    private ArrayAdapter<MessageBubble> adapter;
    private int choice;
    private String TAG = "MessagesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        // Layout Stuff
        Utils.greenStatusBar(this, R.color.colorMorseGreen);
        // Change to show actual name later
        setTitle("Contact Name");

        MessageBubbles = new ArrayList<>();

        // creates a reference on firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        // Read from the reference on database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                Notification notification = new Notification();
                notification.sendNotification("","rando",getContext());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        ListView messagesListView = (ListView) findViewById(R.id.messages_list_view);
        View buttonSend = findViewById(R.id.btn_send_message);
        EditText et = (EditText) findViewById(R.id.et_enter_message);

        // Set ListView adapter
        adapter = new MessageAdapter(this, R.layout.their_message, MessageBubbles);
        messagesListView.setAdapter(adapter);

        messagesListView.setOnItemClickListener(

            (parent, view, position, id) ->{
            // Sound
            if (choice == 1) {
                Translator translator = new Translator();
                String morseCode = translator.ConvertToMorse(MessageBubbles.get(position).getContent());
                // Need to convert message into a string then give it to the program.
                MorseMediaPlayer player = new MorseMediaPlayer(morseCode,this);
                player.note();
            }
            // Vibration
            else if (choice == 2) {
                Translator translator = new Translator();
                String morseCode = translator.ConvertToMorse(MessageBubbles.get(position).getContent());
                MorseVibrationPlayer player = new MorseVibrationPlayer(morseCode, this);
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                player.vibration();
            }
            // Sound and vibration
            else if (choice == 3) {
                Translator translator = new Translator();
                String morseCode = translator.ConvertToMorse(MessageBubbles.get(position).getContent());

                MorseMediaPlayer player = new MorseMediaPlayer(morseCode, this);
                player.note();

                MorseVibrationPlayer player2 = new MorseVibrationPlayer(morseCode, this);
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                player2.vibration();
            }
            // Do nothing
            else if (choice == 4) {
            }
        });

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

                    // Sets a new reference in firebase with a string from messages
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("message");

                    myRef.setValue(et.getText().toString());

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
            //Sound
            case R.id.item1:
                Toast.makeText(this, "Sound selected", Toast.LENGTH_SHORT).show();
                choice = 1;
                return true;
            // Vibration
            case R.id.item2:
                Toast.makeText(this, "Vibration selected", Toast.LENGTH_SHORT).show();
                choice = 2;
                return true;
            // Sound and vibration
            case R.id.item3:
                Toast.makeText(this, "Sound and Vibration selected", Toast.LENGTH_SHORT).show();
                choice = 3;
                return true;
            // Nothing
            case R.id.item4:
                Toast.makeText(this, "None selected", Toast.LENGTH_SHORT).show();
                choice = 4;
                return true;
            // Start GlossaryActivity
            case R.id.item5:
                Intent glossaryIntent = new Intent(this, GlossaryActivity.class);
                startActivity(glossaryIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    Context getContext(){
        return this;
    }
}
