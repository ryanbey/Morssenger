package com.goodfellows.morssenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ProgressBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth; //creates fire base Authentication object -Evans
    private FirebaseAuth.AuthStateListener authListener;
    private Button loginBtn;
    private EditText userEmail, userPassword;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_login);

        userEmail = (EditText) findViewById(R.id.et_email);
        userPassword = (EditText) findViewById(R.id.et_password);
        loginBtn = (Button) findViewById(R.id.button_login);

        getSupportActionBar().hide();
        updateProgressBar(false);

        // Firebase
        // Get firebase auth instance
        auth = FirebaseAuth.getInstance();
        loginBtn = (Button) findViewById(R.id.button_login);

        //if user is signed in, go to conversation activity
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // launch conversation activity if user is logged in
            Intent existingUser = new Intent(LoginActivity.this, ConversationsActivity.class);
            startActivity(existingUser);
            finish();
        }

            loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            updateProgressBar(true);
            String email = userEmail.getText().toString();
            final String password = userPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                updateProgressBar(false);
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                updateProgressBar(false);
                return;
            }

            // Authenticate user
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Wrong email/password!", Toast.LENGTH_SHORT).show();
                            updateProgressBar(false);
                        }
                        else {
                            Intent intent = new Intent(LoginActivity.this, ConversationsActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }

    private void updateProgressBar(boolean isOn) {
        ProgressBar pb = findViewById(R.id.progressBar3);
        if (isOn) {
            pb.setVisibility(ProgressBar.VISIBLE);
        }
        else {
            pb.setVisibility(ProgressBar.INVISIBLE);
        }
    }

    // Temporary button to bypass sign in to test conversations screen
    //public void displayConversationsActivity(View view) {
    //   Intent iConversations = new Intent(this, ConversationsActivity.class);
    //    startActivity(iConversations);
    //}

    public void displaySignUp(View view) {
        Intent iCreateAccount = new Intent(this, SignUpActivity.class);
        startActivity(iCreateAccount);
    }
}
