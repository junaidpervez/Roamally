package com.example.roamally;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Find "Let's Go" button
        Button letsGoButton = findViewById(R.id.letsGoButton);

        // Set click listener for the button
        letsGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the next screen
                Intent intent = new Intent(SplashScreenActivity.this, signinscreenActivity.class);
                startActivity(intent);
                finish(); // Close splash screen
            }
        });
    }
}