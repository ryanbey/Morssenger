package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.login_activity);

        getSupportActionBar().hide();
    }

    public void displayConversationActivity(View view) {
        Intent iConversation = new Intent(this, ConversationsActivity.class);
        startActivity(iConversation);
    }

    public void displayCreateAccount(View view) {
        Intent iCreateAccount = new Intent(this, SignUpActivity.class);
        startActivity(iCreateAccount);
    }
}
