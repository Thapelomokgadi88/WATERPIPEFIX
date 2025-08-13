package com.example.waterpipefix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView locationInfoTextView = findViewById(R.id.locationInfoTextView);
        Button showLocationButton = findViewById(R.id.showLocationButton);
        EmpInfo info = new EmpInfo();
        showLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationInfoTextView.setText(info.getEmployee_lat());
                locationTextView.setText(info.getEmployee_Address());
            }
        });
    }
}