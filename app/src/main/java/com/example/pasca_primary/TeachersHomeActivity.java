package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pasca_primary.Fragments.ProfilePasswordFragment;
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

    CardView teachers_daily,teachers_rank,teachers_notepad,teachers_gk,teachers_books,teachers_ai;
    String uid;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    TextView stubjectname;
    CircleImageView imageView;
    ImageView lanT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_home);

        teachers_daily = findViewById(R.id.teachers_daily);
        teachers_rank = findViewById(R.id.teachers_rank);
        teachers_notepad = findViewById(R.id.teachers_notepad);
        teachers_gk = findViewById(R.id.teachers_gk);
        teachers_books = findViewById(R.id.teachers_books);
        teachers_ai = findViewById(R.id.teachers_ai);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        stubjectname = findViewById(R.id.stubjectname);
        imageView = findViewById(R.id.teachers_home_profile);
        lanT = findViewById(R.id.lanT);



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
        // setting long press to profile password
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Fragment fragment = new ProfilePasswordFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.teachers_home_container,fragment).commit();

                return true;
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

        teachers_ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeachersHomeActivity.this, ChatGptActivity.class);
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

        //setting language
        lanT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLangDialog();
            }
        });



    }

    private void showChangeLangDialog() {
        final String[] listItems = {"En", "አማ", "عربي", "Fr"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TeachersHomeActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0){
                    setLocal("en");
                    recreate();
                }else if(which == 1){
                    setLocal("am");
                    recreate();
                }else if(which == 2){
                    setLocal("ar");
                    recreate();
                }else if(which == 3){
                    setLocal("fr");
                    recreate();
                }
                dialog.dismiss();

            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    public void setLocal(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        // save data
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void onBackPressed(){
        super.onBackPressed();
        finishAffinity();
    }

}