package com.example.pasca_primary.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {

    Context context;
    List<Users> userlist;
    boolean isChat;

    String friendid;
    int userTypeR,lastmessagecolor;
    String thelastmessage;
    FirebaseUser firebaseUser;

    public UserAdapter(Context context, List<Users> userlist, boolean isChat) {
        this.context = context;
        this.userlist = userlist;
        this.isChat = isChat;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layoutofusers, parent, false);
        return new MyHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Users user = userlist.get(position);
        userTypeR = user.getUsertype();

        friendid = user.getId();


        holder.username.setText(user.getUsername());
        holder.layout_class.setText(user.getStudent_class());

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

        if (isChat) {

            LastMessage(user.getId(), holder.last_msg);
            if(userTypeR == 2 || userTypeR == 3 || userTypeR == 1){
                holder.layout_class.setVisibility(View.INVISIBLE);
            }

        } else {

            holder.last_msg.setVisibility(View.INVISIBLE);
        }



    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView username, last_msg,layout_class;
        CircleImageView imageView, image_on, image_off;

        public MyHolder(@NonNull View itemView) {
            super(itemView);


            username = itemView.findViewById(R.id.username_userfrag);
            imageView = itemView.findViewById(R.id.image_user_userfrag);
            image_on = itemView.findViewById(R.id.image_online);
            image_off = itemView.findViewById(R.id.image_offline);
            last_msg = itemView.findViewById(R.id.lastMessage);
            layout_class = itemView.findViewById(R.id.layout_class);



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


    private void LastMessage(final String friendid, final TextView last_msg) {


        thelastmessage = "default";
        lastmessagecolor = 0;

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren()) {

                    Chats chats = ds.getValue(Chats.class);

                    if (firebaseUser!=null &&  chats!=null) {


                        if (chats.getSender().equals(friendid) && chats.getReciever().equals(firebaseUser.getUid()) ||
                                chats.getSender().equals(firebaseUser.getUid()) && chats.getReciever().equals(friendid)) {



                            if(chats.getReciever().equals(firebaseUser.getUid()) && !chats.isIsseen()){
                                lastmessagecolor++;
                                thelastmessage ="New("+lastmessagecolor+") "+chats.getMessage();
                                last_msg.setTextColor(Color.RED);


                            }else {
                                thelastmessage = chats.getMessage();
                            }
                        }







                    }

                }


                switch (thelastmessage) {


                    case "default":
                        last_msg.setText("No message");
                        break;

                    default:

                        last_msg.setText(thelastmessage);

                }


                thelastmessage = "default";


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });










    }
}
