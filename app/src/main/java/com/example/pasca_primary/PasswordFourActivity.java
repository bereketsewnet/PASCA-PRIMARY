package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class PasswordFourActivity extends AppCompatActivity {

    MaterialEditText pass_four;
    String password_store_four;
    Button btn_pass_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_four);

        pass_four = findViewById(R.id.pass_four);
        btn_pass_four =findViewById(R.id.btn_pass_four);

        btn_pass_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_store_four = pass_four.getText().toString();
                // Teachers Only Upload Iq Question. Password Here
                if(password_store_four.equals("1234567")){
                    Intent intent = new Intent(PasswordFourActivity.this, UploadIqTActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(PasswordFourActivity.this, "Right", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PasswordFourActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}