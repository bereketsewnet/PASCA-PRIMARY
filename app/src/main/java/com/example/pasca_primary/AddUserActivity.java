package com.example.pasca_primary;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.List;

public class AddUserActivity extends AppCompatActivity {
    Button add_account;
    MaterialEditText add_userId, add_userpassword, add_userName;
    Spinner user_counter_spinner;
    String user_counter_spinner_sotre, pathNameStorege, pathNameStoregeEmail, pathUserName;
    private static final String TAG = "PERMISSION_TAG";
    private static final int STORAGE_PERMISSION_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Permissions();


        add_userId = findViewById(R.id.add_userId);
        add_userpassword = findViewById(R.id.add_userpassword);
        add_userName = findViewById(R.id.add_userName);
        add_account = findViewById(R.id.add_account);
        user_counter_spinner = findViewById(R.id.user_counter_spinner);


        ArrayAdapter<CharSequence> adapter_user_counter = ArrayAdapter.createFromResource(this, R.array.counter_user, android.R.layout.simple_spinner_item);
        adapter_user_counter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_counter_spinner.setAdapter(adapter_user_counter);


        add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkPermission()){
                    Log.d(TAG,"onClick: Permission already granted...");
                    cerateFile();
                }else{
                    Log.d(TAG,"onClick: Permission was not granted, request...");
                    requestPermission();
                }

            }
        });


    }

    //for cerate file
    private void cerateFile(){

        String email, password, name;
        email = add_userId.getText().toString();
        password = add_userpassword.getText().toString();
        name = add_userName.getText().toString();

        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {

            Toast.makeText(AddUserActivity.this, "All Are Required!!", Toast.LENGTH_SHORT).show();
        } else {

            // setting the path
            user_counter_spinner_sotre = user_counter_spinner.getSelectedItem().toString();


            if (user_counter_spinner_sotre.equals("User 1")) {
                pathNameStorege = "/sdcard/Android/MultiUserId_1.txt";
                pathNameStoregeEmail = "/sdcard/Android/MultiUserPassword_1.txt";
                pathUserName = "/sdcard/Android/MultiUserName_1";
            } else if (user_counter_spinner_sotre.equals("User 2")) {
                pathNameStorege = "/sdcard/Android/MultiUserId_2.txt";
                pathNameStoregeEmail = "/sdcard/Android/MultiUserPassword_2.txt";
                pathUserName = "/sdcard/Android/MultiUserName_2";
            } else if (user_counter_spinner_sotre.equals("User 3")) {
                pathNameStorege = "/sdcard/Android/MultiUserId_3.txt";
                pathNameStoregeEmail = "/sdcard/Android/MultiUserPassword_3.txt";
                pathUserName = "/sdcard/Android/MultiUserName_3";
            } else if (user_counter_spinner_sotre.equals("User 4")) {
                pathNameStorege = "/sdcard/Android/MultiUserId_4.txt";
                pathNameStoregeEmail = "/sdcard/Android/MultiUserPassword_4.txt";
                pathUserName = "/sdcard/Android/MultiUserName_4";
            } else if (user_counter_spinner_sotre.equals("User 5")) {
                pathNameStorege = "/sdcard/Android/MultiUserId_5.txt";
                pathNameStoregeEmail = "/sdcard/Android/MultiUserPassword_5.txt";
                pathUserName = "/sdcard/Android/MultiUserName_5";
            } else if (user_counter_spinner_sotre.equals("User 6")) {
                pathNameStorege = "/sdcard/Android/MultiUserId_6.txt";
                pathNameStoregeEmail = "/sdcard/Android/MultiUserPassword_6.txt";
                pathUserName = "/sdcard/Android/MultiUserName_6";
            } else if (user_counter_spinner_sotre.equals("User 7")) {
                pathNameStorege = "/sdcard/Android/MultiUserId_7.txt";
                pathNameStoregeEmail = "/sdcard/Android/MultiUserPassword_7.txt";
                pathUserName = "/sdcard/Android/MultiUserName_7";
            } else if (user_counter_spinner_sotre.equals("User 8")) {
                pathNameStorege = "/sdcard/Android/MultiUserId_8.txt";
                pathNameStoregeEmail = "/sdcard/Android/MultiUserPassword_8.txt";
                pathUserName = "/sdcard/Android/MultiUserName_8";
            } else if (user_counter_spinner_sotre.equals("User 9")) {
                pathNameStorege = "/sdcard/Android/MultiUserId_9.txt";
                pathNameStoregeEmail = "/sdcard/Android/MultiUserPassword_9.txt";
                pathUserName = "/sdcard/Android/MultiUserName_9";
            } else {
                pathNameStorege = "/sdcard/Android/MultiUserId_10.txt";
                pathNameStoregeEmail = "/sdcard/Android/MultiUserPassword_10.txt";
                pathUserName = "/sdcard/Android/MultiUserName_10";
            }
            //end


            try {
                String add_user_id_store = add_userId.getText().toString();
                if (add_user_id_store.isEmpty()) {
                    add_userId.setError("id id empty");
                } else {
                    File myfile = new File(pathNameStorege);
                    myfile.createNewFile();
                    FileOutputStream fout = new FileOutputStream(myfile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fout);
                    myOutWriter.append(add_userId.getText());
                    myOutWriter.close();
                    Toast.makeText(AddUserActivity.this, "Done Writing", Toast.LENGTH_SHORT).show();
                    add_userId.setText("");
                }


            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            try {
                String add_user_password_store = add_userpassword.getText().toString();
                if (add_user_password_store.isEmpty()) {
                    add_userpassword.setError("id id empty");
                } else {
                    File myfile = new File(pathNameStoregeEmail);
                    myfile.createNewFile();
                    FileOutputStream fout = new FileOutputStream(myfile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fout);
                    myOutWriter.append(add_userpassword.getText());
                    myOutWriter.close();
                    Toast.makeText(AddUserActivity.this, "Done Writing", Toast.LENGTH_SHORT).show();
                    add_userpassword.setText("");
                }


            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            try {
                String add_user_name_store = add_userName.getText().toString();
                if (add_user_name_store.isEmpty()) {
                    add_userName.setError("id id empty");
                } else {
                    File myfile = new File(pathUserName);
                    myfile.createNewFile();
                    FileOutputStream fout = new FileOutputStream(myfile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fout);
                    myOutWriter.append(add_userName.getText());
                    myOutWriter.close();
                    Toast.makeText(AddUserActivity.this, "Done Writing", Toast.LENGTH_SHORT).show();
                    add_userName.setText("");
                }


            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }
    //end

    // for permisson
    private void Permissions() {


        Dexter.withContext(getApplicationContext())
                .withPermissions(
                        Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }

                }).check();


    }
    // end

    // request premisssion

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // android is 11 (R) or above
            try {

                Log.d(TAG, "requestPermission: try");
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("packge", this.getPackageName(), null);
                intent.setData(uri);
                storegeActivityResultLauncher.launch(intent);

            } catch (Exception e) {

                Log.d(TAG, "requestPermission: catch", e);
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                storegeActivityResultLauncher.launch(intent);

            }
        } else {
            // android is 11 (R) or below
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    private ActivityResultLauncher<Intent> storegeActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Manage External Permission is granted
                    Log.d(TAG, "onActivity: ");
                    // here were we handle the result
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        if (Environment.isExternalStorageManager()) {
                            Log.d(TAG, "onActivityResult: Manage External Permission is granted");
                            cerateFile();
                        }else{
                            // Manage External Storage Permission is denied
                            Log.d(TAG,"onActivityResult: Manage External Storage Permission is denied");
                            Toast.makeText(AddUserActivity.this, "Manage External Storage Permission is denied", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        // Android is below 11 (R)

                    }
                }
            }
    );

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // android is 11 (R) or above
            return Environment.isExternalStorageManager();

        }else{
            // android is 11 (R) or below
            int write = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int read = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);

            return write == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0){
                //check each permission if granted or not
                boolean write = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if(write && read){
                    // External Storage Permission granted
                    Log.d(TAG,"onRequestPermissionResult: External Storage Permission granted");
                    cerateFile();
                }else {
                    // External Storage Permission denied
                    Log.d(TAG,"onRequestPermissionResult: External Storage Permission denied");
                    Toast.makeText(this, "External Storage Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}