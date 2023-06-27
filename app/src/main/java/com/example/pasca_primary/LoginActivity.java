package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pasca_primary.Adapters.UserAdapter;
import com.example.pasca_primary.Model.Users;
import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    MaterialEditText et_email, et_password;
    Dialog ErrorRegisterDialog;
    Button loginBtn;
    Toolbar toolbar;
    String email, password,uid2;
    int usertype2;
    FirebaseAuth mAuth;
    FirebaseUser user;




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
        loginBtn = findViewById(R.id.login_account);


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

                    //start of filter Student and  Teacher
                    String uid = task.getResult().getUser().getUid();
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child("Users").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int usertype = snapshot.getValue(Integer.class);
                            if(usertype==0){
                                Intent intent = new Intent(LoginActivity.this,StudentsHomeActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, "Welcome Student", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                finish();

                            }


                            if(usertype==1 || usertype==3){
                                Intent intent = new Intent(LoginActivity.this,Home_twoActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, "Welcome Teacher", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                finish();
                            }



                            if(usertype==2){
                                Intent intent = new Intent(LoginActivity.this,AdminHomeActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                finish();
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
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user!=null) {

            startActivity(new Intent(LoginActivity.this, CheckerActivity.class));

        }
    }

}