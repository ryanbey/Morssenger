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
    private FirebaseAuth auth; //creates fire base Authentication object
    //private FirebaseAuth.AuthStateListener authListener; - dont need remove later
    private Button loginBtn;
    private EditText userEmail, userPassword;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_login);


        userEmail = findViewById(R.id.et_email);
        userPassword = findViewById(R.id.et_password);
        loginBtn = findViewById(R.id.button_login);


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

            //checks to see if email text view is empty and toasts if true
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                updateProgressBar(false);
                return;
            }

            //checks to see if password text view is empty and toasts if true
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                updateProgressBar(false);
                return;
            }


            // Authenticate user with email and password
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // if the user was unable to sign in, send a toast
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Wrong email/password!", Toast.LENGTH_SHORT).show();
                            updateProgressBar(false);
                        }

                        // if sign in is successful start conversationActivity
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


    //ProgressBar circle
    private void updateProgressBar(boolean isOn) {
        ProgressBar pb = findViewById(R.id.progressBar3);
        if (isOn) {
            pb.setVisibility(ProgressBar.VISIBLE);
        }
        else {
            pb.setVisibility(ProgressBar.INVISIBLE);
        }
    }


    //takes user to SignUpActivity
    public void displaySignUp(View view) {
        Intent iCreateAccount = new Intent(this, SignUpActivity.class);
        startActivity(iCreateAccount);
    }
}
