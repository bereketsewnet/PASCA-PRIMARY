package com.example.pasca_primary.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pasca_primary.IdProfileActivity;
import com.example.pasca_primary.Model.ChangeProfilePass;
import com.example.pasca_primary.R;

import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.firebase.auth.FirebaseAuth;
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
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_password, container, false);

        profile_password_btn = view.findViewById(R.id.profile_password_btn);
        profile_password = view.findViewById(R.id.profile_password);
        final CustomProgressDialog dialog = new CustomProgressDialog(getContext());

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

}