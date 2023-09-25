package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pasca_primary.Model.Users;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class AdminHomeActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    TextView admin_home_name;
    FirebaseAuth mAuth;
    ImageView LanA;
    String langg;
    CardView admin_show_gk,admin_show_books,admin_show_student_profile,admin_show_teacher_profile,admin_show_calendar,admin_show_fees;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_home);



        Window window = getWindow();
        // Show status bar
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide status bar
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LanA = findViewById(R.id.LanA);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mAuth = FirebaseAuth.getInstance();

        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        admin_home_name = findViewById(R.id.admin_home_name);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // home page indicator
        admin_show_gk = findViewById(R.id.admin_show_gk);
        admin_show_books = findViewById(R.id.admin_show_books);
        admin_show_student_profile = findViewById(R.id.admin_show_student_profile);
        admin_show_teacher_profile = findViewById(R.id.admin_show_teachers_profile);
        admin_show_calendar = findViewById(R.id.admin_show_calendar);
        admin_show_fees = findViewById(R.id.admin_show_fees);



        LanA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLangDialog();
            }
        });



        admin_show_gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, IqDisplayActivity.class);
                startActivity(intent);
            }
        });

        admin_show_gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, IqDisplayActivity.class);
                startActivity(intent);
            }
        });

        admin_show_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, BooksActivity.class);
                startActivity(intent);
            }
        });

        admin_show_student_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // i change student profile to chatGpt activity b/c it's not important.. student profile so i change
                Intent intent = new Intent(AdminHomeActivity.this, ChatGptActivity.class);
                startActivity(intent);
            }
        });

        admin_show_teacher_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, ProfilePhotoTActivity.class);
                startActivity(intent);
            }
        });

        admin_show_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, SchoolCalendarActivity.class);
                startActivity(intent);
            }
        });

        admin_show_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, FeesInformationActivity.class);
                startActivity(intent);
            }
        });

        // end of indicator

        // setting name to home display
        DatabaseReference reference;
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Users student_name = snapshot.getValue(Users.class);

                admin_home_name.setText(student_name.getUsername()); // set the text of the user on textivew in toolbar

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // end of setting name

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.admin_users:
                        Intent intent = new Intent(AdminHomeActivity.this,RegisterUserListActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_post:
                        Intent intent1 = new Intent(AdminHomeActivity.this,UploadNewsTActivity.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_password:
                        Intent intent2 = new Intent(AdminHomeActivity.this, ChangeProfilePassActivity.class);
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_about:
                        Intent intent3 = new Intent(AdminHomeActivity.this,AboutUsActivity.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_multi_register:
                        Intent intent4 = new Intent(AdminHomeActivity.this,BulkRegisterActivity.class);
                        startActivity(intent4);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_logout:
                        Intent intent5 = new Intent(AdminHomeActivity.this,LoginActivity.class);
                        startActivity(intent5);
                        finish();
                        mAuth.signOut();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

                return true;
            }
        });

    }

    private void showChangeLangDialog() {
        final String[] listItems = {"English", "አማርኛ", "عربي", "Français","中国人"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminHomeActivity.this);
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
                }else if(which == 4){
                    setLocal("zh");
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




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}