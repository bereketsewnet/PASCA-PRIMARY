package com.example.pasca_primary;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pasca_primary.Model.DataClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadActivity extends AppCompatActivity {

    private FloatingActionButton uploadButton;
    Dialog SuccessDialog;
    Dialog ErrorDialog;
    private ImageView uploadImage;
    EditText uploadCaption;
    ProgressBar progressBar;
    private Uri imageUri;
    final  private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        //Create the Dialog here
        SuccessDialog = new Dialog(this);
        SuccessDialog.setContentView(R.layout.custom_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SuccessDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        SuccessDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SuccessDialog.setCancelable(false); //Optional
        SuccessDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button Okay = SuccessDialog.findViewById(R.id.btn_okay);

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(UploadActivity.this, "Finish", Toast.LENGTH_SHORT).show();
                SuccessDialog.dismiss();
            }
        });

        // end of success dialog


        //Create the Wrong Dialog here
        ErrorDialog = new Dialog(this);
        ErrorDialog.setContentView(R.layout.custom_wrong_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ErrorDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        ErrorDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ErrorDialog.setCancelable(false); //Optional
        ErrorDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button ErrorOkay = ErrorDialog.findViewById(R.id.btn_okay_error);

        ErrorOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(UploadActivity.this, "Done", Toast.LENGTH_SHORT).show();
                ErrorDialog.dismiss();
            }
        });

        // end of Wrong dialog


        uploadButton = findViewById(R.id.uploadButton);
        uploadCaption = findViewById(R.id.uploadCaption);
        uploadImage = findViewById(R.id.uploadImage);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            imageUri = data.getData();
                            uploadImage.setImageURI(imageUri);
                        } else {
                            Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                } else  {
                    Toast.makeText(UploadActivity.this, "Please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // bottom navitation start
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation2);
        bottomNavigationView.setSelectedItemId(R.id.bottom_student_profile);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_register_list:
                    startActivity(new Intent(getApplicationContext(), RegisterUserListActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_student_profile:
                    return true;
                case R.id.bottom_teacher_profile:
                    startActivity(new Intent(getApplicationContext(), UploadTActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;

            }
            return false;
        });
        //bottom navitation end

    }

    //Outside onCreate
    private void uploadToFirebase(Uri uri){
        String caption = uploadCaption.getText().toString();
        final StorageReference imageReference = storageReference.child("StudentProfile/"+System.currentTimeMillis() + "." + getFileExtension(uri));
        imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        DataClass dataClass = new DataClass(uri.toString(), caption);
                        String key = databaseReference.push().getKey();
                        databaseReference.child(key).setValue(dataClass);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(UploadActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UploadActivity.this, UploadActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(UploadActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                ErrorDialog.show();
            }
        });
    }
    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }

}