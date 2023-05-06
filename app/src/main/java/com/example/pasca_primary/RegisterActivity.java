package com.example.pasca_primary;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {


    MaterialEditText et_username, et_password, et_email;
    Button registerbtn;
    Spinner spinner;
    Toolbar toolbar;

    String username, email, password;

    FirebaseAuth mAuth;

    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbarregis);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CustomProgressDialog dialog = new CustomProgressDialog(RegisterActivity.this);
        et_username = findViewById(R.id.reg_username);
        et_email = findViewById(R.id.reg_email);
        et_password = findViewById(R.id.reg_password);
        registerbtn = findViewById(R.id.register_Account_btn);


        mAuth = FirebaseAuth.getInstance();




        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomProgressDialog dialog = new CustomProgressDialog(RegisterActivity.this);

                email = et_email.getText().toString();
                password = et_password.getText().toString();
                username = et_username.getText().toString();


                if (TextUtils.isEmpty(email)) {
                    et_email.setError("Required");
                } else if (TextUtils.isEmpty(username)) {
                    et_username.setError("Required");

                } else if (TextUtils.isEmpty(password)) {
                    et_password.setError("Required");

                } else if (password.length() < 6) {

                    et_password.setError("Length Must Be 6 or more");
                } else {

                    dialog.show();
                    registerUser(username, password, email);
                    dialog.dismiss();
                }

                }
        });





    }

    private void registerUser(final String username, String password, final String email) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    FirebaseUser user = mAuth.getCurrentUser();

                    reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());


                    if (user!=null) {

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("username", username);
                        hashMap.put("email", email);
                        hashMap.put("password",password);
                        hashMap.put("search",username);
                        hashMap.put("id", user.getUid());
                        hashMap.put("imageURL", "default");
                        hashMap.put("status", "offline");
                        hashMap.put("usertype", 0);


                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {


                                if (task.isSuccessful()) {

                                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                   et_email.setText("");
                                   et_password.setText("");
                                   et_username.setText("");


                                }
                            }
                        });






                    }


                }



            }
        });

    }


}