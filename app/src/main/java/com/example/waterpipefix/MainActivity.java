package com.example.waterpipefix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button ,admi,hrr,tec,employ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        admi = (Button) findViewById(R.id.admin);
        tec = (Button) findViewById(R.id.tech);
        hrr = (Button) findViewById(R.id.hr);



        admi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click here
                startActivity(new Intent(MainActivity.this, LoginAdmin.class));
            }
        });
        tec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click here
                startActivity(new Intent(MainActivity.this, LoginTech.class));
            }
        });
        hrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click here
                startActivity(new Intent(MainActivity.this, Loginhr.class));
            }
        });
    }
}
