package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pasca_primary.Adapters.UserAdapterRank;
import com.example.pasca_primary.Adapters.UserRegisterAdapter;
import com.example.pasca_primary.Model.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegisterUserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView_register;

    private UserRegisterAdapter userAdapter;
    private List<Users> mUsers;
    Button filter_register1,filter_register2;
    String filter_register_store;
    FloatingActionButton fab_register;

    EditText search_users_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_list);

        filter_register1 = findViewById(R.id.filter_register1);
        filter_register2 = findViewById(R.id.filter_register2);
        fab_register = findViewById(R.id.fab_register);
        recyclerView_register = findViewById(R.id.recyclerview_users_register);
        recyclerView_register.setHasFixedSize(true);
        recyclerView_register.setLayoutManager(new LinearLayoutManager(RegisterUserListActivity.this));

        readUsers();
        mUsers = new ArrayList<>();

        filter_register_store = "search";
        filter_register1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                filter_register_store = "student_class";
                filter_register1.setBackgroundColor(Color.BLACK);

            }

        });

        filter_register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter_register_store = "search";
                filter_register2.setBackgroundColor(Color.BLACK);
            }
        });

        search_users_register = findViewById(R.id.search_users_register);


        search_users_register.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUsers(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        fab_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUserListActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });



    }


    private void searchUsers(String s) {

        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild(filter_register_store)
                .startAt(s)
                .endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Users user = snapshot.getValue(Users.class);

                    assert user != null;
                    assert fuser != null;
                    if (!user.getId().equals(fuser.getUid())){
                        mUsers.add(user);
                    }
                }

                userAdapter = new UserRegisterAdapter(RegisterUserListActivity.this, mUsers, false);
                recyclerView_register.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void readUsers() {

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (search_users_register.getText().toString().equals("")) {
                    mUsers.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Users user = snapshot.getValue(Users.class);

                        if (!user.getId().equals(firebaseUser.getUid())) {
                            if(user.getUsertype() == 0){
                                mUsers.add(user);
                            }
                        }

                    }

                    userAdapter = new UserRegisterAdapter(RegisterUserListActivity.this, mUsers, false);
                    recyclerView_register.setAdapter(userAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}