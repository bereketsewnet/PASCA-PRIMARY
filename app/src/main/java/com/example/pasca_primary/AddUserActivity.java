package com.example.pasca_primary;


import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
public class AddUserActivity extends AppCompatActivity {
    Button add_account;
    MaterialEditText add_userId, add_userpassword, add_userName;
    Spinner user_counter_spinner;
    String user_counter_spinner_sotre, pathNameStorege, pathNameStoregeEmail, pathUserName;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private String[] permissions = {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);




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
                if (checkPermission()) {
                     cerateFile();
                } else {
                    requestPermission(); // Request Permission
                }


            }
        });

        //Add these line of code in onCreate Method
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult( ActivityResult result ) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (Environment.isExternalStorageManager())
                        Toast.makeText(AddUserActivity.this,"We Have Permission",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(AddUserActivity.this, "You Denied the permission", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddUserActivity.this, "You Denied the permission", Toast.LENGTH_SHORT).show();
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


    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int readCheck = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
            int writeCheck = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
            return readCheck == PackageManager.PERMISSION_GRANTED && writeCheck == PackageManager.PERMISSION_GRANTED;
        }
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            new AlertDialog.Builder(AddUserActivity.this)
                    .setTitle("Permission")
                    .setMessage("Please give the Storage permission")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick( DialogInterface dialog, int which ) {
                            try {
                                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                                intent.addCategory("android.intent.category.DEFAULT");
                                intent.setData(Uri.parse(String.format("package:%s", new Object[]{getApplicationContext().getPackageName()})));
                                activityResultLauncher.launch(intent);
                            } catch (Exception e) {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                                activityResultLauncher.launch(intent);
                            }
                        }
                    })
                    .setCancelable(false)
                    .show();

        } else {

            ActivityCompat.requestPermissions(AddUserActivity.this, permissions, 30);
        }
    }

}