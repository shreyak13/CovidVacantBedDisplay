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

public class addisolation extends AppCompatActivity {
    EditText namea,typea,capasitya,adressa,contacta,normalbeda,oxygenbeda;
    Button add;
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
        add=findViewById(R.id.icupdate);
        normalbeda=findViewById(R.id.inormalbed);
        oxygenbeda=findViewById(R.id.ioxygenbed);

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
                String address=adressa.getText().toString();
                String cont=contacta.getText().toString();

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
                                    inewpost.child("address").setValue(address);
                                    inewpost.child("contact").setValue(cont);
                                    inewpost.child("image").setValue(task.getResult().toString());


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
            }
        });
    }
}