package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class ComplainActivity extends AppCompatActivity {


    MaterialEditText complain_name,complain_email,complain_content;
    Button complain_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        complain_btn = findViewById(R.id.complain_btn);
        complain_name = findViewById(R.id.complain_name);
        complain_email = findViewById(R.id.complain_email);
        complain_content = findViewById(R.id.complain_content);

        complain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,content,email;
                name = complain_name.getText().toString();
                content = complain_content.getText().toString();
                email = complain_email.getText().toString();

                if(name.equals("") && content.equals("") && email.equals("")){
                    Toast.makeText(ComplainActivity.this, "All are required", Toast.LENGTH_SHORT).show();
                }else{
                    sendEmail(name, content, email);

                }

            }
        });

    }

    public void sendEmail(String name, String content, String to_email){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{to_email});
        intent.putExtra(Intent.EXTRA_SUBJECT,name);
        intent.putExtra(Intent.EXTRA_TEXT,content);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose email client"));

        complain_name.setText("");
        complain_email.setText("");
        complain_content.setText("");

    }

}