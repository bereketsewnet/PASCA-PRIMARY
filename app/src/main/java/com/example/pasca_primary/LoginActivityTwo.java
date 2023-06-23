package com.example.pasca_primary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivityTwo extends AppCompatActivity {


    MaterialEditText et_email, et_password;
    Button loginBtn;
    Toolbar toolbar;
    String email, password,uid2;
    int usertype2;
    FirebaseAuth mAuth;
    FirebaseUser user;
    TextView forgotPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_two);


      toolbar = findViewById(R.id.toolbarlogin);
       setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_email = findViewById(R.id.log_email);
        et_password = findViewById(R.id.log_password);
        loginBtn = findViewById(R.id.login_account);
        forgotPassword = findViewById(R.id.forgot_password);


        mAuth = FirebaseAuth.getInstance();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                email = et_email.getText().toString();
                password = et_password.getText().toString();


                if (TextUtils.isEmpty(email)) {

                    et_email.setError("Required");

                } else if (TextUtils.isEmpty(password)) {

                    et_password.setError("Required");
                } else {

                    LoginMeIn(email, password);
                }



            }
        });
    }


    private void LoginMeIn(String email, String password) {
        final CustomProgressDialog dialog = new CustomProgressDialog(LoginActivityTwo.this);
        dialog.show();


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {



                if (task.isSuccessful()) {

                    //start of filter Student and  Teacher
                    String uid = task.getResult().getUser().getUid();
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child("Users").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int usertype = snapshot.getValue(Integer.class);


                            if(usertype==2){
                                Intent intent = new Intent(LoginActivityTwo.this,StartChatActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivityTwo.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(LoginActivityTwo.this, "You are not admin to access", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    //end of filter

                }else{
                    Toast.makeText(LoginActivityTwo.this, "Username or Password is In correct", Toast.LENGTH_SHORT).show();
                }

            }
        });


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivityTwo.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot, null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail = emailBox.getText().toString();
                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(LoginActivityTwo.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginActivityTwo.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(LoginActivityTwo.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
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

    }



}