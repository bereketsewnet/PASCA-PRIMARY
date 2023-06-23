package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_twoActivity extends AppCompatActivity {

    CardView teachers_daily,teachers_rank,teachers_notepad,teachers_gk,teachers_books,teachers_s_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        teachers_daily = findViewById(R.id.teachers_daily);
        teachers_rank = findViewById(R.id.teachers_rank);
        teachers_notepad = findViewById(R.id.teachers_notepad);
        teachers_gk = findViewById(R.id.teachers_gk);
        teachers_books = findViewById(R.id.teachers_books);
        teachers_s_profile = findViewById(R.id.teachers_s_profile);

        teachers_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, Main_twoActivity.class);
                startActivity(intent);
            }
        });

        teachers_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, RankListActivity.class);
                startActivity(intent);
            }
        });

        teachers_s_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, ProfilePhotoActivity.class);
                startActivity(intent);
            }
        });

        teachers_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, BooksActivity.class);
                startActivity(intent);
            }
        });

        teachers_notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_twoActivity.this, TeachersNotePadActivity.class);
                startActivity(intent);
            }
        });

        teachers_gk.setOnClickListener(new View.OnClickListener() {
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