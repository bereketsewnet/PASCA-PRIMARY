package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pasca_primary.Adapters.MyAdapter;
import com.example.pasca_primary.Model.DataClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeesInformationActivity extends AppCompatActivity {


    FloatingActionButton fab_fee;
    private RecyclerView recyclerView_fee;
    private ArrayList<DataClass> dataList;
    private MyAdapter adapter;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Fees");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_information);

        fab_fee = findViewById(R.id.fab_fee);
        recyclerView_fee = findViewById(R.id.recyclerView_fee);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_fee.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView_fee.setHasFixedSize(true);
       // recyclerView_fee.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,true));
        dataList = new ArrayList<>();
        adapter = new MyAdapter(this, dataList);
        recyclerView_fee.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataClass dataClass = dataSnapshot.getValue(DataClass.class);
                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        fab_fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeesInformationActivity.this, PasswordSevenActivity.class);
                startActivity(intent);
                finish();
            }
        });


// bottom navigation start
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_feesinformation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_schoollaw:
                    startActivity(new Intent(getApplicationContext(), SchoolLawActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_schoolcalendar:
                    startActivity(new Intent(getApplicationContext(), SchooCalendarActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_maps:
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_feesinformation:
                    return true;
                case R.id.bottom_aboutpasca:
                    startActivity(new Intent(getApplicationContext(), AboutPascaActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
        // bottom navigation end

    }
}