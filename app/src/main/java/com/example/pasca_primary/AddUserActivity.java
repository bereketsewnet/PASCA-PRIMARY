package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AddUserActivity extends AppCompatActivity {
    Button add_account;
    MaterialEditText add_userId,add_userpassword,add_userName;
    Spinner user_counter_spinner;
    String user_counter_spinner_sotre,pathNameStorege,pathNameStoregeEmail,pathUserName;


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

                String email,password,name;
                email = add_userId.getText().toString();
                password = add_userpassword.getText().toString();
                name = add_userName.getText().toString();

                if(email.isEmpty() || password.isEmpty() || name.isEmpty()){

                    Toast.makeText(AddUserActivity.this, "All Are Required!!", Toast.LENGTH_SHORT).show();
                }else {

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
        });



    }
}