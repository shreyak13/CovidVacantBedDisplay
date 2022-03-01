package com.example.mytext;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;



public class isolatiomadapter extends RecyclerView.Adapter<isolatiomadapter.ViewHolder>{
    Context context;
    List<dataholder> isolationList;
    public isolatiomadapter(Context context, List<dataholder> isolationList) {
        this.context = context;
        this.isolationList = isolationList;
    }



    @NonNull
    @Override
    public isolatiomadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design,parent,false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull isolatiomadapter.ViewHolder holder, int position) {
        dataholder dataholder1=isolationList.get(position);

        holder.name.setText(dataholder1.getIcname());
        holder.type.setText(dataholder1.getIctype());
        holder.details.setText(dataholder1.getIcNbed());
        String imageuri=null;
        imageuri=dataholder1.getIcimage();
        Picasso.get().load(imageuri).into(holder.imageView);





      /*  holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=holder.getAdapterPosition();
                Intent i = new Intent(context,view.class);
                i.putExtra("Name",dataholder1.getName());
                i.putExtra("type",dataholder1.getType());
                i.putExtra("bed",dataholder1.getBed());
                i.putExtra("address",dataholder1.getAddress());
                i.putExtra("contact",dataholder1.getContact());
                i.putExtra("image",dataholder1.getImage());
                i.putExtra("normalBed",dataholder1.getNbed());
                i.putExtra("oxygenBed",dataholder1.getObed());

                context.startActivity(i);


            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return isolationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,details,type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.tvname);
            type=itemView.findViewById(R.id.tvtype);
            details=itemView.findViewById(R.id.tvdetails);
        }
    }
}
