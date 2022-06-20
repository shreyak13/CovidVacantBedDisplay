package com.example.mytext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class addambulance extends AppCompatActivity {
    EditText namea,typea,adressa,contacta;
    Button add,update;
    FirebaseStorage storage;
    FirebaseDatabase fbase;
    DatabaseReference dref;
    ImageButton imageButton;
    private static final int gallarycode=1;
    Uri imageuri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addambulance);
        imageButton=findViewById(R.id.imageButton);
        namea=findViewById(R.id.name);
        typea=findViewById(R.id.type);
        adressa=findViewById(R.id.address);
        contacta=findViewById(R.id.contact);
        add=findViewById(R.id.Aadd);
        fbase=FirebaseDatabase.getInstance();
        dref= fbase.getReference().child("Ambulance");
        storage=FirebaseStorage.getInstance();
        update=findViewById(R.id.Aupdate);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,gallarycode);
            }
        });
        SharedPreferences shared=getSharedPreferences("myKey",MODE_PRIVATE);
        String stre=shared.getString("email","");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ambulance");

        reference.orderByChild("stre").equalTo(stre).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String name=datas.child("name").getValue().toString();
                    String type=datas.child("type").getValue().toString();
                    String contact=datas.child("contact").getValue().toString();
                    String address=datas.child("address").getValue().toString();

                    namea.setText(name);
                    typea.setText(type);
                    adressa.setText(address);
                    contacta.setText(contact);


                    if(namea!=null){
                        add.setVisibility(View.GONE);
                    }


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name=namea.getText().toString();
                String type=typea.getText().toString();

                String address=adressa.getText().toString();
                String cont=contacta.getText().toString();
                HashMap<String,Object> uphospital=new HashMap<>();

                uphospital.put("name",name);
                uphospital.put("type",type);
                uphospital.put("address",address);
                uphospital.put("contact",cont);

       /* namea.setText(name);
        typea.setText(type);
        capasitya.setText(bed);
        adressa.setText(address);
        contacta.setText(cont);
        normalbeda.setText(Nbed);
        oxygenbeda.setText(Obed);
        icubeda.setText(Ibed);*/



                DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference("Ambulance");

                dbRef.orderByChild("stre").equalTo(stre).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot datas : snapshot.getChildren()) {
                            String str = datas.child("stre").getValue().toString();
                            datas.getRef().updateChildren(uphospital).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Ambulance Details Updated", Toast.LENGTH_SHORT).show();
                                }
                            }); }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==gallarycode&& resultCode==RESULT_OK){
            imageuri=data.getData();
            imageButton.setImageURI(imageuri);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=namea.getText().toString();
                String type=typea.getText().toString();
                String address=adressa.getText().toString();
                String cont=contacta.getText().toString();
                SharedPreferences shared=getSharedPreferences("myKey",MODE_PRIVATE);
                String stre=shared.getString("email","");
                if(!(name.isEmpty()&&type.isEmpty()&&address.isEmpty()&&cont.isEmpty() && imageuri!=null)){


                    StorageReference filepath=storage.getReference().child("ambulance").child(imageuri.getLastPathSegment());
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
                                    newpost.child("address").setValue(address);
                                    newpost.child("contact").setValue(cont);
                                    newpost.child("stre").setValue(stre);
                                    newpost.child("image").setValue(task.getResult().toString());


                                }
                            });
                        }
                    });
                }
                Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                namea.setText("");
                typea.setText("");
                contacta.setText("");
                adressa.setText("");
            }
        });

    }




}