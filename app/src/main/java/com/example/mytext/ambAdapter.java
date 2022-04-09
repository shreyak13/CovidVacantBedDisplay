package com.example.mytext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ambAdapter extends FirebaseRecyclerAdapter<ambholder,ambAdapter.myviewholder> {
    public ambAdapter(@NonNull FirebaseRecyclerOptions<ambholder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ambholder model) {

        holder.name.setText(model.getName());
        holder.type.setText(model.getType());
        holder.contact.setText(model.getContact());
        holder.address.setText(model.getAddress());
        String imageuri=null;
        imageuri= model.getImage();
        Picasso.get().load(imageuri).into(holder.img);


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleambulance,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
    ImageView img;
    TextView name,contact,type,address;
    public myviewholder(@NonNull View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.aImage);
        name=itemView.findViewById(R.id.aName);
        contact=itemView.findViewById(R.id.aContact);
        type=itemView.findViewById(R.id.aType);
        address=itemView.findViewById(R.id.aAddress);
    }
}
}
