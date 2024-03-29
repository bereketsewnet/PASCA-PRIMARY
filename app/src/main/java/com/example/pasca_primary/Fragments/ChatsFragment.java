package com.example.pasca_primary.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pasca_primary.Adapters.UserAdapter;
import com.example.pasca_primary.Model.Chatslist;
import com.example.pasca_primary.Model.Users;
import com.example.pasca_primary.R;
import com.example.pasca_primary.StartChatPasswordSActivity;
import com.example.pasca_primary.StartChatPasswordTActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {


    List<Chatslist> userlist;
    List<Users> mUsers;
    RecyclerView recyclerView;
    UserAdapter mAdapter;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;
    FloatingActionButton fab_start_chat;
    int usertype_flot;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chats, container, false);


        userlist = new ArrayList<>();

        fab_start_chat = view.findViewById(R.id.fab_start_chat);
        recyclerView = view.findViewById(R.id.chat_recyclerview_chatfrag);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("Chatslist")
                .child(firebaseUser.getUid());



        fab_start_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(usertype_flot == 0){
                    Intent intent = new Intent(getActivity(), StartChatPasswordSActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), StartChatPasswordTActivity.class);
                    startActivity(intent);
                }

            }
        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userlist.clear();
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Chatslist chatslist = ds.getValue(Chatslist.class);

                    userlist.add(chatslist);



                }

                ChatsListings();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }



    private void ChatsListings() {

        mUsers = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mUsers.clear();

                for (DataSnapshot ds: snapshot.getChildren()) {

                    Users users = ds.getValue(Users.class);

                    for (Chatslist chatslist: userlist) {


                        if (users.getId().equals(chatslist.getId())) {

                            mUsers.add(users);

                        }




                    }


                }

                mAdapter = new UserAdapter(getContext(), mUsers, true);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // start

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child("Users").child(user.getUid()).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int usertype = snapshot.getValue(Integer.class);
                usertype_flot = usertype;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //end



    }
}