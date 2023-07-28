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
import com.example.pasca_primary.Model.ChangeStartChatPassS;
import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeStartChatPassSActivity extends AppCompatActivity {

    Dialog SuccessDialog;
    Dialog ErrorDialog;
    Button change_start_chat_pass_btn;
    TextView change_start_chat_pass1,change_start_chat_pass2;
    FirebaseAuth mAuth;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_start_chat_pass_sactivity);

        toolbar = findViewById(R.id.toolbar_start_chat_s_pass);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("StartChat Student");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        change_start_chat_pass1 = findViewById(R.id.change_start_chat_s_pass1);
        change_start_chat_pass2 = findViewById(R.id.change_start_chat_s_pass2);
        change_start_chat_pass_btn = findViewById(R.id.change_start_chat_s_pass_btn);
        mAuth = FirebaseAuth.getInstance();
        final CustomProgressDialog dialog = new CustomProgressDialog(ChangeStartChatPassSActivity.this);

        change_start_chat_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                ChangeStartChatPassStudents();
                dialog.dismiss();
            }



        });

    }

    private void ChangeStartChatPassStudents() {

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


        DatabaseReference PassRef = FirebaseDatabase.getInstance().getReference().child("StartChatStudentPass");
        // reciving Password by String
        String password = change_start_chat_pass1.getText().toString();
        String password2 = change_start_chat_pass2.getText().toString();

        if(TextUtils.isEmpty(password)){
            change_start_chat_pass1.setError("Please Insert Password");
        }else if(password.length() < 6){
            change_start_chat_pass1.setError("Password Must Be At Least 6!");
        }else if(!password.equals(password2)){
            change_start_chat_pass1.setError("Password Must Be Same!");
            change_start_chat_pass2.setError("Password Must Be Same!");
        }else{
            ChangeStartChatPassS changeStartChatPassS = new ChangeStartChatPassS(password);
            PassRef.setValue(changeStartChatPassS);
            // set content is null
            change_start_chat_pass1.setText("");
            change_start_chat_pass2.setText("");
            SuccessDialog.show();
        }




    }

}