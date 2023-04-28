package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_oneActivity extends AppCompatActivity {

    CardView daily,information,complain,iq_test,pasca,t_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_one);

        daily = findViewById(R.id.daily);
        information = findViewById(R.id.information);
        complain = findViewById(R.id.complain);
        iq_test = findViewById(R.id.iq_test);
        pasca = findViewById(R.id.pasca);
        t_profile = findViewById(R.id.t_profile);

        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_oneActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_oneActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        t_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_oneActivity.this, ProfilePhotoTActivity.class);
                startActivity(intent);
            }
        });

        iq_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_oneActivity.this, IqDisplayActivity.class);
                startActivity(intent);
            }
        });

        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_oneActivity.this, ComplainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}