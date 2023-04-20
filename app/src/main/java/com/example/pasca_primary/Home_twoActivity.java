package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_twoActivity extends AppCompatActivity {

    CardView daily,information,complaint,iq_test,pasca,s_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        daily = findViewById(R.id.daily);
        information = findViewById(R.id.information);
        complaint = findViewById(R.id.complaint);
        iq_test = findViewById(R.id.iq_test);
        pasca = findViewById(R.id.pasca);
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
                Intent intent = new Intent(Home_twoActivity.this, Main_twoActivity.class);
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

    }
}