package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class PasswordSevenActivity extends AppCompatActivity {

    MaterialEditText pass_seven;
    String password_store_seven;
    Button btn_pass_seven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_seven);


        pass_seven = findViewById(R.id.pass_seven);
        btn_pass_seven =findViewById(R.id.btn_pass_seven);

        btn_pass_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_store_seven = pass_seven.getText().toString();
                // Upload Fee Information. Password Here
                if(password_store_seven.equals("1234567890")){
                    Intent intent = new Intent(PasswordSevenActivity.this, UploadFeesInformationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(PasswordSevenActivity.this, "Right", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PasswordSevenActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}