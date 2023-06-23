package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class CustomDialogActivity extends AppCompatActivity {

    Dialog ErrorDialog;
    private Button ShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        ShowDialog = findViewById(R.id.dialog_btn);

        //Create the Wrong Dialog here
        ErrorDialog = new Dialog(this);
        ErrorDialog.setContentView(R.layout.custom_wrong_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ErrorDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        ErrorDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ErrorDialog.setCancelable(false); //Optional
        ErrorDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button Okay = ErrorDialog.findViewById(R.id.btn_okay_error);

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CustomDialogActivity.this, "Done", Toast.LENGTH_SHORT).show();
                ErrorDialog.dismiss();
            }
        });

        // end of Wrong dialog


        ShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ErrorDialog.show(); // Showing the dialog here
            }
        });

    }
}