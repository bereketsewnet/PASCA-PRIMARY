package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class PasswordOneActivity extends AppCompatActivity {

    MaterialEditText go_password;
    String password;
    Button go;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_one);


        go_password = findViewById(R.id.go_password);
        go = findViewById(R.id.go);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = go_password.getText().toString();
                  // Student profile photo password here
                if(password.equals("1234")){

                    Intent intent = new Intent(PasswordOneActivity.this, UploadActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(PasswordOneActivity.this, "Right", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(PasswordOneActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}