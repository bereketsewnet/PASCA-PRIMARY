package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
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

public class StudentsHomeActivity extends AppCompatActivity {

    LinearLayout  student_law,student_map,student_adv;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    String uid;
    public static final String[] language = {"Language", "English", "አማረኛ", "عربي", "Français"};
    TextView textView2;
    CircleImageView imageView;
    Spinner spinner;
    ConstraintLayout student_ai,student_daily, student_library, student_rank, student_gk, student_t, student_complain, student_calendar, student_fees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_home);

           // Upper 3 cards
        student_law = findViewById(R.id.student_law);
        student_map = findViewById(R.id.student_map);
        student_adv = findViewById(R.id.student_adv);
        //lower 8 cards
        student_daily = findViewById(R.id.student_daily);
        student_library = findViewById(R.id.student_library);
        student_rank = findViewById(R.id.student_rank);
        student_gk = findViewById(R.id.student_gk);
        student_t = findViewById(R.id.student_t);
        student_calendar = findViewById(R.id.student_calendar);
        student_fees = findViewById(R.id.student_fees);
        student_complain = findViewById(R.id.student_complain);
        student_ai = findViewById(R.id.student_ai);
        // others
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        textView2 = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView2);
        spinner = findViewById(R.id.lan);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,language);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if(selectedLang.equals("English")){
                    setLocal(StudentsHomeActivity.this,"en");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("አማረኛ")){
                    setLocal(StudentsHomeActivity.this,"am");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("عربي")){
                    setLocal(StudentsHomeActivity.this,"ar");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("Français")){
                    setLocal(StudentsHomeActivity.this,"fr");
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        student_law.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, SchoolLawActivity.class);
                startActivity(intent);
            }
        });

        student_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        student_adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        student_ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, ChatGptActivity.class);
                startActivity(intent);
            }
        });



        student_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, MainActivity.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });

        student_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, BooksActivity.class);
                startActivity(intent);
            }
        });

        student_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, RankActivity.class);
                startActivity(intent);
            }
        });

        student_gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, IqDisplayActivity.class);
                startActivity(intent);
            }
        });

        student_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, ProfilePhotoTActivity.class);
                startActivity(intent);
            }
        });



        student_complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, ComplainActivity.class);
                startActivity(intent);
            }
        });

        student_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, SchooCalendarActivity.class);
                startActivity(intent);
            }
        });

        student_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, FeesInformationActivity.class);
                startActivity(intent);
            }
        });




        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
         uid = firebaseUser.getUid();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Users student_name = snapshot.getValue(Users.class);

                textView2.setText(student_name.getUsername()); // set the text of the user on textivew in toolbar


                if (student_name.getImageURL().equals("default")) {

                    imageView.setImageResource(R.drawable.pppp);
                } else {
                    // imageView.setImageResource(R.drawable.pppp);
                    Glide.with(getApplicationContext()).load(student_name.getImageURL()).into(imageView);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}