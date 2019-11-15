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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_login);
        userEmail = (EditText) findViewById(R.id.et_email);
        userPassword = (EditText) findViewById(R.id.et_password);
        loginBtn = (Button) findViewById(R.id.button_login);

        getSupportActionBar().hide();

        //firebase - Evans
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        loginBtn = (Button) findViewById(R.id.button_login);

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // launch conversation activity if user is logged in
                    Intent existingUser = new Intent(LoginActivity.this, ConversationsActivity.class);
                    startActivity(existingUser);
                    finish();
                }
            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Wrong email/password!", Toast.LENGTH_SHORT).show();
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

    // Connected to button to bypass sign in to test conversations screen
    public void displayConversationsActivity(View view) {
        Intent iConversations = new Intent(this, ConversationsActivity.class);
        startActivity(iConversations);
    }

    public void displaySignUp(View view) {
        Intent iCreateAccount = new Intent(this, SignUpActivity.class);
        startActivity(iCreateAccount);
    }
}
