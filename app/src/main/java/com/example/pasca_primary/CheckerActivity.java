package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pasca_primary.additional.CustomProgressDialog;
import com.example.pasca_primary.additional.CustomProgressDialogTwo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckerActivity extends AppCompatActivity {

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);

        final CustomProgressDialogTwo dialogtwo = new CustomProgressDialogTwo(CheckerActivity.this);
        dialogtwo.show();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child("Users").child(user.getUid()).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int usertype = snapshot.getValue(Integer.class);
                if(usertype==0){
                    Intent intent = new Intent(CheckerActivity.this,StudentsHomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(CheckerActivity.this, "Welcome Student", Toast.LENGTH_SHORT).show();
                    finish();
                }


                if(usertype==1){
                    Intent intent = new Intent(CheckerActivity.this,Home_twoActivity.class);
                    startActivity(intent);
                    Toast.makeText(CheckerActivity.this, "Welcome Teacher", Toast.LENGTH_SHORT).show();
                    finish();
                }





                if(usertype==2){
                    Intent intent = new Intent(CheckerActivity.this,AdminHomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(CheckerActivity.this, "Welcome Teacher", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}