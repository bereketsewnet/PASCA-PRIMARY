package com.example.pasca_primary;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import com.example.pasca_primary.Fragments.ChatsFragment;
import com.example.pasca_primary.Fragments.NewsFragment;
import com.example.pasca_primary.Fragments.ProfilePasswordFragment;
import com.example.pasca_primary.Model.Chats;
import com.example.pasca_primary.Model.Users;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Toolbar toolbar;

    CircleImageView imageView;
    TextView username;
    String uid;

    DatabaseReference reference;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAuth = FirebaseAuth.getInstance();

        //casting of the views
        imageView = findViewById(R.id.profile_image);
        username = findViewById(R.id.usernameonmainactivity);
        uid = getIntent().getStringExtra("uid");





        toolbar = findViewById(R.id.toolbarmain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final  TabLayout tabLayout = findViewById(R.id.tablayout);
        final   ViewPager viewPager = findViewById(R.id.viewPager);
        // if user id is empty back to mainStudent activity and not other function done
        if (uid == null) {
            Intent intent = new Intent(MainActivity.this,StudentsHomeActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Please Back To Connection a Moment", Toast.LENGTH_SHORT).show();

        }else {

            reference = FirebaseDatabase.getInstance().getReference("Chats");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
                    int unread = 0;
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        Chats chats = snapshot1.getValue(Chats.class);
                        if(chats.getReciever().equals(uid) && !chats.isIsseen()){
                            unread ++;
                        }
                    }

                    if(unread == 0){
                        viewPagerAdapter.addFragment(new ChatsFragment(), getString(R.string.main_daily));
                    }else{
                        viewPagerAdapter.addFragment(new ChatsFragment(), getString(R.string.main_daily)+"("+unread+")");
                    }
                    viewPagerAdapter.addFragment(new NewsFragment(), getString(R.string.main_news));
                    viewPagerAdapter.addFragment(new ProfilePasswordFragment(), getString(R.string.main_id));


                    viewPager.setAdapter(viewPagerAdapter);
                    tabLayout.setupWithViewPager(viewPager);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


            reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                    Users users = snapshot.getValue(Users.class);

                    username.setText(users.getUsername()); // set the text of the user on textivew in toolbar

                    if (users.getImageURL().equals("default")) {

                        imageView.setImageResource(R.drawable.user);
                    } else {

                        Glide.with(getApplicationContext()).load(users.getImageURL()).into(imageView);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }


    }




    public static class ViewPagerAdapter extends FragmentPagerAdapter{

        ArrayList<Fragment> fragments;
        ArrayList<String> titles;


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

            this.fragments = new ArrayList<>();
            this. titles = new ArrayList<>();

        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment (Fragment fragment, String title) {

            fragments.add(fragment);
            titles.add(title);



        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.logout) {


            Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(MainActivity.this,
                    LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK ));

            mAuth.signOut();
            finish();
            return  true;


        }


        if(item.getItemId() == R.id.switch_user){
            Intent intent = new Intent(MainActivity.this,SwitchUserActivity.class);
            startActivity(intent);
            return  true;

        }


        if(item.getItemId() == R.id.add_user){
            Intent intent = new Intent(MainActivity.this,AddUserActivity.class);
            startActivity(intent);
            return  true;

        }


        if(item.getItemId() == R.id.main_about){
            Intent intent = new Intent(MainActivity.this,AboutUsActivity.class);
            startActivity(intent);
            return  true;


        }





        return super.onOptionsItemSelected(item);
    }


    private void Status (final String status) {

        if(uid == null){

            Intent intent = new Intent(MainActivity.this,StudentsHomeActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Please back to connection a moment!", Toast.LENGTH_SHORT).show();

        }else{

            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("status", status);

            reference.updateChildren(hashMap);

        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        Status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Status("offline");
    }


}