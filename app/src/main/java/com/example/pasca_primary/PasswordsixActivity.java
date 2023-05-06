package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class PasswordsixActivity extends AppCompatActivity {

    MaterialEditText pass_six;
    String password_store_six;
    Button btn_pass_six;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordsix);

        pass_six = findViewById(R.id.pass_six);
        btn_pass_six =findViewById(R.id.btn_pass_six);

        btn_pass_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_store_six = pass_six.getText().toString();
                // Upload Calendar. Password Here
                if(password_store_six.equals("123456789")){
                    Intent intent = new Intent(PasswordsixActivity.this, UploadCalendarActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(PasswordsixActivity.this, "Right", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PasswordsixActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}