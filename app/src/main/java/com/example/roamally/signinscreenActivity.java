package com.example.roamally;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signinscreenActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private TextView signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_screen);

        // Initialize views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password_signin);
        signInButton = findViewById(R.id.sign_in_button);
        signUpLink = findViewById(R.id.sign_up_link);

        // Set click listener for sign-in button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Static credentials check
                if (username.equals("admin") && password.equals("password")) {
                    // Successful login
                    Toast.makeText(signinscreenActivity.this, "Sign-in successful!", Toast.LENGTH_SHORT).show();

                    // Redirect to the main or home activity (if exists)
                     Intent intent = new Intent(signinscreenActivity.this, HomeActivity.class);
                     startActivity(intent);
                } else {
                    // Failed login
                    Toast.makeText(signinscreenActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for sign-up link
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to CreateAccountActivity
                Intent intent = new Intent(signinscreenActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
