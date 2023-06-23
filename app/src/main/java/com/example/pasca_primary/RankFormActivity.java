package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pasca_primary.Model.DisplayRank;
import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RankFormActivity extends AppCompatActivity {

     Dialog SuccessDialog;
    Dialog ErrorDialog;
    Button send_rank;
    TextView cont1, cont2, cont3, cont4, cont5, cont6, cont7, cont8, cont9, cont10, cont11, cont12;
    TextView value1, value2,value3, value4, value5,value6,value7, value8, value9, value10, value11, value12;


    String  friendId;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_form);



        //set cont
        cont1 = findViewById(R.id.cont1);
        cont2 = findViewById(R.id.cont2);
        cont3 = findViewById(R.id.cont3);
        cont4 = findViewById(R.id.cont4);
        cont5 = findViewById(R.id.cont5);
        cont6 = findViewById(R.id.cont6);
        cont7 = findViewById(R.id.cont7);
        cont8 = findViewById(R.id.cont8);
        cont9 = findViewById(R.id.cont9);
        cont10 = findViewById(R.id.cont10);
        cont11 = findViewById(R.id.cont11);
        cont12 = findViewById(R.id.cont12);
        //set values
        value1 = findViewById(R.id.value1);
        value2 = findViewById(R.id.value2);
        value3 = findViewById(R.id.value3);
        value4 = findViewById(R.id.value4);
        value5 = findViewById(R.id.value5);
        value6 = findViewById(R.id.value6);
        value7 = findViewById(R.id.value7);
        value8 = findViewById(R.id.value8);
        value9 = findViewById(R.id.value9);
        value10 = findViewById(R.id.value10);
        value11 = findViewById(R.id.value11);
        value12 = findViewById(R.id.value12);
        //others
        final CustomProgressDialog dialog = new CustomProgressDialog(RankFormActivity.this);
        send_rank = findViewById(R.id.rank_send_btn);
        mAuth = FirebaseAuth.getInstance();
        friendId = getIntent().getStringExtra("friendid"); // retreive the friendid when we click on the item

        FirebaseUser user = mAuth.getCurrentUser();




        send_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                  sendrank();
                dialog.dismiss();
            }



        });

    }

    private void sendrank() {

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

                Toast.makeText(RankFormActivity.this, "Finish", Toast.LENGTH_SHORT).show();
                SuccessDialog.dismiss();
            }
        });

        // end of success dialog

        //Create the Wrong Dialog here
        ErrorDialog = new Dialog(this);
        ErrorDialog.setContentView(R.layout.custom_wrong_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ErrorDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        ErrorDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ErrorDialog.setCancelable(false); //Optional
        ErrorDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button ErrorOkay = ErrorDialog.findViewById(R.id.btn_okay_error);

        ErrorOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(RankFormActivity.this, "Done", Toast.LENGTH_SHORT).show();
                ErrorDialog.dismiss();
            }
        });

        // end of Wrong dialog

        DatabaseReference RankReference = FirebaseDatabase.getInstance().getReference().child("RankReport").child(friendId);
        // reciving contents by String
        String rcont1 = cont1.getText().toString();
        String rcont2 = cont2.getText().toString();
        String rcont3 = cont3.getText().toString();
        String rcont4 = cont4.getText().toString();
        String rcont5 = cont5.getText().toString();
        String rcont6 = cont6.getText().toString();
        String rcont7 = cont7.getText().toString();
        String rcont8 = cont8.getText().toString();
        String rcont9 = cont9.getText().toString();
        String rcont10 = cont10.getText().toString();
        String rcont11 = cont11.getText().toString();
        String rcont12 = cont12.getText().toString();
        //reciving values by String
        String rvalue1 = value1.getText().toString();
        String rvalue2 = value2.getText().toString();
        String rvalue3 = value3.getText().toString();
        String rvalue4 = value4.getText().toString();
        String rvalue5 = value5.getText().toString();
        String rvalue6 = value6.getText().toString();
        String rvalue7 = value7.getText().toString();
        String rvalue8 = value8.getText().toString();
        String rvalue9 = value9.getText().toString();
        String rvalue10 = value10.getText().toString();
        String rvalue11 = value11.getText().toString();
        String rvalue12 = value12.getText().toString();


        if(rcont1.isEmpty() && rcont2.isEmpty() && rcont3.isEmpty() && rcont4.isEmpty()){
            Toast.makeText(this, "at least 4 content are required", Toast.LENGTH_SHORT).show();

        }else{
            DisplayRank displayRank = new DisplayRank(rcont1,rcont2,rcont3,rcont4,rcont5,rcont6,rcont7,rcont8,rcont9,rcont10,rcont11,rcont12,rvalue1,rvalue2,rvalue3,rvalue4,rvalue5,rvalue6,rvalue7,rvalue8,rvalue9,rvalue10,rvalue11,rvalue12);
            RankReference.setValue(displayRank);
            // set content is null
            cont1.setText(" ");
            cont2.setText(" ");
            cont3.setText(" ");
            cont4.setText(" ");
            cont5.setText(" ");
            cont6.setText(" ");
            cont7.setText(" ");
            cont8.setText(" ");
            cont9.setText(" ");
            cont10.setText(" ");
            cont11.setText(" ");
            cont12.setText(" ");
            //set value is null
            value1.setText(" ");
            value2.setText(" ");
            value3.setText(" ");
            value4.setText(" ");
            value5.setText(" ");
            value6.setText(" ");
            value7.setText(" ");
            value8.setText(" ");
            value9.setText(" ");
            value10.setText(" ");
            value11.setText(" ");
            value12.setText(" ");

            Toast.makeText(this, "Rank in Inserted", Toast.LENGTH_SHORT).show();
            SuccessDialog.show();
        }




    }

}

