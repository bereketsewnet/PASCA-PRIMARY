package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pasca_primary.Model.DisplayRank;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RankActivity extends AppCompatActivity {

    TextView cont_r1,cont_r2,cont_r3,cont_r4,cont_r5,cont_r6,cont_r7,cont_r8,cont_r9,cont_r10,cont_r11,cont_r12;
    TextView value_r1,value_r2,value_r3,value_r4,value_r5,value_r6,value_r7,value_r8,value_r9,value_r10,value_r11,value_r12;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        //cont_r1
        cont_r1 = findViewById(R.id.cont_r1);
        cont_r2 = findViewById(R.id.cont_r2);
        cont_r3 = findViewById(R.id.cont_r3);
        cont_r4 = findViewById(R.id.cont_r4);
        cont_r5 = findViewById(R.id.cont_r5);
        cont_r6 = findViewById(R.id.cont_r6);
        cont_r7 = findViewById(R.id.cont_r7);
        cont_r8 = findViewById(R.id.cont_r8);
        cont_r9 = findViewById(R.id.cont_r9);
        cont_r10 = findViewById(R.id.cont_r10);
        cont_r11 = findViewById(R.id.cont_r11);
        cont_r12 = findViewById(R.id.cont_r12);
        //value_r1
        value_r1 = findViewById(R.id.value_r1);
        value_r2 = findViewById(R.id.value_r2);
        value_r3 = findViewById(R.id.value_r3);
        value_r4 = findViewById(R.id.value_r4);
        value_r5 = findViewById(R.id.value_r5);
        value_r6 = findViewById(R.id.value_r6);
        value_r7 = findViewById(R.id.value_r7);
        value_r8 = findViewById(R.id.value_r8);
        value_r9 = findViewById(R.id.value_r9);
        value_r10 = findViewById(R.id.value_r10);
        value_r11 = findViewById(R.id.value_r11);
        value_r12 = findViewById(R.id.value_r12);
        // others
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("RankReport").child(user.getUid());
        if(reference != null){

        }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DisplayRank displayRank = snapshot.getValue(DisplayRank.class);
                String name ;

                if(displayRank != null){

                    if(displayRank.getRcont1().equals("rcont1")){
                        cont_r1.setText(" ");
                    }else{
                        cont_r1.setText(displayRank.getRcont1());
                        value_r1.setText(displayRank.getRvalue1());
                    }

                    if(displayRank.getRcont2().equals("rcont2")){
                        cont_r2.setText(" ");
                    }else{
                        cont_r2.setText(displayRank.getRcont2());
                        value_r2.setText(displayRank.getRvalue2());
                    }

                    if(displayRank.getRcont3().equals("rcont3")){
                        cont_r3.setText(" ");
                    }else{
                        cont_r3.setText(displayRank.getRcont3());
                        value_r3.setText(displayRank.getRvalue3());

                    }

                    if(displayRank.getRcont4().equals("rcont4")){
                        cont_r4.setText(" ");
                    }else{
                        cont_r4.setText(displayRank.getRcont4());
                        value_r4.setText(displayRank.getRvalue4());

                    }

                    if(displayRank.getRcont5().equals("rcont5")){
                        cont_r5.setText(" ");
                    }else{
                        cont_r5.setText(displayRank.getRcont5());
                        value_r5.setText(displayRank.getRvalue5());

                    }

                    if(displayRank.getRcont6().equals("rcont6")){
                        cont_r6.setText(" ");
                    }else{
                        cont_r6.setText(displayRank.getRcont6());
                        value_r6.setText(displayRank.getRvalue6());
                    }


                    if(displayRank.getRcont7().equals("rcont7")){
                        cont_r7.setText(" ");
                    }else{
                        cont_r7.setText(displayRank.getRcont7());
                        value_r7.setText(displayRank.getRvalue7());
                    }


                    if(displayRank.getRcont8().equals("rcont8")){
                        cont_r8.setText(" ");
                    }else{
                        cont_r8.setText(displayRank.getRcont8());
                        value_r8.setText(displayRank.getRvalue8());
                    }

                    if(displayRank.getRcont9().equals("rcont9")){
                        cont_r9.setText(" ");
                    }else{
                        cont_r8.setText(displayRank.getRcont8());
                        value_r8.setText(displayRank.getRvalue8());
                    }

                    if(displayRank.getRcont10().equals("rcont10")){
                        cont_r10.setText(" ");
                    }else{
                        cont_r10.setText(displayRank.getRcont10());
                        value_r10.setText(displayRank.getRvalue10());

                    }

                    if(displayRank.getRcont11().equals("rcont11")){
                        cont_r11.setText(" ");
                    }else{
                        cont_r11.setText(displayRank.getRcont11());
                        value_r11.setText(displayRank.getRvalue11());
                    }

                    if(displayRank.getRcont12().equals("rcont12")){
                        cont_r12.setText(" ");
                    }else{
                        cont_r12.setText(displayRank.getRcont12());
                        value_r12.setText(displayRank.getRvalue12());

                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}