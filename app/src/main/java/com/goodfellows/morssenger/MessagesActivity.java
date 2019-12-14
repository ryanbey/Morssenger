package com.goodfellows.morssenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MessagesActivity extends AppCompatActivity {

    boolean myMessage = true;
    public List<MessageBubble> messageBubbles;
    private ArrayAdapter<MessageBubble> adapter;
    private int choice;
    public String textTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        // Layout Stuff
        Utils.greenStatusBar(this, R.color.colorMorseGreen);

        // Change to show actual name later
        setTitle("All Chat");

        messageBubbles = new ArrayList<>();

        ListView messagesListView = findViewById(R.id.messages_list_view);
        View buttonSend = findViewById(R.id.btn_send_message);
        EditText et = findViewById(R.id.et_enter_message);

        // Set ListView adapter
        adapter = new MessageAdapter(this, R.layout.their_message, messageBubbles);
        messagesListView.setAdapter(adapter);

        // creates a reference on firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference messages = database.getReferenceFromUrl("https://morssenger-158ad.firebaseio.com/");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("FireBase", dataSnapshot.getKey());
                Message newMessage = dataSnapshot.getValue(Message.class);
                Log.d("FireBaseText", "text=" + newMessage.getText());
                Log.d("checkTime", "time= " + newMessage.getTextTime());

                if (newMessage.getTextSender() != null) {
                    Log.d("checkEmailOne", "emailOne= " + newMessage.getTextSender().toLowerCase().trim());
                    Log.d("checkEmailTwo", "emailTwo= " + FirebaseAuth.getInstance().getCurrentUser().getEmail().toLowerCase().trim());

                    if (newMessage.getTextSender().toLowerCase().trim().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail().toLowerCase().trim()) == true)
                    {
                        myMessage = true;
                    }
                    else
                    {
                        myMessage = false;
                        Notification notification = new Notification();
                        notification.sendNotification(getContext());
                    }

                    MessageBubble messageBubble = new MessageBubble(newMessage.getText(), newMessage.getTextSender(), myMessage);
                    messageBubbles.add(messageBubble);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        messages.addChildEventListener(childEventListener);

        messagesListView.setOnItemClickListener(
            (parent, view, position, id) ->{
            // Sound
            if (choice == 1) {
                Translator translator = new Translator();
                String morseCode = translator.ConvertToMorse(messageBubbles.get(position).getContent());
                // Need to convert message into a string then give it to the program.
                MorseMediaPlayer player = new MorseMediaPlayer(morseCode,this);
                player.note();
            }
            // Vibration
            else if (choice == 2) {
                Translator translator = new Translator();
                String morseCode = translator.ConvertToMorse(messageBubbles.get(position).getContent());

                MorseVibrationPlayer player = new MorseVibrationPlayer(morseCode, this);
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                player.vibration();
            }
            // Sound and vibration
            else if (choice == 3) {
                Translator translator = new Translator();
                String morseCode = translator.ConvertToMorse(messageBubbles.get(position).getContent());

                MorseBothPlayer player = new MorseBothPlayer(morseCode, this);
                Vibrator both = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                player.both();
            }
            // Do nothing
            else if (choice == 4) {
            }
        });

    }

    /*
    send button
     */
    public void sendMessage(View view){

        // links message input to et
        EditText et = findViewById(R.id.et_enter_message);

        // error checking to make sure there is something to send in the first place
        if (et.getText().toString().trim().equals("")) {
            Toast.makeText(MessagesActivity.this, "Input some text!", Toast.LENGTH_SHORT).show();
        } else {

            // Gets message time
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            textTime = sdf.format(new Date());

            // puts the message into firebase with message, sender email, and time
            String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            FirebaseDatabase.getInstance().getReference().push().setValue(new Message(et.getText().toString(), userEmail, textTime));

            // sets the edit text back to null
            et.setText("");

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    /*
    CREATES OPTIONS MENU
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    /*
    GETS ENGLISH MESSAGE
     */
    public void getTranslatedMessage(String translatedMessage) {
        EditText et = findViewById(R.id.et_enter_message);
        et.setText(translatedMessage);
    }

    /*
    STARTS MORSE INPUT ACTIVITY EXPECTING RESULT
     */
    public void displayMorseInputActivity(View view) {
            Intent i = new Intent(this, MorseInputActivity.class);
            startActivityForResult(i, 0);
    }

    /*
    ACCEPTS RESULT FROM MORSE INPUT ACTIVITY
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText et = findViewById(R.id.et_enter_message);
        et.setText(data.getStringExtra("MESSAGE"));
    }

    /*
    DISPLAYS TOAST ON OPTION PRESS
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Sound
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

    public boolean fromCurrentUser()
    {
        return true;
    }


}
