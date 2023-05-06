package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pasca_primary.Fragments.UsersFragment;
import com.rengwuxian.materialedittext.MaterialEditText;

public class PasswordFiveActivity extends AppCompatActivity {

    MaterialEditText pass_five;
    String password_store_five;
    Button btn_pass_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_five);

        pass_five = findViewById(R.id.pass_five);
        btn_pass_five =findViewById(R.id.btn_pass_five);


        btn_pass_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_store_five = pass_five.getText().toString();
                // Start Chat Password Here
                if(password_store_five.equals("12345678")){
                    Intent intent = new Intent(PasswordFiveActivity.this, StartChatActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(PasswordFiveActivity.this, "Right", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PasswordFiveActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}