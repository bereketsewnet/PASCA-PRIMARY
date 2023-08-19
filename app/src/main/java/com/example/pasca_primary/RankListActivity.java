package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pasca_primary.Adapters.UserAdapterRank;
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

public class RankListActivity extends AppCompatActivity {

    private RecyclerView recyclerView_rank;

    private UserAdapterRank userAdapter;
    private List<Users> mUsers;
    Button filter_rank1,filter_rank2;
    String filter_rank_store;

    EditText search_users_rank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_list);

        filter_rank1 = findViewById(R.id.filter_rank1);
        filter_rank2 = findViewById(R.id.filter_rank2);
        recyclerView_rank = findViewById(R.id.recyclerview_users_rank);
        recyclerView_rank.setHasFixedSize(true);
        recyclerView_rank.setLayoutManager(new LinearLayoutManager(RankListActivity.this));

        readUsers();
        mUsers = new ArrayList<>();

        filter_rank_store = "search";
        filter_rank1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                filter_rank_store = "student_class";
                filter_rank1.setBackgroundColor(Color.BLACK);

            }

        });

        filter_rank2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter_rank_store = "search";
                filter_rank2.setBackgroundColor(Color.BLACK);
            }
        });

        search_users_rank = findViewById(R.id.search_users_rank);


        search_users_rank.addTextChangedListener(new TextWatcher() {
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
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild(filter_rank_store)
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

                userAdapter = new UserAdapterRank(RankListActivity.this, mUsers, false);
                recyclerView_rank.setAdapter(userAdapter);
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
                if (search_users_rank.getText().toString().equals("")) {
                    mUsers.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Users user = snapshot.getValue(Users.class);

                        if (!user.getId().equals(firebaseUser.getUid())) {
                            if(user.getUsertype() == 0){
                                mUsers.add(user);
                            }
                        }

                    }

                    userAdapter = new UserAdapterRank(RankListActivity.this, mUsers, false);
                    recyclerView_rank.setAdapter(userAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}