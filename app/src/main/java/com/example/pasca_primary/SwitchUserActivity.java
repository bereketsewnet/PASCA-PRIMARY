package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.pasca_primary.additional.CustomProgressDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SwitchUserActivity extends AppCompatActivity {

    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8,cardView9,cardView10;

    String uEmail_sotre1,uEmail_sotre2,uEmail_sotre3,uEmail_sotre4,uEmail_sotre5,
            uEmail_sotre6,uEmail_sotre7,uEmail_sotre8,uEmail_sotre9,uEmail_sotre10;

    String uPass_sotre1,uPass_sotre2,uPass_sotre3,uPass_sotre4,uPass_sotre5,
            uPass_sotre6,uPass_sotre7,uPass_sotre8,uPass_sotre9,uPass_sotre10;
    TextView switchName1,switchName2,switchName3,switchName4,switchName5,
            switchName6,switchName7,switchName8,switchName9,switchName10;


    Dialog ErrorRegisterDialog;
    FirebaseAuth mAuth;
    FirebaseUser user;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);


        toolbar = findViewById(R.id.toolbar_switch_user);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Change Student");

        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);
        cardView4 = findViewById(R.id.cardView4);
        cardView5 = findViewById(R.id.cardView5);
        cardView6 = findViewById(R.id.cardView6);
        cardView7 = findViewById(R.id.cardView7);
        cardView8 = findViewById(R.id.cardView8);
        cardView9 = findViewById(R.id.cardView9);
        cardView10 = findViewById(R.id.cardView10);

        switchName1 = findViewById(R.id.switchName1);
        switchName2 = findViewById(R.id.switchName2);
        switchName3 = findViewById(R.id.switchName3);
        switchName4 = findViewById(R.id.switchName4);
        switchName5 = findViewById(R.id.switchName5);
        switchName6 = findViewById(R.id.switchName6);
        switchName7 = findViewById(R.id.switchName7);
        switchName8 = findViewById(R.id.switchName8);
        switchName9 = findViewById(R.id.switchName9);
        switchName10 = findViewById(R.id.switchName10);

        // setting name to cards

        try{
            File myfile = new File("/sdcard/Android/MultiUserName_1");
            FileInputStream fIn = new FileInputStream(myfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null){
                aBuffer += aDataRow;
            }
            switchName1.setText(aBuffer);
            myReader.close();
            cardView1.setVisibility(View.VISIBLE);


        }catch (Exception e){


        }

        try{
            File myfile = new File("/sdcard/Android/MultiUserName_2");
            FileInputStream fIn = new FileInputStream(myfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null){
                aBuffer += aDataRow;
            }
            switchName2.setText(aBuffer);
            myReader.close();
            cardView2.setVisibility(View.VISIBLE);


        }catch (Exception e){


        }

        try{
            File myfile = new File("/sdcard/Android/MultiUserName_3");
            FileInputStream fIn = new FileInputStream(myfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null){
                aBuffer += aDataRow;
            }
            switchName3.setText(aBuffer);
            myReader.close();
            cardView3.setVisibility(View.VISIBLE);


        }catch (Exception e){

        }

        try{
            File myfile = new File("/sdcard/Android/MultiUserName_4");
            FileInputStream fIn = new FileInputStream(myfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null){
                aBuffer += aDataRow;
            }
            switchName4.setText(aBuffer);
            myReader.close();
            cardView4.setVisibility(View.VISIBLE);


        }catch (Exception e){


        }

        try{
            File myfile = new File("/sdcard/Android/MultiUserName_5");
            FileInputStream fIn = new FileInputStream(myfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null){
                aBuffer += aDataRow;
            }
            switchName5.setText(aBuffer);
            myReader.close();
            cardView5.setVisibility(View.VISIBLE);


        }catch (Exception e){


        }

        try{
            File myfile = new File("/sdcard/Android/MultiUserName_6");
            FileInputStream fIn = new FileInputStream(myfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null){
                aBuffer += aDataRow;
            }
            switchName6.setText(aBuffer);
            myReader.close();
            cardView6.setVisibility(View.VISIBLE);


        }catch (Exception e){


        }

        try{
            File myfile = new File("/sdcard/Android/MultiUserName_7");
            FileInputStream fIn = new FileInputStream(myfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null){
                aBuffer += aDataRow;
            }
            switchName7.setText(aBuffer);
            myReader.close();
            cardView7.setVisibility(View.VISIBLE);


        }catch (Exception e){


        }

        try{
            File myfile = new File("/sdcard/Android/MultiUserName_8");
            FileInputStream fIn = new FileInputStream(myfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null){
                aBuffer += aDataRow;
            }
            switchName8.setText(aBuffer);
            myReader.close();
            cardView8.setVisibility(View.VISIBLE);


        }catch (Exception e){


        }

        try{
            File myfile = new File("/sdcard/Android/MultiUserName_9");
            FileInputStream fIn = new FileInputStream(myfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null){
                aBuffer += aDataRow;
            }
            switchName9.setText(aBuffer);
            myReader.close();
            cardView9.setVisibility(View.VISIBLE);


        }catch (Exception e){


        }

        try{
            File myfile = new File("/sdcard/Android/MultiUserName_10");
            FileInputStream fIn = new FileInputStream(myfile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null){
                aBuffer += aDataRow;
            }
            switchName10.setText(aBuffer);
            myReader.close();
            cardView10.setVisibility(View.VISIBLE);


        }catch (Exception e){


        }

        //

        mAuth = FirebaseAuth.getInstance();

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    File myfile = new File("/sdcard/Android/MultiUserId_1.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uEmail_sotre1 = aBuffer;
                    myReader.close();


                }catch (Exception e){

                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                try{
                    File myfile = new File("/sdcard/Android/MultiUserPassword_1.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uPass_sotre1 = aBuffer;
                    myReader.close();
                    LoginById(uEmail_sotre1,uPass_sotre1);


                }catch (Exception e){

                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }
        });


        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try{
                    File myfile = new File("/sdcard/Android/MultiUserId_2.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uEmail_sotre2 = aBuffer;
                    myReader.close();


                }catch (Exception e){

                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                try{
                    File myfile = new File("/sdcard/Android/MultiUserPassword_2.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uPass_sotre2 = aBuffer;
                    myReader.close();
                    LoginById(uEmail_sotre2,uPass_sotre2);


                }catch (Exception e){

                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });


        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try{
                    File myfile = new File("/sdcard/Android/MultiUserId_3.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uEmail_sotre3 = aBuffer;
                    myReader.close();


                }catch (Exception e){

                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                try{
                    File myfile = new File("/sdcard/Android/MultiUserPassword_3.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uPass_sotre3 = aBuffer;
                    myReader.close();
                    LoginById(uEmail_sotre3,uPass_sotre3);


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    File myfile = new File("/sdcard/Android/MultiUserId_4.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uEmail_sotre4 = aBuffer;
                    myReader.close();


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                try{
                    File myfile = new File("/sdcard/Android/MultiUserPassword_4.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uPass_sotre4 = aBuffer;
                    myReader.close();
                    LoginById(uEmail_sotre4,uPass_sotre4);


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    File myfile = new File("/sdcard/Android/MultiUserId_5.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uEmail_sotre5 = aBuffer;
                    myReader.close();


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }


                try{
                    File myfile = new File("/sdcard/Android/MultiUserPassword_5.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uPass_sotre5 = aBuffer;
                    myReader.close();
                    LoginById(uEmail_sotre5, uPass_sotre5);


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();


                }



            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    File myfile = new File("/sdcard/Android/MultiUserId_6.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uEmail_sotre6 = aBuffer;
                    myReader.close();


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }


                try{
                    File myfile = new File("/sdcard/Android/MultiUserPassword_6.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uPass_sotre6 = aBuffer;
                    myReader.close();
                    LoginById(uEmail_sotre6,uPass_sotre6);


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    File myfile = new File("/sdcard/Android/MultiUserId_7.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uEmail_sotre7 = aBuffer;
                    myReader.close();


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                try{
                    File myfile = new File("/sdcard/Android/MultiUserPassword_7.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uPass_sotre7 = aBuffer;
                    myReader.close();
                    LoginById(uEmail_sotre7,uPass_sotre7);


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int error8 = 0;

                try{
                    File myfile = new File("/sdcard/Android/MultiUserId_8.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uEmail_sotre8 = aBuffer;
                    myReader.close();


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                try{
                    File myfile = new File("/sdcard/Android/MultiUserPassword_8.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uPass_sotre8 = aBuffer;
                    myReader.close();
                    LoginById(uEmail_sotre8,uPass_sotre8);


                }catch (Exception e){

                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    File myfile = new File("/sdcard/Android/MultiUserId_9.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uEmail_sotre9 = aBuffer;
                    myReader.close();


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                try{
                    File myfile = new File("/sdcard/Android/MultiUserPassword_9.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uPass_sotre9 = aBuffer;
                    myReader.close();
                    LoginById(uEmail_sotre9,uPass_sotre9);


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });

        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    File myfile = new File("/sdcard/Android/MultiUserId_10.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uEmail_sotre10 = aBuffer;
                    myReader.close();


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }


                try{
                    File myfile = new File("/sdcard/Android/MultiUserPassword_10.txt");
                    FileInputStream fIn = new FileInputStream(myfile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null){
                        aBuffer += aDataRow;
                    }
                    uPass_sotre10 = aBuffer;
                    myReader.close();
                    LoginById(uEmail_sotre10,uPass_sotre10);


                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });




    }

    private void LoginById(String email, String password) {

        final CustomProgressDialog dialog = new CustomProgressDialog(SwitchUserActivity.this);
        dialog.show();

        //Create the Wrong Dialog here
        ErrorRegisterDialog = new Dialog(this);
        ErrorRegisterDialog.setContentView(R.layout.custom_register_error_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ErrorRegisterDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        ErrorRegisterDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ErrorRegisterDialog.setCancelable(false); //Optional
        ErrorRegisterDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button ErrorOkay = ErrorRegisterDialog.findViewById(R.id.btn_okay_error_register);

        ErrorOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SwitchUserActivity.this, "Done", Toast.LENGTH_SHORT).show();
                ErrorRegisterDialog.dismiss();
            }
        });

        // end of Wrong dialog




        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {



                if (task.isSuccessful()) {

                    //start of filter Student and  Teacher
                    String uid = task.getResult().getUser().getUid();

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child("Users").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int usertype = snapshot.getValue(Integer.class);
                            if(usertype==0){
                                Intent intent = new Intent(SwitchUserActivity.this,StudentsHomeActivity.class);
                                intent.putExtra("MyId", uid);
                                startActivity(intent);
                                dialog.dismiss();
                                finish();



                            }


                            if(usertype==1 || usertype==2){
                                Intent intent = new Intent(SwitchUserActivity.this, TeachersHomeActivity.class);
                                intent.putExtra("MyId", uid);
                                startActivity(intent);
                                dialog.dismiss();
                                finish();
                            }



                            if(usertype==3){
                                Intent intent = new Intent(SwitchUserActivity.this,AdminHomeActivity.class);
                                intent.putExtra("MyId", uid);
                                startActivity(intent);
                                dialog.dismiss();
                                finish();
                            }

                            if(usertype==4){
                                Toast.makeText(SwitchUserActivity.this, "Not Found!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    //end of filter

                }else{
                    ErrorRegisterDialog.show();
                    dialog.dismiss();
                }

            }
        });
    }


}