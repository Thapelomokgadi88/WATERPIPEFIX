package com.example.waterpipefix;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class CameraActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 100;
    ImageView imageView;
    Button takePhoto, logout, insert;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ActivityResultLauncher activityResultLauncher;
    private int STORAGE_PERMISSION_CODE = 23;
    private byte[] imageBytes;
    static final int REQUEST_CODE_IMAGE = 101;
    ImageView imageViewAdd;
    EditText inputImageName;
    TextView textViewProgreess;
    ProgressBar progressBar;
    Button btn_Upload;
    Uri imageUri;
    Boolean isImageAdded = false;
    DatabaseReference mref;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        takePhoto = findViewById(R.id.btnPhoto);
        imageViewAdd = findViewById(R.id.imageViewAdd);
        inputImageName = findViewById(R.id.inputImageName);
        textViewProgreess = findViewById(R.id.textViewProgress);
        progressBar = findViewById(R.id.progressBar);
        btn_Upload = findViewById(R.id.btnUpload);


        textViewProgreess.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        mref = FirebaseDatabase.getInstance().getReference().child("Site");
        storageRef = FirebaseStorage.getInstance().getReference().child("SiteImages");

        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("*image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });
        btn_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String imageName = inputImageName.getText().toString();
                if (isImageAdded!=false && imageName!=null){
                    upLoadImage(imageName);
                }
            }
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imageView.setImageBitmap(bitmap);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    imageBytes = byteArrayOutputStream.toByteArray();
                }
            }
        });


        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    activityResultLauncher.launch(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "There is no app that support this action",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void upLoadImage(String imageName) {
        textViewProgreess.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        String key = mref.push().getKey();
        storageRef.child(key + "jpeg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageRef.child(key + "jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("site",imageName);
                        hashMap.put("imageUrl",uri.toString());
                        mref.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Data successfully uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = snapshot.getBytesTransferred()*100/snapshot.getTotalByteCount();
                progressBar.setProgress((int) progress);
                textViewProgreess.setText(progress + "");
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==REQUEST_CODE_IMAGE && data!=null){
            imageUri = data.getData();
            isImageAdded=true;
            imageViewAdd.setImageURI(imageUri);

        }
    }
}


