package com.example.pasca_primary.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pasca_primary.R;
import com.example.pasca_primary.profileChangeActivity;


public class ProfilePasswordFragment extends Fragment {
    TextView profile_password;
    Button profile_password_btn;
    String profile_password_store;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_password, container, false);

        profile_password_btn = view.findViewById(R.id.profile_password_btn);
        profile_password = view.findViewById(R.id.profile_password);


        profile_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profile_password_store = profile_password.getText().toString();

                if(profile_password_store.isEmpty()){
                    Toast.makeText(getContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                }else if(profile_password_store.equals("1234")){

                  //  Fragment profragment = new ProfileFragment();
                 //   FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                  //  fm.replace(R.id.containerfragmet,profragment);

                    Intent intent = new Intent(getActivity(), profileChangeActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getContext(), "Password is incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

}