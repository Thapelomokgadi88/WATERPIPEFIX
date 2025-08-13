package com.example.waterpipefix;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

public class LandingActivity extends AppCompatActivity {
     private Button buttonNotify;
    private static final int NOTIFICATION_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
       Button createNotification = findViewById(R.id.button);


        buttonNotify = findViewById(R.id.button);


        createNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "JOB alert";
                String description = "Go";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setSmallIcon(R.drawable.baseline_notifications_active_24);
                builder.setContentTitle(title.toString());
                builder.setContentText(description.toString());
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
// TODO: Consider calling
// ActivityCompat#requestPermissions
// here to request the missing permissions, and then overriding
// public void onRequestPermissionsResult(int requestCode, String[] permissions,
// int[] grantResults)
// to handle the case where the user grants the permission. See the documentation
// for ActivityCompat#requestPermissions for more details.
                    return;
                }
                notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
            }
        });



    }

    public void onIcon1Click(View view) {

      startActivity(new Intent(LandingActivity.this, CalendarActivity.class));

    }

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.baseline_notifications_active_24);
        builder.setContentTitle("Task Notification");
        builder.setContentText("You have been assigned at Galeshewe pipe45 in Pitsane road");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    public void onIcon2Click(View view) {
        // Handle icon 2 click event

            startActivity(new Intent(LandingActivity.this, CameraActivity.class));
    }

    public void onIcon3Click(View view) {
        // Handle icon 3 click event
        startActivity(new Intent(LandingActivity.this, Location.class));
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
