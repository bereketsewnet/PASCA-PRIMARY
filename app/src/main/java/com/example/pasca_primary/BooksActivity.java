package com.example.pasca_primary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pasca_primary.Model.pdfClass;
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

public class BooksActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<pdfClass> uploads_pdf;
    FirebaseAuth mAuth;
    FloatingActionButton books_upload_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        books_upload_t = findViewById(R.id.books_upload_t);
        uploads_pdf = new ArrayList<>();
        listView = findViewById(R.id.list_view);

        viewAllFiles();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pdfClass pdfupload = uploads_pdf.get(position);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(pdfupload.getUrl()));
                startActivity(intent);
            }
        });

        // checker if user teacher or homeroom
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child("Users").child(user.getUid()).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int usertype = snapshot.getValue(Integer.class);

                if(usertype==2){
                    books_upload_t.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // checker end


        books_upload_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BooksActivity.this,UploadPdfActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }

    private void viewAllFiles() {

        databaseReference = FirebaseDatabase.getInstance().getReference("UploadsPdf");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot postsnapshot : snapshot.getChildren()){
                    pdfClass pdfClass = postsnapshot.getValue(com.example.pasca_primary.Model.pdfClass.class);

                    uploads_pdf.add(pdfClass);
                }
                String[] Uploads_pdf = new String[uploads_pdf.size()];
                for(int i =0;i<Uploads_pdf.length;i++){
                    Uploads_pdf[i]= uploads_pdf.get(i).getName_pdf();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,Uploads_pdf){

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position,convertView,parent);
                        TextView text = view.findViewById(android.R.id.text1);
                        text.setTextColor(Color.BLACK);
                        text.setTextSize(22);
                        return view;

                    }
                };
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}