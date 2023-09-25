package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class TeachersNotePadActivity extends AppCompatActivity {

    MaterialEditText notepad_message;
    Button notepad_btn,notepad_multi_btn,common_message_btn;
    String MyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_note_pad);

        notepad_message = findViewById(R.id.notepad_edittext);
        notepad_btn = findViewById(R.id.notepad_edittext_btn);
        notepad_multi_btn = findViewById(R.id.notepad_multi_btn);
        common_message_btn = findViewById(R.id.notepad_common_btn);
        MyId = getIntent().getStringExtra("userId");

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
                    notepad_message.setText("");
                    Intent intent = new Intent(TeachersNotePadActivity.this,Main_twoActivity.class);
                    startActivity(intent);
                    Toast.makeText(TeachersNotePadActivity.this, "Copied", Toast.LENGTH_SHORT).show();

                }
            }
        });
        FirebaseUser firebaseUser;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        notepad_multi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bulkMessage = notepad_message.getText().toString();
                if(bulkMessage.isEmpty()){
                    notepad_message.setError("Please Write your Message");

                }else{

                    Intent intent = new Intent(TeachersNotePadActivity.this,BulkMessagingActivity.class);
                    intent.putExtra("bulkMsg", bulkMessage);
                    intent.putExtra("uid",uid);
                    startActivity(intent);
                }

            }
        });

        common_message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeachersNoteDialog();
            }
        });

    }

    private void TeachersNoteDialog() {
        final String[] listItems = {getString(R.string.notseen),getString(R.string.checknews),getString(R.string.checkprogress),getString(R.string.incompleteschoolequipment),
                                    getString(R.string.dintworkhome), getString(R.string.didntworkclas),getString(R.string.disorder)};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TeachersNotePadActivity.this);
        mBuilder.setTitle("Check-In...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0){
                    notepad_message.setText("Dear Parents Sent Action Wasn't Seen!");
                }else if(which == 1){
                    notepad_message.setText("Dear Parents Please Check News Page");
                }else if(which == 2){
                    notepad_message.setText("Dear Parents Please Check Progress Page");
                }else if(which == 3){
                    notepad_message.setText("Dear Parents Your Child Bring Incomplete school equipment!");
                }else if(which == 4){
                    notepad_message.setText("Dear Parents Your Child Not doing school home work!");
                }else if(which == 5){
                    notepad_message.setText("Dear Parents Your Child Not doing school class work!");
                }else if(which == 6){
                    notepad_message.setText("Dear Parents Your Child Disobeying School Order");
                }
                dialog.dismiss();

            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }
}