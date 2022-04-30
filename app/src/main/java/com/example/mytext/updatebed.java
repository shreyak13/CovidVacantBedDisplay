package com.example.mytext;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

public class updatebed extends AppCompatActivity {
    EditText upNormalBed;
    EditText upOxygenBed;
    EditText upIcuBed;
    TextView namee,emaile;
    Button addBed,updateBed;
    FirebaseStorage storage;
    FirebaseAuth mAuth;
    FirebaseDatabase fbase;
    DatabaseReference dref,dbref;
    FirebaseFirestore fstore;
   FirebaseUser currentUser;
    String currentUserID;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatebed);
        upNormalBed = findViewById(R.id.upNoBed);
        upOxygenBed = findViewById(R.id.upOxBed);
        upIcuBed = findViewById(R.id.upIcuBed);
        addBed = findViewById(R.id.addBedDetails);
        updateBed = findViewById(R.id.updateBedDetails);
        namee=findViewById(R.id.centerName);



        fbase = FirebaseDatabase.getInstance();
        dref = fbase.getReference().child("Update Bed");
        fstore= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        //String email=getIntent().getStringExtra("email");
         // namee.setText(email);
       Bundle extras = getIntent().getExtras();
      String stre = extras.getString("email");


        addBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String upnormalbed=upNormalBed.getText().toString();
                String upoxygenbed=upOxygenBed.getText().toString();
                String upicubed=upIcuBed.getText().toString();

                bedupholder bedholder=new bedupholder(stre,upnormalbed,upoxygenbed,upicubed);

                dref.push().setValue(bedholder);


                upNormalBed.setText("");
                upOxygenBed.setText("");
                upIcuBed.setText("");
                addBed.setVisibility(View.GONE);

            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Update Bed");

        reference.orderByChild("stre").equalTo(stre).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String upnbed=datas.child("upnormalbed").getValue().toString();
                    String upobed=datas.child("upoxygenbed").getValue().toString();
                    String upibed=datas.child("upicubed").getValue().toString();

                    upNormalBed.setText(upnbed);
                    upOxygenBed.setText(upobed);
                    upIcuBed.setText(upibed);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }

    }




