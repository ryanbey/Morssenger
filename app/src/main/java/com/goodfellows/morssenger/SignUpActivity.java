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

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth; //creates fire base Authentication object -Evans
    private Button buttonSignUp;
    private EditText nEmail, nPassword, nFirstName, nLastName, authPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        //Get Firebase auth instance -Evans
        auth = FirebaseAuth.getInstance();

        //set button/editText to respective IDs -Evans
        buttonSignUp = (Button) findViewById(R.id.button_sign_up3);
        nEmail = (EditText) findViewById(R.id.et_email);
        nPassword = (EditText) findViewById(R.id.et_password);
        authPassword = (EditText) findViewById(R.id.et_password_confirm);
        nFirstName = (EditText) findViewById(R.id.et_first_name);
        nLastName = (EditText) findViewById(R.id.et_last_name);


        //
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sets user input to strings
                final String email = nEmail.getText().toString().trim();
                final String password = nPassword.getText().toString().trim();
                final String fname = nFirstName.getText().toString().trim();
                final String lname = nLastName.getText().toString().trim();
                final String confirmPassword = authPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {  //makes sure password is grater than 6 characters -Evans
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) //makes sure password matches -Evans
                {
                    Toast.makeText(getApplicationContext(), "Password does not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),
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
    //sends new user information to firebase.
    private void addUserTofirebase(String email, String password, String fname, String lname){

        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(fname);
        user.setLastName(lname);

        auth.createUserWithEmailAndPassword(user.email, user.password);
    }
}
