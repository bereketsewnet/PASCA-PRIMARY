package com.example.pasca_primary;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pasca_primary.Model.ChangeProfilePass;
import com.example.pasca_primary.Model.MultiSelectionId;
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
    Spinner reg_usertype,reg_class,reg_sex;
    Dialog SuccessDialog;
    Dialog ErrorRegisterDialog;
    Button registerbtn;
    Toolbar toolbar;

    String username, email, password,usertype,student_class,student_sex,searchusername;

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
        reg_usertype = findViewById(R.id.reg_usertype);
        reg_class = findViewById(R.id.reg_class);
        reg_sex = findViewById(R.id.reg_sex);
        registerbtn = findViewById(R.id.register_Account_btn);

       ArrayAdapter<CharSequence> adapter_usertype = ArrayAdapter.createFromResource(this, R.array.user_type, android.R.layout.simple_spinner_item);
        adapter_usertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_usertype.setAdapter(adapter_usertype);

        ArrayAdapter<CharSequence> adapter_class = ArrayAdapter.createFromResource(this, R.array.student_class, android.R.layout.simple_spinner_item);
        adapter_class.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_class.setAdapter(adapter_class);

        ArrayAdapter<CharSequence> adapter_sex = ArrayAdapter.createFromResource(this, R.array.student_sex, android.R.layout.simple_spinner_item);
        adapter_sex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_sex.setAdapter(adapter_sex);




        mAuth = FirebaseAuth.getInstance();




        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                email = et_email.getText().toString();
                password = et_password.getText().toString();
                username = et_username.getText().toString();
                searchusername = et_username.getText().toString().toLowerCase();
                usertype = reg_usertype.getSelectedItem().toString();
                student_class = reg_class.getSelectedItem().toString();
                student_sex = reg_sex.getSelectedItem().toString();

                if(usertype.equals("Student")){
                    usertype = "0";
                }else if(usertype.equals("Teacher")){
                    usertype = "1";
                }else if(usertype.equals("HomeRoom Teacher")){
                    usertype = "2";
                }else{
                    usertype = "3";
                }







                if (TextUtils.isEmpty(email)) {
                    et_email.setError("Required");
                } else if (TextUtils.isEmpty(username)) {
                    et_username.setError("Required");

                } else if (TextUtils.isEmpty(password)) {
                    et_password.setError("Required");

                }else if (password.length() < 6) {

                    et_password.setError("Length Must Be 6 or more");
                } else {

                    registerUser(username, password, email, Integer.parseInt(usertype));

                }

                }
        });





    }

    private void registerUser(final String username, String password, final String email, final int usertype) {
        final CustomProgressDialog dialog = new CustomProgressDialog(RegisterActivity.this);
        dialog.show();

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

                Toast.makeText(RegisterActivity.this, "Finish", Toast.LENGTH_SHORT).show();
                SuccessDialog.dismiss();
            }
        });

        // end of success dialog

        //Create the Wrong Dialog here
        ErrorRegisterDialog = new Dialog(this);
        ErrorRegisterDialog.setContentView(R.layout.custom_register_error_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ErrorRegisterDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        ErrorRegisterDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ErrorRegisterDialog.setCancelable(false); //Optional
        ErrorRegisterDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button ErrorOkay = ErrorRegisterDialog.findViewById(R.id.btn_okay_error_register);

        ErrorOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(RegisterActivity.this, "Done", Toast.LENGTH_SHORT).show();
                ErrorRegisterDialog.dismiss();
            }
        });

        // end of Wrong dialog

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {



                    FirebaseUser user = mAuth.getCurrentUser();

                    reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
                    String multiId = user.getUid();

                    // multiple sender id settin
                    DatabaseReference multiSenderRef = FirebaseDatabase.getInstance().getReference().child("MultiSenderId").child(username);
                    MultiSelectionId multiSelectionId = new MultiSelectionId(multiId);
                    multiSenderRef.setValue(multiSelectionId);
                    // end

                    if (user!=null) {

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("username", username);
                        hashMap.put("email", email);
                        hashMap.put("password",password);
                        hashMap.put("search",searchusername);
                        hashMap.put("id", user.getUid());
                        hashMap.put("imageURL", "default");
                        hashMap.put("status", "offline");
                        hashMap.put("usertype", usertype);
                        hashMap.put("student_class", student_class);
                        hashMap.put("sex", student_sex);


                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {


                                if (task.isSuccessful()) {

                                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                   et_email.setText("");
                                   et_password.setText("");
                                   et_username.setText("");
                                   dialog.dismiss();
                                   SuccessDialog.show();


                                }
                            }
                        });






                    }


                }else{
                    dialog.dismiss();
                    ErrorRegisterDialog.show();
                }



            }
        });

    }


}