package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pasca_primary.Model.MultiRegisterUsers;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class BulkRegisterActivity extends AppCompatActivity {
    String name,email,password,studentClass,studentSex,userRollNo;
   int usertype;
   Button register_from,register_save;
   MaterialEditText getnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_register);

        register_from = findViewById(R.id.register_from);
        register_save = findViewById(R.id.register_save);
        getnumber = findViewById(R.id.getnumber);

        register_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        register_save.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {



                return true;
            }
        });

        register_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        register_from.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                getUsersFromDatabase("1");
                return true;
            }
        });


    }

    private void register(String userRollNo){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MultiRegister").child(userRollNo);
        MultiRegisterUsers multiRegisterUsers = new MultiRegisterUsers("azme","azme@gmail.com","123456","kg1","male",0);
        reference.setValue(multiRegisterUsers);
    }

    private void getUsersFromDatabase(String userRollNo){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MultiRegister").child(userRollNo);

        if (reference != null) {

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    MultiRegisterUsers multiRegisterUsers = snapshot.getValue(MultiRegisterUsers.class);

                    if (multiRegisterUsers != null) {
                        name = multiRegisterUsers.getName();
                        email = multiRegisterUsers.getEmail();
                        password = multiRegisterUsers.getPassword();
                        studentClass = multiRegisterUsers.getStudentClass();
                        studentSex = multiRegisterUsers.getStudentSex();
                        usertype = multiRegisterUsers.getUsertype();

                        Toast.makeText(BulkRegisterActivity.this, ""+name, Toast.LENGTH_SHORT).show();
                        Toast.makeText(BulkRegisterActivity.this, ""+email, Toast.LENGTH_SHORT).show();
                        Toast.makeText(BulkRegisterActivity.this, ""+password, Toast.LENGTH_SHORT).show();
                        Toast.makeText(BulkRegisterActivity.this, ""+usertype, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BulkRegisterActivity.this, "first Import User Data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(this, "first set data on database", Toast.LENGTH_SHORT).show();
        }
    }

}
