package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class TeachersNotePadActivity extends AppCompatActivity {

    MaterialEditText notepad_message;
    Button notepad_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_note_pad);

        notepad_message = findViewById(R.id.notepad_edittext);
        notepad_btn = findViewById(R.id.notepad_edittext_btn);

        notepad_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String output = notepad_message.getText().toString();

                if(output.isEmpty()){
                    notepad_message.setError("Please write your message!");
                }else{
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("MyData",output);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(TeachersNotePadActivity.this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
                    notepad_message.setText("");

                }
            }
        });

    }
}