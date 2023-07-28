package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.pasca_primary.Model.ChangeProfilePass;
import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeProfilePassActivity extends AppCompatActivity {

    Dialog SuccessDialog;
    Dialog ErrorDialog;
    Button change_profile_pass_btn;
    TextView change_profile_pass1,change_profile_pass2;
    FirebaseAuth mAuth;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile_pass);

        toolbar = findViewById(R.id.toolbar_change_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ChangeProfile Pass");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        change_profile_pass1 = findViewById(R.id.change_profile_pass1);
        change_profile_pass2 = findViewById(R.id.change_profile_pass2);
        change_profile_pass_btn = findViewById(R.id.change_profile_pass_btn);
        mAuth = FirebaseAuth.getInstance();
        final CustomProgressDialog dialog = new CustomProgressDialog(ChangeProfilePassActivity.this);
        change_profile_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                ChangeProfilePassword();
                dialog.dismiss();
            }



        });


    }

    private void ChangeProfilePassword() {

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

                SuccessDialog.dismiss();
            }
        });

        // end of success dialog


        DatabaseReference PassRef = FirebaseDatabase.getInstance().getReference().child("ProfileChangePassword");
        // reciving Password by String
        String password = change_profile_pass1.getText().toString();
        String password2 = change_profile_pass2.getText().toString();

        if(TextUtils.isEmpty(password)){
            change_profile_pass1.setError("Please Insert Password");
        }else if(password.length() < 6){
            change_profile_pass1.setError("Password Must Be At Least 6!");
        }else if(!password.equals(password2)){
            change_profile_pass1.setError("Password Must Be Same!");
            change_profile_pass2.setError("Password Must Be Same!");
        }else{
            ChangeProfilePass changeProfilePass = new ChangeProfilePass(password);
            PassRef.setValue(changeProfilePass);
            // set content is null
            change_profile_pass1.setText("");
            change_profile_pass2.setText("");
            SuccessDialog.show();
        }




    }
}