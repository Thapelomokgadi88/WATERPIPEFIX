package com.example.waterpipefix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddEmployees extends AppCompatActivity {
    EditText inputName, inputEmpID, inputQualification, inputRegion, inputLastName, inputCellNo;
    Button btnSubmit;
    DatabaseReference rootFirebaseRef = FirebaseDatabase.getInstance().getReference().child("UserData");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employees2);
        inputName = findViewById(R.id.editText1);
        inputQualification = findViewById(R.id.editText2);
        inputEmpID = findViewById(R.id.editText3);
        inputLastName = findViewById(R.id.editText4);
        inputRegion = findViewById(R.id.editText5);
        inputCellNo = findViewById(R.id.editText6);
        btnSubmit = findViewById(R.id.button1);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long childCount = snapshot.getChildrenCount();
                        String nextChildKey = "user" + (childCount + 1);

                        String name = inputName.getText().toString();
                        String qualification = inputQualification.getText().toString();
                        int empID = Integer.parseInt(inputEmpID.getText().toString());
                        String lastName = inputLastName.getText().toString();
                        String region = inputRegion.getText().toString();
                        int cellNo = Integer.parseInt(inputCellNo.getText().toString());

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("name", name);
                        hashMap.put("Qualification", qualification);
                        hashMap.put("Employee Number", empID);
                        hashMap.put("Lastname", lastName);
                        hashMap.put("Region", region);
                        hashMap.put("CellPhone", cellNo);

                        rootFirebaseRef.child(nextChildKey).setValue(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Data Successfully added", Toast.LENGTH_SHORT).show();
                                        inputName.setText("");
                                        inputCellNo.setText("");
                                        inputEmpID.setText("");
                                        inputQualification.setText("");
                                        inputLastName.setText("");
                                        inputRegion.setText("");
                                    }
                                });
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