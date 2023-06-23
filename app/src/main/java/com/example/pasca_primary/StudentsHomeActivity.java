package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pasca_primary.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentsHomeActivity extends AppCompatActivity {

    LinearLayout student_law, student_map, student_adv;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    TextView textView2;
    ImageView imageView4;
    ConstraintLayout student_daily, student_library, student_rank, student_gk, student_gkk, student_complain, student_calendar, student_fees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_home);

        // Upper 3 cards
        student_adv = findViewById(R.id.student_adv);
        student_map = findViewById(R.id.student_map);
        student_law = findViewById(R.id.student_law);
        //lower 8 cards
        student_daily = findViewById(R.id.student_daily);
        student_library = findViewById(R.id.student_library);
        student_rank = findViewById(R.id.student_rank);
        student_gk = findViewById(R.id.student_gk);
        student_gkk = findViewById(R.id.student_gkk);
        student_calendar = findViewById(R.id.student_calendar);
        student_fees = findViewById(R.id.student_fees);
        student_complain = findViewById(R.id.student_complain);
        // others
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        textView2 = findViewById(R.id.textView2);
        imageView4 = findViewById(R.id.imageView4);

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, ChatGptActivity.class);
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

        student_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsHomeActivity.this, MainActivity.class);
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

        student_gkk.setOnClickListener(new View.OnClickListener() {
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
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Users student_name = snapshot.getValue(Users.class);

                textView2.setText(student_name.getUsername()); // set the text of the user on textivew in toolbar


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}