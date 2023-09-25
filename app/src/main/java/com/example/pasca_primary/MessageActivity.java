package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import com.example.pasca_primary.Adapters.MessageAdapter;
import com.example.pasca_primary.Fragments.ProfilePasswordFragment;
import com.example.pasca_primary.Model.Chats;
import com.example.pasca_primary.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {


    String friendid, message, myid,friendName,friendEmail,friendPassword,getYourName;
    int myUserType;
    CircleImageView imageViewOnToolbar;
    TextView usernameonToolbar;
    Toolbar toolbar;
    FirebaseUser firebaseUser;

    EditText et_message;
    Button send,paste;

    DatabaseReference reference;

    List<Chats> chatsList;
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    ValueEventListener seenlistener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        toolbar = findViewById(R.id.toolbar_message);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        imageViewOnToolbar = findViewById(R.id.profile_image_toolbar_message);
        usernameonToolbar = findViewById(R.id.username_ontoolbar_message);

        recyclerView = findViewById(R.id.recyclerview_messages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        send = findViewById(R.id.send_messsage_btn);
        paste = findViewById(R.id.paste);
        et_message = findViewById(R.id.edit_message_text);
        loadyourName();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        myid = firebaseUser.getUid();// my id or the one who is loggedin
        getUsertype(myid); // get my usertype for dialog

        friendid = getIntent().getStringExtra("friendid"); // retreive the friendid when we click on the item

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(friendid);


            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Users users = snapshot.getValue(Users.class);
                    if(users != null) {

                        usernameonToolbar.setText(users.getUsername()); // set the text of the user on textivew in toolbar
                        friendName = users.getUsername();
                        friendEmail = users.getEmail();
                        friendPassword = users.getPassword();
                        if (users.getImageURL().equals("default")) {

                            imageViewOnToolbar.setImageResource(R.drawable.user);
                        } else {

                            Glide.with(getApplicationContext()).load(users.getImageURL()).into(imageViewOnToolbar);
                        }

                        readMessages(myid, friendid, users.getImageURL());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        seenMessage(friendid);




        imageViewOnToolbar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Fragment fragment = new ProfilePasswordFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.message_activity_container,fragment).commit();

                return true;
            }
        });


         et_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (s.toString().length() > 0) {

                    send.setEnabled(true);

                } else {

                    send.setEnabled(false);


                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                String text = et_message.getText().toString();

                if (!text.startsWith(" ")) {
                    et_message.getText().insert(0, " ");

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });






        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   message = et_message.getText().toString();
                   sendMessage(myid, friendid, message);
                   et_message.setText("");

            }
        });

        paste.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                send.setEnabled(true);
                if(myUserType == 0) {
                    StudentDialog(myid, friendid);
                    et_message.setText("");
                }else if(myUserType == 1 || myUserType == 2 || myUserType == 3){
                    TeachersDialog(myid,friendid);
                    et_message.setText("");
                }else{
                    Toast.makeText(MessageActivity.this, "You Are Not Found", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                android.content.ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = clipboardManager.getPrimaryClip();
                ClipData.Item item = clipData.getItemAt(0);
                String text1 = item.getText().toString();
                if(text1 != null){
                    et_message.setText(text1);
                }



            }
        });





    }

    private void getUsertype(String myid) {


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(myid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Users users = snapshot.getValue(Users.class);
                
                myUserType = users.getUsertype(); // setting usertype to myusertype variable
                

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void StudentDialog(String mid, String frId) {
        final String[] listItems = {getString(R.string.seenanddone),getString(R.string.seen), getString(R.string.seennotdone)};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MessageActivity.this);
        mBuilder.setTitle("Check-In...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0){
                    sendMessage(mid, frId, "Seen And Done.\n@ "+getYourName); //
                }else if(which == 1){
                    sendMessage(mid, frId, "Seen.\n@ "+getYourName);
                }else if(which == 2){
                    sendMessage(mid, frId, "Seen But Not Done.\n@ "+getYourName);
                }
                dialog.dismiss();

            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    private void TeachersDialog(String mid, String frId) {
        final String[] listItems = {"Not Seen","Incomplete HomeWork", "Incomplete ClassWork",
                                    "Check News","Check Progress","Great Day","Forgot Material"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MessageActivity.this);
        mBuilder.setTitle("Check-In...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0){
                    sendMessage(mid, frId, friendName+"\'s"+" Sent Action Was Not Seen");
                }else if(which == 1){
                    sendMessage(mid, frId, friendName+"\'s"+" HomeWork Was Incomplete");
                }else if(which == 2){
                    sendMessage(mid, frId, friendName+"\'s"+" ClassWork Was Incomplete");
                }else if(which == 3){
                    sendMessage(mid, frId, "Please Check "+friendName+"\'s"+" News Page");
                }else if(which == 4){
                    sendMessage(mid, frId, "Please Check "+friendName+"\'s"+" Progress Page");
                }else if(which == 5){
                    sendMessage(mid, frId, "Today Was A Good Day For "+friendName);
                }else if(which == 6){
                    sendMessage(mid, frId, friendName+"\'s"+" Educational Equipment Was Incomplete");
                }
                dialog.dismiss();

            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }


    private void seenMessage(final String friendid) {

        reference = FirebaseDatabase.getInstance().getReference("Chats");


        seenlistener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren()) {

                    Chats chats = ds.getValue(Chats.class);


                    if (chats.getReciever().equals(myid) && chats.getSender().equals(friendid)) {

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("isseen", true);
                        ds.getRef().updateChildren(hashMap);

                    }




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }


    private void readMessages(final String myid, final String friendid, final String imageURL) {

        chatsList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatsList.clear();

                for (DataSnapshot ds: snapshot.getChildren()) {

                    Chats chats = ds.getValue(Chats.class);


                    if (chats.getSender().equals(myid) && chats.getReciever().equals(friendid) ||
                            chats.getSender().equals(friendid) && chats.getReciever().equals(myid)) {

                        chatsList.add(chats);
                    }

                    messageAdapter = new MessageAdapter(MessageActivity.this, chatsList, imageURL);
                    recyclerView.setAdapter(messageAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void sendMessage(final String myid, final String friendid, final String message) {


        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

                String key = reference.getKey();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("sender", myid);
                hashMap.put("reciever", friendid);
                hashMap.put("message", message);
                hashMap.put("isseen", false);
                hashMap.put("key",key);

                reference.child("Chats").push().setValue(hashMap);


                final DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Chatslist").child(myid).child(friendid);

                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        if (!snapshot.exists()) {


                            reference1.child("id").setValue(friendid);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }




    private void Status (final String status) {


        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("status", status);

                reference.updateChildren(hashMap);



    }

    // load save file
    public void loadyourName(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String Name = preferences.getString("YourName","");
        if (!Name.equals("")){
            // default  is visible the edite text
           getYourName = Name;

        }else{
            getYourName = "UnKnown User";
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
        reference.removeEventListener(seenlistener);
    }


}