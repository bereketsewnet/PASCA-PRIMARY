package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreenActivity extends AppCompatActivity {

    private TextView logo_name, slogan,splash_screen_login;
    private ImageView logo;
    private View topView1,topView2,topView3;
    private View bottomView1,bottomView2,bottomView3;
    private ProgressBar accelerate;

    private int count = 0;
    private int UserTypeInt;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        |View.SYSTEM_UI_FLAG_FULLSCREEN
        |View.SYSTEM_UI_FLAG_IMMERSIVE);

                setContentView(R.layout.activity_splash_screen);




       logo_name = findViewById(R.id.logo_name);
       slogan = findViewById(R.id.slogan);

       logo = findViewById(R.id.splash_screen_logo);
        accelerate = findViewById(R.id.accelerate);
        splash_screen_login = findViewById(R.id.splash_screen_login);

       topView1 = findViewById(R.id.topView1);
       topView2 = findViewById(R.id.topView2);
       topView3 = findViewById(R.id.topView3);
       //00041c

       bottomView1 = findViewById(R.id.bottomView1);
       bottomView2 = findViewById(R.id.bottomView2);
       bottomView3 = findViewById(R.id.bottomView3);

        Animation logoAnimation = AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.zoom_animation);
        Animation nameAnimation = AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.zoom_animation);

        Animation topView1Animation = AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.top_views_animation);
        Animation topView2Animation = AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.top_views_animation);
        Animation topView3Animation = AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.top_views_animation);

        Animation bottomView1Animation = AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.bottom_views_animation);
        Animation bottomView2Animation = AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.bottom_views_animation);
        Animation bottomView3Animation = AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.bottom_views_animation);

        topView1.startAnimation(topView1Animation);
        bottomView1.startAnimation(bottomView1Animation);

        topView1Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {




            }

            @Override
            public void onAnimationEnd(Animation animation) {

                topView2.setVisibility(View.VISIBLE);
                bottomView2.setVisibility(View.VISIBLE);

                topView2.startAnimation(topView2Animation);
                bottomView2.startAnimation(bottomView2Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topView2Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {




            }

            @Override
            public void onAnimationEnd(Animation animation) {

                topView3.setVisibility(View.VISIBLE);
                bottomView3.setVisibility(View.VISIBLE);

                topView3.startAnimation(topView3Animation);
                bottomView3.startAnimation(bottomView3Animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        topView3Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {




            }

            @Override
            public void onAnimationEnd(Animation animation) {

                logo.setVisibility(View.VISIBLE);
                logo.startAnimation(logoAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {




            }

            @Override
            public void onAnimationEnd(Animation animation) {

                logo_name.setVisibility(View.VISIBLE);
                logo_name.startAnimation(nameAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        nameAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {




            }

            @Override
            public void onAnimationEnd(Animation animation) {

                slogan.setVisibility(View.VISIBLE);
                accelerate.setVisibility(View.VISIBLE);
                final String animateTxt = slogan.getText().toString();
                slogan.setText(" ");
                count = 0;


                new CountDownTimer(animateTxt.length() * 10L,10){
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTick(long millisUntilFinished) {
                        slogan.setText(slogan.getText().toString()+animateTxt.charAt(count));
                        count++;


                    }

                    @Override
                    public void onFinish() {



                         // checker in
                        FirebaseUser user1;

                        user1 = FirebaseAuth.getInstance().getCurrentUser();
                        if (user1 == null) {

                            accelerate.setVisibility(View.INVISIBLE);
                            splash_screen_login.setVisibility(View.VISIBLE);

                        }else{
                            mAuth = FirebaseAuth.getInstance();
                            FirebaseUser user = mAuth.getCurrentUser();

                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                            firebaseDatabase.getReference().child("Users").child(user.getUid()).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int usertype = snapshot.getValue(Integer.class);

                                    FirebaseUser user;

                                    user = FirebaseAuth.getInstance().getCurrentUser();



                                    if(usertype==0){
                                        Intent intent = new Intent(SplashScreenActivity.this,StudentsHomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }


                                    if(usertype==1){
                                        Intent intent = new Intent(SplashScreenActivity.this,Home_twoActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }





                                    if(usertype==2){
                                        Intent intent = new Intent(SplashScreenActivity.this,AdminHomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }






                        // end


                    }


                }.start();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        splash_screen_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}