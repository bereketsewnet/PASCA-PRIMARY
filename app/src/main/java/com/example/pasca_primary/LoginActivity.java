package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {


    MaterialEditText et_email, et_password,log_yourname;
    Dialog ErrorRegisterDialog;
    Button loginBtn;
    Toolbar toolbar;
    String getYourName;
    String email, password;
    FirebaseAuth mAuth;
    FirebaseUser user;

    TextView forgotPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


      toolbar = findViewById(R.id.toolbarlogin);
       setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        et_email = findViewById(R.id.log_email);
        et_password = findViewById(R.id.log_password);
        log_yourname = findViewById(R.id.log_yourname);
        loginBtn = findViewById(R.id.login_account);
        forgotPassword = findViewById(R.id.forgot_password);


        mAuth = FirebaseAuth.getInstance();
       // check if yourName preferance not null
        loadyourName();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                email = et_email.getText().toString();
                password = et_password.getText().toString();
                getYourName = log_yourname.getText().toString();





                if (TextUtils.isEmpty(email)) {

                    et_email.setError("Required");

                } else if (TextUtils.isEmpty(password)) {

                    et_password.setError("Required");
                }else if (TextUtils.isEmpty(getYourName)) {
                    log_yourname.setError("Required");
                } else {

                    LoginMeIn(email, password);

                }



            }
        });
    }


    private void LoginMeIn(String email, String password) {

        final CustomProgressDialog dialog = new CustomProgressDialog(LoginActivity.this);
            dialog.show();
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

                Toast.makeText(LoginActivity.this, "Done", Toast.LENGTH_SHORT).show();
                ErrorRegisterDialog.dismiss();
            }
        });

        // end of Wrong dialog


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {



                if (task.isSuccessful()) {

                    // save data
                    SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
                    editor.putString("YourName", getYourName);
                    editor.apply();

                    //start of filter Student and  Teacher
                    String uid = task.getResult().getUser().getUid();

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child("Users").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int usertype = snapshot.getValue(Integer.class);
                            saveUserType(uid, String.valueOf(usertype)); // saving usertype by in preferance using uid as name
                            if(usertype==0){
                                Intent intent = new Intent(LoginActivity.this,StudentsHomeActivity.class);
                                intent.putExtra("MyId", uid);
                                startActivity(intent);
                                dialog.dismiss();
                                finish();



                            }


                            if(usertype==1 || usertype==2){
                                Intent intent = new Intent(LoginActivity.this, TeachersHomeActivity.class);
                                intent.putExtra("MyId", uid);
                                startActivity(intent);
                                dialog.dismiss();
                                finish();
                            }



                            if(usertype==3){
                                Intent intent = new Intent(LoginActivity.this,AdminHomeActivity.class);
                                intent.putExtra("MyId", uid);
                                startActivity(intent);
                                dialog.dismiss();
                                finish();
                            }



                            if(usertype==4){
                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Not Found!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    //end of filter

                }else{
                    ErrorRegisterDialog.show();
                    dialog.dismiss();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error is: "+e, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        // forgot password
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot, null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail = emailBox.getText().toString();
                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(LoginActivity.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
        // end



    }

    public void saveUserType(String uid, String UserType){
        // save data
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString(uid, UserType);
        editor.apply();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }



    // load save file
    public void loadyourName(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String Name = preferences.getString("YourName","");
        if (!Name.equals("")){
            // default  is visible the edite text
            log_yourname.setVisibility(View.GONE);
            log_yourname.setText(Name);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user!=null) {

          // startActivity(new Intent(LoginActivity.this, UserCheckerActivity.class));

        }
    }

}