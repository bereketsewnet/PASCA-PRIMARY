package com.example.pasca_primary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {

    TextView check_for_update,official_website,follow_facebook,follow_instagram,author,terms_of_service,privacy_policy;
    ImageView check_for_update_link,official_website_link,follow_facebook_link,follow_instagram_link,author_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        check_for_update = findViewById(R.id.check_for_update);
        check_for_update_link = findViewById(R.id.check_for_update_link);
        official_website = findViewById(R.id.official_website);
        official_website_link = findViewById(R.id.official_website_link);
        follow_facebook = findViewById(R.id.follow_facebook);
        follow_facebook_link = findViewById(R.id.follow_facebook_link);
        follow_instagram = findViewById(R.id.follow_instagram);
        follow_instagram_link = findViewById(R.id.follow_instagram_link);
        author = findViewById(R.id.author);
        author_link = findViewById(R.id.author_link);
        terms_of_service = findViewById(R.id.terms_of_service);
        privacy_policy = findViewById(R.id.privacy_policy);

        check_for_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AboutUsActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        check_for_update_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Comming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        official_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "WWW.Pasca.com", Toast.LENGTH_SHORT).show();

            }
        });

        official_website_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "WWW.Pasca.com", Toast.LENGTH_SHORT).show();

            }
        });

        follow_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "@pasca-primary", Toast.LENGTH_SHORT).show();

            }
        });

        follow_instagram_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "@pasca-primary", Toast.LENGTH_SHORT).show();

            }
        });

        follow_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "@pasca-primary", Toast.LENGTH_SHORT).show();

            }
        });

        follow_facebook_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "@pasca-primary", Toast.LENGTH_SHORT).show();

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

        terms_of_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUsActivity.this, "Terms of Service", Toast.LENGTH_SHORT).show();
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