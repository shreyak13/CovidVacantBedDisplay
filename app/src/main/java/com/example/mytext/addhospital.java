package com.example.mytext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class addhospital extends AppCompatActivity {
    EditText namea,typea,capasitya,adressa,contacta,normalbeda,oxygenbeda,icubeda;
    Button add;
    FirebaseStorage storage;
    FirebaseDatabase fbase;
    DatabaseReference dref;
    ImageButton himageButton;
    private static final int gallarycode=1;
    Uri imageuri=null;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhospital);
        himageButton=findViewById(R.id.imageButton);
        namea=findViewById(R.id.name);
        typea=findViewById(R.id.type);
        capasitya=findViewById(R.id.capsity);
        adressa=findViewById(R.id.address);
        contacta=findViewById(R.id.contact);
        add=findViewById(R.id.add);
        normalbeda=findViewById(R.id.normalbed);
        icubeda=findViewById(R.id.icubed);
        oxygenbeda=findViewById(R.id.oxygenbed);

        fbase=FirebaseDatabase.getInstance();
        dref= fbase.getReference().child("Hospitals");
        storage=FirebaseStorage.getInstance();

        himageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
             startActivityForResult(intent,gallarycode);
            }
        });
        SharedPreferences shared=getSharedPreferences("myKey",MODE_PRIVATE);
        String stre=shared.getString("email","");

      dref.orderByChild("stre").equalTo(stre).addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              for(DataSnapshot datas: snapshot.getChildren()) {
                  String name = datas.child("name").getValue().toString();
                  String type=datas.child("type").getValue().toString();
                  String capasity=datas.child("bed").getValue().toString();
                  String contact=datas.child("contact").getValue().toString();
                  String address=datas.child("address").getValue().toString();
                  String normalBed=datas.child("normalBed").getValue().toString();
                  String oxygenBed=datas.child("oxygenBed").getValue().toString();
                  String icuBed=datas.child("ICUBed").getValue().toString();
                  namea.setText(name);
                  typea.setText(type);
                  capasitya.setText(capasity);
                  adressa.setText(address);
                  contacta.setText(contact);
                  normalbeda.setText(normalBed);
                  oxygenbeda.setText(oxygenBed);
                  icubeda.setText(icuBed);

                  if(namea!=null){
                      add.setVisibility(View.GONE);
                  }
              }


          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==gallarycode&& resultCode==RESULT_OK){
            imageuri=data.getData();
            himageButton.setImageURI(imageuri);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=namea.getText().toString();
                String type=typea.getText().toString();
                String bed=capasitya.getText().toString();
                String address=adressa.getText().toString();
                String cont=contacta.getText().toString();
                String Nbed=normalbeda.getText().toString();
                String Obed=oxygenbeda.getText().toString();
                String Ibed=icubeda.getText().toString();
                SharedPreferences shared=getSharedPreferences("myKey",MODE_PRIVATE);
                String stre=shared.getString("email","");

                if(!(name.isEmpty()&&type.isEmpty()&&bed.isEmpty()&&address.isEmpty()&&cont.isEmpty() && imageuri!=null)){


                    StorageReference filepath=storage.getReference().child("imagepost").child(imageuri.getLastPathSegment());
                    filepath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadurl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t=task.getResult().toString();
                                    DatabaseReference newpost=dref.push();

                                    newpost.child("name").setValue(name);
                                    newpost.child("type").setValue(type);
                                    newpost.child("bed").setValue(bed);
                                    newpost.child("address").setValue(address);
                                    newpost.child("contact").setValue(cont);
                                    newpost.child("normalBed").setValue(Nbed);
                                    newpost.child("oxygenBed").setValue(Obed);
                                    newpost.child("ICUBed").setValue(Ibed);
                                    newpost.child("image").setValue(task.getResult().toString());
                                   String key =  dref.push().getKey();

                                    newpost.child("stre").setValue(stre);
                                newpost.child("key").setValue(key);


                                }
                            });
                        }
                    });
                }
                Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                namea.setText("");
                typea.setText("");
                capasitya.setText("");
                contacta.setText("");
                adressa.setText("");
                normalbeda.setText("");
                oxygenbeda.setText("");
                icubeda.setText("");
            }
        });

    }



}