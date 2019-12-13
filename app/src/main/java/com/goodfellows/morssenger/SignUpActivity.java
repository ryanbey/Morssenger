package com.goodfellows.morssenger;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth; //creates fire base Authentication object
    private Button buttonSignUp;
    private EditText nEmail, nPassword, nFirstName, nLastName, authPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();


        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        updateProgressBar(false);


        // Set button/editText to respective IDs
        buttonSignUp = findViewById(R.id.button_sign_up3);
        nEmail = findViewById(R.id.et_email);
        nPassword = findViewById(R.id.et_password);
        authPassword = findViewById(R.id.et_password_confirm);
        nFirstName = findViewById(R.id.et_first_name);
        nLastName = findViewById(R.id.et_last_name);


        //when user inputs their data, buttonSignUp uses the input to create a new user when clicked
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProgressBar(true);
                // Sets user input to strings
                final String email = nEmail.getText().toString().trim();
                final String password = nPassword.getText().toString().trim();
                final String fname = nFirstName.getText().toString().trim();
                final String lname = nLastName.getText().toString().trim();
                final String confirmPassword = authPassword.getText().toString().trim();

                //Error handling
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Empty email", Toast.LENGTH_SHORT).show();
                    updateProgressBar(false);
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Empty password", Toast.LENGTH_SHORT).show();
                    updateProgressBar(false);
                    return;
                }

                if (password.length() < 6) {  //makes sure password is grater than 6 characters -Evans
                    Toast.makeText(getApplicationContext(), "Password must be 6 characters long", Toast.LENGTH_SHORT).show();
                    updateProgressBar(false);
                    return;
                }

                if (!password.equals(confirmPassword)) //makes sure password matches -Evans
                {
                    Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
                    updateProgressBar(false);
                    return;
                }

                // Create user
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(SignUpActivity.this, "Account created" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                updateProgressBar(false);
                                Toast.makeText(SignUpActivity.this, "Authentication failed" + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                addUserTofirebase(email, password, fname, lname);
                                startActivity(new Intent(SignUpActivity.this, ConversationsActivity.class));
                                finish();
                            }
                        }
                    });
            }
        });
    }


    // Sends new user information to firebase
    private void addUserTofirebase(String email, String password, String fname, String lname){

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                User user = new User();

                user.setEmail(email);
                user.setPassword(password);
                user.setFirstName(fname);
                user.setLastName(lname);

                FirebaseUser FBuser = auth.getCurrentUser();
                String uid = FBuser.getUid();
                FirebaseDatabase.getInstance().getReference("users/" + uid).setValue(user);
            }
        });
    }


    //ProgressBar circle
    private void updateProgressBar(boolean isOn) {

        ProgressBar pb = findViewById(R.id.progressBar);
        if (isOn) {
            pb.setVisibility(ProgressBar.VISIBLE);
        }

        else {
             pb.setVisibility(ProgressBar.INVISIBLE);
        }
    }
}
