package com.example.waterpipefix;





import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LandingAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingadmin);
    }

    public void onIcon1Click(View view) {
        // Handle icon 1 click event
        Toast.makeText(this, "ADD EMPLOYEE", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddEmployees.class);
        startActivity(intent);
        finish();
    }

    public void onIcon2Click(View view) {
        // Handle icon 2 click event

        Toast.makeText(this, "RETRIVE", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RetrieveData.class);
        startActivity(intent);
        finish();
    }

    public void onIcon3Click(View view) {
        // Handle icon 3 click event
        Toast.makeText(this, "VIEW POINT", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ViewLocation.class);
        startActivity(intent);
        finish();

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

