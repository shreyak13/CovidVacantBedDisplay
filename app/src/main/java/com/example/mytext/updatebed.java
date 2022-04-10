package com.example.mytext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class updatebed extends AppCompatActivity {
    EditText upNormalBed,upOxygenBed,upIcuBed;
    Button updateBed;
    FirebaseStorage storage;
    FirebaseDatabase fbase;
    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatebed);
        upNormalBed = findViewById(R.id.upNoBed);
        upOxygenBed = findViewById(R.id.upOxBed);
        upIcuBed = findViewById(R.id.upIcuBed);
        updateBed = findViewById(R.id.updateBedDetails);

        fbase = FirebaseDatabase.getInstance();
        dref = fbase.getReference().child("Update Bed");

        updateBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String upnormalbed=upNormalBed.getText().toString();
                String upoxygenbed=upOxygenBed.getText().toString();
                String upicubed=upIcuBed.getText().toString();

                bedupholder bedholder=new bedupholder(upnormalbed,upoxygenbed,upicubed);

                dref.push().setValue(bedholder);


                // DatabaseReference newpost=dref.push();

                //newpost.child("upnormalbed").setValue(upnormalbed);
                //newpost.child("upoxygenbed").setValue(upoxygenbed);
                //newpost.child("upicubed").setValue(upicubed);

                upNormalBed.setText("");
                upOxygenBed.setText("");
                upIcuBed.setText("");


            }
        });


    }

    }




