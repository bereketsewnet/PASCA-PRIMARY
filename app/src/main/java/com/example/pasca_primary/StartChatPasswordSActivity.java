package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.pasca_primary.Model.ChangeProfilePass;
import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class StartChatPasswordSActivity extends AppCompatActivity {

    MaterialEditText start_chat_password_S;
    Button start_chat_password_btn;
    DatabaseReference reference;
    String start_chat_password_S_store,recive_pass_s;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_chat_password_s);

        toolbar = findViewById(R.id.toolbar_start_chat_s_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("StartChat Student");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        start_chat_password_btn = findViewById(R.id.start_chat_password_btn);
        start_chat_password_S = findViewById(R.id.start_chat_password_s);

        final CustomProgressDialog dialog = new CustomProgressDialog(StartChatPasswordSActivity.this);

        reference = FirebaseDatabase.getInstance().getReference("StartChatStudentPass");

        start_chat_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_chat_password_S_store = start_chat_password_S.getText().toString();

                if(TextUtils.isEmpty(start_chat_password_S_store)){
                    start_chat_password_S.setError("Please Insert Password");
                }else{
                    // start
                    dialog.show();
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            ChangeProfilePass changeProfilePass = snapshot.getValue(ChangeProfilePass.class);


                            if(changeProfilePass != null){
                                recive_pass_s = changeProfilePass.getPassword();
                                if(recive_pass_s.equals(start_chat_password_S_store)){
                                    Intent intent = new Intent(StartChatPasswordSActivity.this, TeacherListActivity.class);
                                    startActivity(intent);
                                    start_chat_password_S.setText("");
                                    dialog.dismiss();
                                    finish();
                                }else{
                                    start_chat_password_S.setError("Please Try Again!");
                                    dialog.dismiss();
                                }



                            }else{
                                Toast.makeText(StartChatPasswordSActivity.this, "First set The Password in Database", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    // end
                }
            }
        });

    }
}