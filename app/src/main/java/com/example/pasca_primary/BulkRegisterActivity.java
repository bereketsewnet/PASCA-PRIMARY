package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pasca_primary.Model.MultiRegisterUsers;
import com.example.pasca_primary.Model.MultiSelectionId;
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

import java.util.HashMap;

public class BulkRegisterActivity extends AppCompatActivity {
    String name,email,password,studentClass,studentSex,usertype;
    Long passwordd;
    Button register_from;
    MaterialEditText getnumber,getnumber_start;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    int usetypeC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_register);

        register_from = findViewById(R.id.register_from);
        getnumber = findViewById(R.id.getnumber);
        getnumber_start = findViewById(R.id.getnumber_start);


        register_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           int StudentNumEnd = Integer.parseInt(getnumber.getText().toString());
           int StudentNumStart = Integer.parseInt(getnumber_start.getText().toString());

           for(int i = StudentNumStart; i <= StudentNumEnd; i++){

               String userRollNo = String.valueOf(i);
               getUsersFromDatabase(userRollNo);
           }

            }
        });



    }



    private void getUsersFromDatabase(String userRollNo){
        final CustomProgressDialog dialog = new CustomProgressDialog(BulkRegisterActivity.this);
       // dialog.show();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MultiRegister").child(userRollNo);

        if (reference != null) {

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    MultiRegisterUsers multiRegisterUsers = snapshot.getValue(MultiRegisterUsers.class);

                    if (multiRegisterUsers != null) {
                        name = multiRegisterUsers.getName();
                        email = multiRegisterUsers.getEmail();
                        passwordd = multiRegisterUsers.getPassword();
                        studentClass = multiRegisterUsers.getStudentClass();
                        studentSex = multiRegisterUsers.getStudentSex();
                        usertype = multiRegisterUsers.getUsertype();

                        password = String.valueOf(passwordd); // change password long variable to string variable.


                        if(usertype.equals("Student")){
                            usetypeC = 0;
                        }else if(usertype.equals("Teacher")){
                            usetypeC = 1;
                        }else if(usertype.equals("HomeRoom Teacher")){
                            usetypeC = 2;
                        }else if(usertype.equals("Admin")){
                            usetypeC = 3;
                        }else{
                            usetypeC = 0;
                        }

                        registerUser(name,email,password,usetypeC,studentClass,studentSex);


                    } else {
                        Toast.makeText(BulkRegisterActivity.this, "User Not Found!!"+userRollNo, Toast.LENGTH_SHORT).show();
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

    private void registerUser(final String username, final String email, String password, final int usertype, final String studentClass, final String studentSex) {

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {


                    String searchusername = username.toLowerCase();

                    FirebaseUser user = mAuth.getCurrentUser();

                    reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
                    String multiId = user.getUid();

                    // multiple sender id setting to database
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
                        hashMap.put("student_class", studentClass);
                        hashMap.put("sex", studentSex);


                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {


                                if (task.isSuccessful()) {

                                    Toast.makeText(BulkRegisterActivity.this, "Registered "+username, Toast.LENGTH_SHORT).show();


                                }
                            }
                        });






                    }


                }else{
                    Toast.makeText(BulkRegisterActivity.this, username+" Not Register", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

}
