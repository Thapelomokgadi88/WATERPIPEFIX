package com.example.waterpipefix;



import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LandingHr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landinghr);
    }

    public void onIcon1Click(View view) {
        // Handle icon 1 click event

    }

    public void onIcon2Click(View view) {
        // Handle icon 2 click event
        startActivity(new Intent(LandingHr.this, CameraActivity.class));
    }

    public void onIcon3Click(View view) {
        // Handle icon 3 click event
        startActivity(new Intent(LandingHr.this, LocationActivity.class));
    }
    public void onIcon4Click(View view) {

        Toast.makeText(this, "Add Employee", Toast.LENGTH_SHORT).show();
    }

    public void onLogoutClick(View view) {
        // Handle logout button click event
        Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show();
        // Add your logout logic here, such as clearing user session and navigating to the login page
        // For example:
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

