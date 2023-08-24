package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pasca_primary.Model.pdfClass;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadPdfActivity extends AppCompatActivity {

    Button upload_btn;
    Dialog SuccessDialog;
    EditText pdf_name;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf_activity);

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

                Toast.makeText(UploadPdfActivity.this, "Finish", Toast.LENGTH_SHORT).show();
                SuccessDialog.dismiss();
            }
        });

        // end of success dialog

        upload_btn = findViewById(R.id.upload_pdf);
        pdf_name = findViewById(R.id.name_pdf);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("UploadsPdf");

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFiles();
            }
        });

    }

    private void selectFiles() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF Files..."),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data!= null && data.getData()!= null){
            UploadFiles(data.getData());
        }
    }

    private void UploadFiles(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference reference = storageReference.child("Uploads/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri url = uriTask.getResult();

                        pdfClass pdfClass = new pdfClass(pdf_name.getText().toString(),url.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(pdfClass);

                        Toast.makeText(UploadPdfActivity.this, "Book Uploaded!!", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                        SuccessDialog.show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        double progress = (100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded:"+(int)progress+"%");

                    }
                });
    }
}