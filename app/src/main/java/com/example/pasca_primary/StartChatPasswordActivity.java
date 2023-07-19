package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

public class StartChatPasswordActivity extends AppCompatActivity {

    MaterialEditText start_chat_password;
    Button start_chat_password_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_chat_password);

        start_chat_password_btn = findViewById(R.id.start_chat_password_btn);
        start_chat_password = findViewById(R.id.start_chat_password);

        start_chat_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = start_chat_password.getText().toString();

                if(TextUtils.isEmpty(password)){
                    start_chat_password.setError("Please enter the password");
                }else if(password.equals("1234")){
                    Intent intent = new Intent(StartChatPasswordActivity.this,StartChatActivity.class);
                    startActivity(intent);
                    start_chat_password.setText("");
                }else{
                    start_chat_password.setError("password is incorrect");
                }
            }
        });

    }
}