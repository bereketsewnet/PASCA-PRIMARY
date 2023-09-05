package com.example.pasca_primary.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pasca_primary.IdProfileActivity;
import com.example.pasca_primary.LoginActivity;
import com.example.pasca_primary.Model.ChangeProfilePass;
import com.example.pasca_primary.Model.Users;
import com.example.pasca_primary.R;

import com.example.pasca_primary.RegisterUserListActivity;
import com.example.pasca_primary.additional.CustomProgressDialog;
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


public class ProfilePasswordFragment extends Fragment {
    TextView profile_password;
    Button profile_password_btn;
    String profile_password_store,recive_pass ;;
    DatabaseReference reference;
    DatabaseReference databaseReference;
    String uid,myEmail,myPassword,myName;
    FirebaseUser firebaseUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_password, container, false);

        profile_password_btn = view.findViewById(R.id.profile_password_btn);
        profile_password = view.findViewById(R.id.profile_password);
        final CustomProgressDialog dialog = new CustomProgressDialog(getContext());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = firebaseUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        if(databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Users users = snapshot.getValue(Users.class);
                    myEmail = users.getEmail();
                    myPassword = users.getPassword();
                    myName = users.getUsername();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        reference = FirebaseDatabase.getInstance().getReference("ProfileChangePassword");


        profile_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_password_store = profile_password.getText().toString();

                if(TextUtils.isEmpty(profile_password_store)){
                    profile_password.setError("Please Insert Password");
                }else{
                    // start
                    dialog.show();
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            ChangeProfilePass changeProfilePass = snapshot.getValue(ChangeProfilePass.class);


                            if(changeProfilePass != null){
                                recive_pass = changeProfilePass.getPassword();
                                if(recive_pass.equals(profile_password_store)){
                                    Intent intent = new Intent(getContext(), IdProfileActivity.class);
                                    startActivity(intent);
                                    profile_password.setText("");
                                    dialog.dismiss();
                                }else{
                                    profile_password.setError("Please Try Again!");
                                    dialog.dismiss();
                                }



                            }else{
                                Toast.makeText(getActivity(), "First set The Password in Database", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    // end
                }



            }
        });


        return view;
    }

    private void showDeleteDialog() {


        final String[] listItems = {"Yes", "NO"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        mBuilder.setTitle("you want to delete this?");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0){
                    DeleteUser();
                }else if(which == 1){
                    dialog.dismiss();
                }
                dialog.dismiss();

            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    private void DeleteUser() {

        DeleteFromAuth(myEmail,myPassword);

        // removing user id and other info from user getId child
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        Task<Void> mtask = reference.removeValue();
        mtask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(), "User Deleted From Database", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), "Error deleting User", Toast.LENGTH_SHORT).show();
            }
        });
        // end removing user id and other info from user getId child

        // removing user Multisender Id  by using name child
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("MultiSenderId").child(myName);
        Task<Void> mtask1 = reference1.removeValue();
        mtask1.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(), "User Deleted From App", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), "Error deleting User From App", Toast.LENGTH_SHORT).show();
            }
        });
        // end removing user Multisender Id  by using name child
    }

    private void DeleteFromAuth(String email, String password) {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);
        if(user != null){
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Log.d("TAG","User account deleted.");
                                Intent intent = new Intent(getContext(), LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(getContext(), "Deleted User Successfully,", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(getContext(), "Error User Deleted,", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error is: "+e, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }
    }

}