package com.example.pasca_primary.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pasca_primary.MessageActivity;
import com.example.pasca_primary.Model.Chats;
import com.example.pasca_primary.Model.Users;
import com.example.pasca_primary.R;
import com.example.pasca_primary.RankFormActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserRegisterAdapter extends RecyclerView.Adapter<UserRegisterAdapter.MyHolder> {

    Context context;
    List<Users> userlist;
    boolean isChat;

    String friendid;
    String getclasss;
    int gettypee;
    FirebaseUser firebaseUser;

    public UserRegisterAdapter(Context context, List<Users> userlist, boolean isChat) {
        this.context = context;
        this.userlist = userlist;
        this.isChat = isChat;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layoutofusersregister, parent, false);
        return new MyHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Users user = userlist.get(position);

        friendid = user.getId();
        getclasss = "Class: " + user.getStudent_class();
        gettypee = user.getUsertype();
        if(gettypee == 0){
            holder.user_list_type.setText("Student |");
        }

        if(gettypee == 1){
            holder.user_list_type.setText("Teacher |");
        }


        if(gettypee == 2){
            holder.user_list_type.setText("Home Room |");
        }


        if(gettypee == 3){
            holder.user_list_type.setText("Admin |");
        }

        if(gettypee == 4){
            holder.user_list_type.setText("None |");
        }



        holder.username.setText(user.getUsername());
        holder.user_list_class.setText(getclasss);


        if (user.getImageURL().equals("default")) {

            holder.imageView.setImageResource(R.drawable.user);


        } else {

            Glide.with(context).load(user.getImageURL()).into(holder.imageView);
        }


        if (isChat) {

            if (user.getStatus().equals("online")) {

                holder.image_on.setVisibility(View.VISIBLE);
                holder.image_off.setVisibility(View.GONE);


            } else {

                holder.image_on.setVisibility(View.GONE);
                holder.image_off.setVisibility(View.VISIBLE);


            }


        } else {

            holder.image_on.setVisibility(View.GONE);
            holder.image_off.setVisibility(View.GONE);


        }



    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView username,user_list_class,user_list_type;
        CircleImageView imageView, image_on, image_off;

        public MyHolder(@NonNull View itemView) {
            super(itemView);


            username = itemView.findViewById(R.id.username_userfrag);
            user_list_class = itemView.findViewById(R.id.user_list_class);
            user_list_type = itemView.findViewById(R.id.user_list_type);
            imageView = itemView.findViewById(R.id.image_user_userfrag);
            image_on = itemView.findViewById(R.id.image_online);
            image_off = itemView.findViewById(R.id.image_offline);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Users users = userlist.get(getAdapterPosition());

            friendid = users.getId();

            Intent intent = new Intent(context, MessageActivity.class);
            intent.putExtra("friendid", friendid);
            context.startActivity(intent);



        }
    }


}
