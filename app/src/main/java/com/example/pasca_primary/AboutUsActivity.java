package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {

    TextView official_school_website,official_website,follow_facebook,follow_instagram,author,privacy_policy;
    ImageView official_school_website_link,official_website_link,follow_facebook_link,follow_instagram_link,author_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        official_school_website = findViewById(R.id.official_school_website);
        official_school_website_link = findViewById(R.id.official_school_website_link);
        official_website = findViewById(R.id.official_website);
        official_website_link = findViewById(R.id.official_website_link);
        follow_facebook = findViewById(R.id.follow_facebook);
        follow_facebook_link = findViewById(R.id.follow_facebook_link);
        follow_instagram = findViewById(R.id.follow_instagram);
        follow_instagram_link = findViewById(R.id.follow_instagram_link);
        author = findViewById(R.id.author);
        author_link = findViewById(R.id.author_link);
        privacy_policy = findViewById(R.id.privacy_policy);

        official_school_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AboutUsActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        official_school_website_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        official_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        official_website_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        follow_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        follow_instagram_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "@Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        follow_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        follow_facebook_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Bereket Sewnet", Toast.LENGTH_SHORT).show();

            }
        });

        author_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Bereket Sewnet", Toast.LENGTH_SHORT).show();

            }
        });

        privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Privacy Policy", Toast.LENGTH_SHORT).show();
            }
        });

    }
}