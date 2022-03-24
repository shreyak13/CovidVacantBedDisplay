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


    

    public class rowadapter extends RecyclerView.Adapter<rowadapter.ViewHolder> {
        Context context;
        List<holddata> hospitalList;


        public rowadapter(Context context, List<holddata> hospitalList) {
            this.context = context;
            this.hospitalList = hospitalList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design,parent,false);

            return new ViewHolder(v);

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          holddata dataholder1=hospitalList.get(position);

            holder.name.setText(dataholder1.getName());
            holder.type.setText(dataholder1.getType());
            holder.details.setText(dataholder1.getBed());
            String imageuri=null;
            imageuri=dataholder1.getImage();
            Picasso.get().load(imageuri).into(holder.imageView);





        holder.imageView.setOnClickListener(new View.OnClickListener() {
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
                 i.putExtra("normalBed",dataholder1.getNormalBed());
                 i.putExtra("oxygenBed",dataholder1.getOxygenBed());
                 i.putExtra("ICUBed",dataholder1.getICUBed());
                 context.startActivity(i);


             }
         });


        }



        @Override
        public int getItemCount() {
            return hospitalList.size();
        }

      public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView name,details,type;
            public ViewHolder(View v) {
                super(v);
                imageView=v.findViewById(R.id.imageView);
                name=v.findViewById(R.id.tvname);
                type=v.findViewById(R.id.tvtype);
                details=v.findViewById(R.id.tvdetails);
            }
        }



    }
