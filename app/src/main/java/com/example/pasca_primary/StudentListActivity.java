package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pasca_primary.Adapters.UserAdapter;
import com.example.pasca_primary.Model.Users;
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

public class StudentListActivity extends AppCompatActivity {

    private RecyclerView recyclerView_l;

    private UserAdapter userAdapter_l;
    private List<Users> mUsers_l;

    EditText search_users_l;

    Button filter_s1, filter_s2;
    String filter_s_store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        recyclerView_l = findViewById(R.id.recyclerview_user_l);
        recyclerView_l.setHasFixedSize(true);
        recyclerView_l.setLayoutManager(new LinearLayoutManager(StudentListActivity.this));
        mUsers_l = new ArrayList<>();
        filter_s1 = findViewById(R.id.filter_s1);
        filter_s2 = findViewById(R.id.filter_s2);
        filter_s_store = "search";

        filter_s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter_s_store = "student_class";
                filter_s1.setBackgroundColor(Color.BLACK);
            }
        });

        filter_s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter_s_store = "search";
                filter_s2.setBackgroundColor(Color.BLACK);
            }
        });

        readUsers();

        search_users_l = findViewById(R.id.search_user_l);
        search_users_l.addTextChangedListener(new TextWatcher() {
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

    }

    private void searchUsers(String s) {

        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild(filter_s_store)
                .startAt(s)
                .endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers_l.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Users user = snapshot.getValue(Users.class);

                    assert user != null;
                    assert fuser != null;
                    if (!user.getId().equals(fuser.getUid())){
                        mUsers_l.add(user);
                    }
                }

                userAdapter_l = new UserAdapter(StudentListActivity.this, mUsers_l, false);
                recyclerView_l.setAdapter(userAdapter_l);
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
                if (search_users_l.getText().toString().equals("")) {
                    mUsers_l.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Users user = snapshot.getValue(Users.class);

                        if (!user.getId().equals(firebaseUser.getUid())) {
                            if(user.getUsertype() == 0){
                                mUsers_l.add(user);
                            }

                        }

                    }

                    userAdapter_l = new UserAdapter(StudentListActivity.this, mUsers_l, false);
                    recyclerView_l.setAdapter(userAdapter_l);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}