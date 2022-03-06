package com.example.mytext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class isolationcentre extends AppCompatActivity {
    FirebaseDatabase fbase;
    FirebaseStorage storage;

    DatabaseReference dref;
    RecyclerView icrecyclerView;
    isolatiomadapter isoadapter;
    List<dataholder> holddata2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isolationcentre);
        fbase=FirebaseDatabase.getInstance();
        dref= fbase.getReference("isolation centre");
        storage=FirebaseStorage.getInstance();
        icrecyclerView=findViewById(R.id.recy);


        icrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        holddata2=new ArrayList<>();
        isoadapter =new isolatiomadapter(isolationcentre.this,holddata2);
        icrecyclerView.setAdapter(isoadapter);




       dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               //dataholder iholddata=snapshot.getValue(dataholder.class);
               // holddata2.add(iholddata);

               for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    dataholder iholddata=dataSnapshot.getValue(dataholder.class);

                    holddata2.add(iholddata);
                }


                isoadapter.notifyDataSetChanged();

            }




           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }




    });}
}