package com.example.mytext;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
    holder.btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(holder.linLayout.getVisibility() == View.GONE){
                TransitionManager.beginDelayedTransition(holder.cardView,new AutoTransition());
                holder.linLayout.setVisibility(View.VISIBLE);


            }
            else{
                TransitionManager.beginDelayedTransition(holder.cardView,new AutoTransition());
                holder.linLayout.setVisibility(View.GONE);

            }
        }
    });



    }

    @Override
    public int getItemCount() {
        return question.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView question,answer;
        Button btn;
        LinearLayout linLayout;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.faqQuestion);
            answer=itemView.findViewById(R.id.faqAnswer);
            btn=itemView.findViewById(R.id.expandAns);
            linLayout=itemView.findViewById(R.id.ansLayout);
            cardView=itemView.findViewById(R.id.card);
        }
    }
}
