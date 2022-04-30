package com.example.mytext;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class hospital extends AppCompatActivity {
    TextView name, type, address, capasity, contact;
    FirebaseDatabase fbase;
    FirebaseStorage storage;

    DatabaseReference dref;
    RecyclerView recyclerView;
    rowadapter rowadapter;
    ArrayList<holddata> holddata1;
    DatabaseReference dataref;
    ArrayList<bedupholder> bedupholders;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);



        fbase=FirebaseDatabase.getInstance();
        dref= fbase.getReference().child("Hospitals");
        storage=FirebaseStorage.getInstance();
        recyclerView=findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        holddata1=new ArrayList<holddata>();
        rowadapter =new rowadapter(hospital.this,holddata1);
        recyclerView.setAdapter(rowadapter);
        dataref=fbase.getReference().child(" ");
        bedupholders=new ArrayList<bedupholder>();




        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                holddata holddataa=snapshot.getValue(holddata.class);
                holddata1.add(holddataa);
                rowadapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dataref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                bedupholder bedupholderr=snapshot.getValue(bedupholder.class);
                bedupholders.add(bedupholderr);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}

