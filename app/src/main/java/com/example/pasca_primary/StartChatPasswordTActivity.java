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

public class StartChatPasswordTActivity extends AppCompatActivity {

    MaterialEditText start_chat_password_t;
    Button start_chat_password_btn_t;
    DatabaseReference reference;
    String start_chat_password_t_store,recive_pass_t;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_chat_password_tactivity);

        toolbar = findViewById(R.id.toolbar_start_chat_t_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("StartChat Teacher");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        start_chat_password_btn_t = findViewById(R.id.start_chat_password_btn_t);
        start_chat_password_t = findViewById(R.id.start_chat_password_t);

        final CustomProgressDialog dialog = new CustomProgressDialog(StartChatPasswordTActivity.this);

        reference = FirebaseDatabase.getInstance().getReference("StartChatTeacherPass");

        start_chat_password_btn_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_chat_password_t_store = start_chat_password_t.getText().toString();

                if(TextUtils.isEmpty(start_chat_password_t_store)){
                    start_chat_password_t.setError("Please Insert Password");
                }else{
                    // start
                    dialog.show();
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            ChangeProfilePass changeProfilePass = snapshot.getValue(ChangeProfilePass.class);


                            if(changeProfilePass != null){
                                recive_pass_t = changeProfilePass.getPassword();
                                if(recive_pass_t.equals(start_chat_password_t_store)){
                                    Intent intent = new Intent(StartChatPasswordTActivity.this, StudentListActivity.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                    finish();
                                }else{
                                    start_chat_password_t.setError("Please Try Again!");
                                    dialog.dismiss();
                                }



                            }else{
                                Toast.makeText(StartChatPasswordTActivity.this, "First set The Password in Database", Toast.LENGTH_SHORT).show();
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