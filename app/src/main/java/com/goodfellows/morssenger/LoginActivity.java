package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goodfellows.morssenger.ConversationsActivity;
import com.goodfellows.morssenger.R;
import com.goodfellows.morssenger.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fade in from SplashScreen
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        setContentView(R.layout.activity_sign_in);

        // Hide Action Bar
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
