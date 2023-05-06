package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_twoActivity extends AppCompatActivity {

    CardView daily,information,notepad,iq_test,advanced,s_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        daily = findViewById(R.id.daily);
        information = findViewById(R.id.information);
        notepad = findViewById(R.id.notepad);
        iq_test = findViewById(R.id.iq_test);
        advanced = findViewById(R.id.advanced);
        s_profile = findViewById(R.id.s_profile);

        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, Main_twoActivity.class);
                startActivity(intent);
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, SchoolLawActivity.class);
                startActivity(intent);
            }
        });

        s_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, ProfilePhotoActivity.class);
                startActivity(intent);
            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, PasswordThreeActivity.class);
                startActivity(intent);
            }
        });

        notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, TeachersNotePadActivity.class);
                startActivity(intent);
            }
        });

        iq_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, IqDisplayTActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onBackPressed(){
        super.onBackPressed();
        finishAffinity();
    }

}