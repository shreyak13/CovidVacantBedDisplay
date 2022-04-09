package com.example.mytext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ambulance extends AppCompatActivity {
    RecyclerView recyclerView;
    ambAdapter ambadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        recyclerView=findViewById(R.id.aRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ambholder> options =
                new FirebaseRecyclerOptions.Builder<ambholder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Ambulance"), ambholder.class)
                        .build();
         ambadapter=new ambAdapter(options);
         recyclerView.setAdapter(ambadapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        ambadapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        ambadapter.stopListening();
    }
}