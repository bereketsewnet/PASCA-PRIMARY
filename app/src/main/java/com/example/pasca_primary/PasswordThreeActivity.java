package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class PasswordThreeActivity extends AppCompatActivity {

    MaterialEditText pass_three;
    String password_store_three;
    Button btn_pass_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_three);

        pass_three = findViewById(R.id.pass_three);
        btn_pass_three =findViewById(R.id.btn_pass_three);

        btn_pass_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_store_three = pass_three.getText().toString();
                // Teachers Advanced for the registration password here
                if(password_store_three.equals("123456")){
                    Intent intent = new Intent(PasswordThreeActivity.this, RegisterActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(PasswordThreeActivity.this, "Right", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PasswordThreeActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}