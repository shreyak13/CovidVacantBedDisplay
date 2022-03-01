package com.example.mytext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class addhospital extends AppCompatActivity {
    EditText namea,typea,capasitya,adressa,contacta,normalbeda,oxygenbeda,icubeda;
    Button add;
    FirebaseStorage storage;
    FirebaseDatabase fbase;
    DatabaseReference dref;
    ImageButton imageButton;
    private static final int gallarycode=1;
    Uri imageuri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhospital);
        imageButton=findViewById(R.id.imageButton);
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

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
             startActivityForResult(intent,gallarycode);
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
                String bed=capasitya.getText().toString();
                String address=adressa.getText().toString();
                String cont=contacta.getText().toString();
                String Nbed=normalbeda.getText().toString();
                String Obed=oxygenbeda.getText().toString();
                String Ibed=icubeda.getText().toString();

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