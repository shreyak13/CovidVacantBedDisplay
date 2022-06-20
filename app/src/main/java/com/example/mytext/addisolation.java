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

public class addisolation extends AppCompatActivity {
    EditText namea,typea,capasitya,adressa,contacta,normalbeda,oxygenbeda,icua;
    Button add,update;
    FirebaseStorage storage;
    FirebaseDatabase fbase;
    DatabaseReference dref;
    ImageButton iimageButton;
    private static final int gallarycode=1;
    Uri imageuri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addisolation);
        iimageButton=findViewById(R.id.icimageButton);
        namea=findViewById(R.id.icname);
        typea=findViewById(R.id.ictype);
        capasitya=findViewById(R.id.iccapsity);
        adressa=findViewById(R.id.icaddress);
        contacta=findViewById(R.id.iccontact);
        add=findViewById(R.id.icadd);
        normalbeda=findViewById(R.id.inormalbed);
        oxygenbeda=findViewById(R.id.ioxygenbed);
        icua=findViewById(R.id.iicubed);
        update=findViewById(R.id.icupdate);
        fbase=FirebaseDatabase.getInstance();
        dref= fbase.getReference().child("isolation centre");
        storage=FirebaseStorage.getInstance();

        iimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,gallarycode);
            }
        });
        SharedPreferences shared=getSharedPreferences("myKey",MODE_PRIVATE);
        String stre=shared.getString("email","");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("isolation centre");

        reference.orderByChild("stre").equalTo(stre).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String name=datas.child("name").getValue().toString();
                    String type=datas.child("type").getValue().toString();
                    String capasity=datas.child("bed").getValue().toString();
                    String contact=datas.child("contact").getValue().toString();
                    String address=datas.child("address").getValue().toString();
                    String normalBed=datas.child("normalBed").getValue().toString();
                    String oxygenBed=datas.child("oxygenBed").getValue().toString();
                    String icuBed=datas.child("icuBed").getValue().toString();
                    namea.setText(name);
                    typea.setText(type);
                    capasitya.setText(capasity);
                    adressa.setText(address);
                    contacta.setText(contact);
                    normalbeda.setText(normalBed);
                    oxygenbeda.setText(oxygenBed);
                    icua.setText(icuBed);

                    if(namea!=null){
                        add.setVisibility(View.GONE);
                    }
                    // String key =  dref.push().getKey();
                    // datas.child("key").setValue(key);

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
                String bed=capasitya.getText().toString();
                String address=adressa.getText().toString();
                String cont=contacta.getText().toString();
                String Nbed=normalbeda.getText().toString();
                String Obed=oxygenbeda.getText().toString();
                String Ibed=icua.getText().toString();

                HashMap<String,Object> uphospital=new HashMap<>();

                uphospital.put("name",name);
                uphospital.put("type",type);
                uphospital.put("bed",bed);
                uphospital.put("address",address);
                uphospital.put("contact",cont);
                uphospital.put("normalBed",Nbed);
                uphospital.put("oxygenBed",Obed);
                uphospital.put("ICUBed",Ibed);

       /* namea.setText(name);
        typea.setText(type);
        capasitya.setText(bed);
        adressa.setText(address);
        contacta.setText(cont);
        normalbeda.setText(Nbed);
        oxygenbeda.setText(Obed);
        icubeda.setText(Ibed);*/



                DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference("isolation centre");

                dbRef.orderByChild("stre").equalTo(stre).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot datas : snapshot.getChildren()) {
                            String str = datas.child("stre").getValue().toString();
                            datas.getRef().updateChildren(uphospital).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Isolation Centre Details Updated", Toast.LENGTH_SHORT).show();
                                }
                            }); }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference("Hospitals");






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==gallarycode&& resultCode==RESULT_OK){
            imageuri=data.getData();
            iimageButton.setImageURI(imageuri);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=namea.getText().toString();
                String type=typea.getText().toString();
                String bed=capasitya.getText().toString();
                String nbed=normalbeda.getText().toString();
                String obed=normalbeda.getText().toString();
                String ibed=icua.getText().toString();
                String address=adressa.getText().toString();
                String cont=contacta.getText().toString();
                SharedPreferences shared=getSharedPreferences("myKey",MODE_PRIVATE);
                String stre=shared.getString("email","");

                if(!(name.isEmpty()&&type.isEmpty()&&bed.isEmpty()&&address.isEmpty()&&cont.isEmpty())){


                    StorageReference filepath=storage.getReference().child("isoimagepost").child(imageuri.getLastPathSegment());
                    filepath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadurl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t=task.getResult().toString();
                                    DatabaseReference inewpost=dref.push();

                                    inewpost.child("name").setValue(name);
                                    inewpost.child("type").setValue(type);
                                    inewpost.child("bed").setValue(bed);
                                    inewpost.child("normalBed").setValue(nbed);
                                    inewpost.child("oxygenBed").setValue(obed);
                                    inewpost.child("icuBed").setValue(ibed);
                                    inewpost.child("address").setValue(address);
                                    inewpost.child("contact").setValue(cont);
                                    inewpost.child("image").setValue(task.getResult().toString());
                                    String key =  dref.push().getKey();
                                    inewpost.child("key").setValue(key);
                                    inewpost.child("stre").setValue(stre);
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
                icua.setText("");


            }
        });


    }
}