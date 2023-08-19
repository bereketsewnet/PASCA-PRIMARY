package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class AdminHomeActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    Spinner spinnerA;
    public static final String[] languageA = {"Lang","En", "አማ", "عربي", "Fr"};
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



        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mAuth = FirebaseAuth.getInstance();

        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);

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

        spinnerA = findViewById(R.id.lanA);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,languageA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerA.setAdapter(adapter);
        spinnerA.setSelection(0);
        spinnerA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if(selectedLang.equals("En")){
                    setLocal(AdminHomeActivity.this,"en");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("አማ")){
                    setLocal(AdminHomeActivity.this,"am");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("عربي")){
                    setLocal(AdminHomeActivity.this,"ar");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("Fr")){
                    setLocal(AdminHomeActivity.this,"fr");
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                Intent intent = new Intent(AdminHomeActivity.this, ProfilePhotoActivity.class);
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
                Intent intent = new Intent(AdminHomeActivity.this, SchooCalendarActivity.class);
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

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.admin_register:
                        Intent intent = new Intent(AdminHomeActivity.this,RegisterActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_news:
                        Intent intent1 = new Intent(AdminHomeActivity.this,UploadNewsTActivity.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_rank:
                        Intent intent2 = new Intent(AdminHomeActivity.this, RankListActivity.class);
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_iq:
                        Intent intent3 = new Intent(AdminHomeActivity.this,UploadIqTActivity.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_book:
                        Intent intent4 = new Intent(AdminHomeActivity.this,UploadPdfActivity.class);
                        startActivity(intent4);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_student:
                        Intent intent5 = new Intent(AdminHomeActivity.this,UploadActivity.class);
                        startActivity(intent5);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_teacher:
                        Intent intent6 = new Intent(AdminHomeActivity.this,UploadTActivity.class);
                        startActivity(intent6);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_calendar:
                        Intent intent7 = new Intent(AdminHomeActivity.this,UploadCalendarActivity.class);
                        startActivity(intent7);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.admin_fees:
                        Intent intent8 = new Intent(AdminHomeActivity.this,UploadFeesInformationActivity.class);
                        startActivity(intent8);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_start_chat_pass_t:
                        Intent intent9 = new Intent(AdminHomeActivity.this, ChangeStartChatPassTeActivity.class);
                        startActivity(intent9);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_start_chat_pass_s:
                        Intent intent10 = new Intent(AdminHomeActivity.this, ChangeStartChatPassSActivity.class);
                        startActivity(intent10);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_profile_pass:
                        Intent intent11 = new Intent(AdminHomeActivity.this, ChangeProfilePassActivity.class);
                        startActivity(intent11);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.admin_about:
                        Intent intent12 = new Intent(AdminHomeActivity.this,AboutUsActivity.class);
                        startActivity(intent12);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.admin_logout:
                        Intent intent13 = new Intent(AdminHomeActivity.this,LoginActivity.class);
                        startActivity(intent13);
                        mAuth.signOut();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

                return true;
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

}