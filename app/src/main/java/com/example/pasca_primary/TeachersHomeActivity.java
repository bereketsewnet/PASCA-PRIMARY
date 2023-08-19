package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pasca_primary.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeachersHomeActivity extends AppCompatActivity {

    CardView teachers_daily,teachers_rank,teachers_notepad,teachers_gk,teachers_books,teachers_s_profile;
    String uid;
    DatabaseReference reference;
    public static final String[] languageT = {"Lang","En", "አማ", "عربي", "Fr"};
    FirebaseUser firebaseUser;
    TextView stubjectname;
    CircleImageView imageView;
    Spinner spinnerT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_home);

        teachers_daily = findViewById(R.id.teachers_daily);
        teachers_rank = findViewById(R.id.teachers_rank);
        teachers_notepad = findViewById(R.id.teachers_notepad);
        teachers_gk = findViewById(R.id.teachers_gk);
        teachers_books = findViewById(R.id.teachers_books);
        teachers_s_profile = findViewById(R.id.teachers_s_profile);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        stubjectname = findViewById(R.id.stubjectname);
        imageView = findViewById(R.id.Student_home_profile);
        spinnerT = findViewById(R.id.lanT);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,languageT);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerT.setAdapter(adapter);
        spinnerT.setSelection(0);
        spinnerT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if(selectedLang.equals("En")){
                    setLocal(TeachersHomeActivity.this,"en");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("አማ")){
                    setLocal(TeachersHomeActivity.this,"am");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("عربي")){
                    setLocal(TeachersHomeActivity.this,"ar");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("Fr")){
                    setLocal(TeachersHomeActivity.this,"fr");
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // get user id from Database
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        uid = firebaseUser.getUid();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Users userModel = snapshot.getValue(Users.class);
                stubjectname.setText(userModel.getUsername());// set the text of the user on textivew in toolbar

                if (userModel.getImageURL().equals("default")) {

                    imageView.setImageResource(R.drawable.pppp);
                } else {
                    //  imageView.setImageResource(R.drawable.pppp);
                    Glide.with(getApplicationContext()).load(userModel.getImageURL()).into(imageView);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // end

        teachers_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeachersHomeActivity.this, Main_twoActivity.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });

        teachers_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeachersHomeActivity.this, RankListActivity.class);
                startActivity(intent);
            }
        });

        teachers_s_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeachersHomeActivity.this, ProfilePhotoActivity.class);
                startActivity(intent);
            }
        });

        teachers_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeachersHomeActivity.this, BooksActivity.class);
                startActivity(intent);
            }
        });

        teachers_notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeachersHomeActivity.this, TeachersNotePadActivity.class);
                intent.putExtra("userId",uid);
                startActivity(intent);
            }
        });

        teachers_gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeachersHomeActivity.this, IqDisplayTActivity.class);
                startActivity(intent);
            }
        });



    }

    public void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config =resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }

    

    public void onBackPressed(){
        super.onBackPressed();
        finishAffinity();
    }

}