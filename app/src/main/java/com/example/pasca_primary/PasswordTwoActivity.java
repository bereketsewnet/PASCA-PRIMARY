package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class PasswordTwoActivity extends AppCompatActivity {

    MaterialEditText pass_two;
    String password_store;
    Button btn_pass_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_two);

        pass_two = findViewById(R.id.pass_two);
        btn_pass_two =findViewById(R.id.btn_pass_two);

        btn_pass_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_store = pass_two.getText().toString();
                  // Teacher profile photo password here
                if(password_store.equals("12345")){
                    Intent intent = new Intent(PasswordTwoActivity.this, UploadTActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(PasswordTwoActivity.this, "Right", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PasswordTwoActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}