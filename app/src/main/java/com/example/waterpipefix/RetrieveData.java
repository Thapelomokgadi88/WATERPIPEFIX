package com.example.waterpipefix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RetrieveData extends AppCompatActivity {
    TextView nameTextView, empIDTextView, qualificationTextView, regionTextView, lastNameTextView, cellNoTextView;
    DatabaseReference rootFirebaseRef = FirebaseDatabase.getInstance().getReference().child("UserData");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);
        nameTextView = findViewById(R.id.textView1);
        qualificationTextView = findViewById(R.id.textView2);
        empIDTextView = findViewById(R.id.textView3);
        lastNameTextView = findViewById(R.id.textView4);
        regionTextView = findViewById(R.id.textView5);
        cellNoTextView = findViewById(R.id.textView6);
       Button btnRetrieve = findViewById(R.id.button2);
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameTextView.setVisibility(View.VISIBLE);
                qualificationTextView.setVisibility(View.VISIBLE);
                empIDTextView.setVisibility(View.VISIBLE);
                lastNameTextView.setVisibility(View.VISIBLE);
                regionTextView.setVisibility(View.VISIBLE);
                cellNoTextView.setVisibility(View.VISIBLE);
                String desiredChild = "user2"; // Specify the desired child here

                rootFirebaseRef.child(desiredChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            HashMap<String, Object> data = (HashMap<String, Object>) snapshot.getValue();
                            if (data != null) {
                                String name = (String) data.get("name");
                                String qualification = (String) data.get("Qualification");
                                int empID = Integer.parseInt(String.valueOf(data.get("Employee Number")));
                                String lastName = (String) data.get("Lastname");
                                String region = (String) data.get("Region");
                                int cellNo = Integer.parseInt(String.valueOf(data.get("CellPhone")));

                                nameTextView.setText(name);
                                qualificationTextView.setText(qualification);
                                empIDTextView.setText(String.valueOf(empID));
                                lastNameTextView.setText(lastName);
                                regionTextView.setText(region);
                                cellNoTextView.setText(String.valueOf(cellNo));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle the error, if any
                    }
                });
            }
        });
    }







}
