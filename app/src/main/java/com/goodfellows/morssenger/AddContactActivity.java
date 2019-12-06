package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {

    // the variables for the input and button
    private Button newContact;
    private EditText contactName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        getSupportActionBar().hide();

        // linking the button and edit text to the variables
        newContact = (Button) findViewById(R.id.addContact);
        contactName = (EditText) findViewById(R.id.et_recipient);

        // listener for when the newContact button is clicked
        newContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // creates an intent and adds the new contact
                Intent intent = new Intent();
                intent.putExtra("CONTACT", contactName.getText().toString());

                // sets the result and sends it back
                setResult(0, intent);
                finish();
            }
        });


    }
}
