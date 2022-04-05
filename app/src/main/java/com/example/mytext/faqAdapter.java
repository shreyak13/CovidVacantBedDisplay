package com.example.mytext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class faqAdapter extends RecyclerView.Adapter<faqAdapter.ViewHolder> {
    LayoutInflater inflater;

    ArrayList<String> question;
    ArrayList<String> answer;
    Context context;

    public faqAdapter(ArrayList<String> question, ArrayList<String> answer,Context context) {
        this.question = question;
        this.answer = answer;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.faqitem,parent,false);
         ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull faqAdapter.ViewHolder holder,final int position) {


     holder.question.setText(question.get(position));
        holder.answer.setText(answer.get(position));


    }

    @Override
    public int getItemCount() {
        return question.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView question,answer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.faqQuestion);
            answer=itemView.findViewById(R.id.faqAnswer);
        }
    }
}
