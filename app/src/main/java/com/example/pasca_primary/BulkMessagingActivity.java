package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.pasca_primary.Adapters.UserAdapter;
import com.example.pasca_primary.Model.ChangeProfilePass;
import com.example.pasca_primary.Model.MultiSelectionId;
import com.example.pasca_primary.Model.Users;
import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BulkMessagingActivity extends AppCompatActivity {

   EditText searchView;
    Dialog SuccessDialog;
   ListView listView;
   List<String> arrayList;
   ArrayAdapter adapter;
   String uid,Msg;
    String fiter_search;
   Button filter_l1,filter_l2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_messaging);

        searchView = findViewById(R.id.search_list);
        listView = findViewById(R.id.ListView);
        arrayList = new ArrayList<String>();
        Button show = findViewById(R.id.multi_send_btn);
        filter_l1 = findViewById(R.id.filter_l1);
        filter_l2 = findViewById(R.id.filter_l2);

        uid = getIntent().getStringExtra("uid");
        Msg = getIntent().getStringExtra("bulkMsg");

        //Create the Dialog here
        SuccessDialog = new Dialog(this);
        SuccessDialog.setContentView(R.layout.custom_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SuccessDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        SuccessDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SuccessDialog.setCancelable(false); //Optional
        SuccessDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button Okay = SuccessDialog.findViewById(R.id.btn_okay);

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SuccessDialog.dismiss();
            }
        });

        // end of success dialog

        fiter_search = "search";

        filter_l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fiter_search = "student_class";

            }
        });

        filter_l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fiter_search = "search";

            }
        });

        searchView.addTextChangedListener(new TextWatcher() {
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

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                     String retirveName;
                    for (int i = 0; i < listView.getCount(); i++) {
                        if (listView.isItemChecked(i)) {
                            retirveName = (String) listView.getItemAtPosition(i);
                            FindtheId(retirveName);

                        }
                    }

            }
        });

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if  (searchView.getText().toString().equals("")) {
                arrayList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Users user = snapshot.getValue(Users.class);
                        String na = user.getUsername();
                        String idd = user.getId();

                        if (!user.getId().equals(firebaseUser.getUid())) {

                                arrayList.add(na);
                        }
                        adapter =new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice,arrayList);
                        listView.setAdapter(adapter);

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }

    private void FindtheId(String retirveName) {


        final CustomProgressDialog dialog = new CustomProgressDialog(BulkMessagingActivity.this);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("MultiSenderId").child(retirveName);

        dialog.show();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                MultiSelectionId multiSelectionId = snapshot.getValue(MultiSelectionId.class);


                if(multiSelectionId != null){
                    String reciveId = multiSelectionId.getId();
                    sendMessage(uid,reciveId,Msg);
                    dialog.dismiss();
                    SuccessDialog.show();


                }else{
                    Toast.makeText(BulkMessagingActivity.this, "Can not find the "+retirveName, Toast.LENGTH_SHORT).show();
                }


            }

            private void sendMessage(final String myid, final String friendid, final String message) {


                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();



                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("sender", myid);
                hashMap.put("reciever", friendid);
                hashMap.put("message", message);
                hashMap.put("isseen", false);

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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void searchUsers(String s) {

        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild(fiter_search)
                .startAt(s)
                .endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Users user = snapshot.getValue(Users.class);
                    String na = user.getUsername();

                    assert user != null;
                    assert fuser != null;
                    if (!user.getId().equals(fuser.getUid())){
                        arrayList.add(na);
                    }
                }

                adapter =new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice,arrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    }

/*
        String[] useridArray = {"6uv3eiU1XvViisEfTisgTJRlW562","LW7wQJEEuNfK2KYSlQPMn099l163","RefmqBqecvTajX7178IGWadcLax1","dIiFu81nFnec1uRVfgblzLyGkvG2"};
        // 6uv3eiU1XvViisEfTisgTJRlW562
        // LW7wQJEEuNfK2KYSlQPMn099l163
        // RefmqBqecvTajX7178IGWadcLax1
        // dIiFu81nFnec1uRVfgblzLyGkvG2

        Button multiSend = findViewById(R.id.multiSend);
        multiSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (String i: useridArray) {

                    Toast.makeText(BulkMessagingActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                    String fId = i;
                    String jsid = "6uv3eiU1XvViisEfTisgTJRlW562";
                    String msg = "hello this is multi message";
                    sendMessage(jsid,fId,msg);

                }

            }
        });
        Button splash = findViewById(R.id.splash);
        splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BulkMessagingActivity.this, SplashScreenActivity.class);
                startActivity(intent);
            }
        });


      */
