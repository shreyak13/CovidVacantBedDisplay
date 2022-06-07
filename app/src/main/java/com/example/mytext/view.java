package com.example.mytext;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class view extends AppCompatActivity {
    private ImageView dimageView;
    TextView textView,textView1,textView2,textView3,textView4,normalbed,oxygenbed,icubed,tBed,vnBed,voBed,viBed;
TextView vtBed;
    View v;
    Button BookNow;
    FirebaseDatabase fbase=FirebaseDatabase.getInstance();
    DatabaseReference dref=fbase.getReference().child("Update Bed");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SharedPreferences shared=getSharedPreferences("myKey",MODE_PRIVATE);
        String stre=shared.getString("email","");

        Bundle extras = getIntent().getExtras();

       dimageView =findViewById(R.id.imageView1);
        textView=findViewById(R.id.sname);
        textView1=findViewById(R.id.stype);
        textView2=findViewById(R.id.sbed);
        textView3=findViewById(R.id.saddress);
        textView4=findViewById(R.id.scontact);
        normalbed=findViewById(R.id.normalBed);
        oxygenbed=findViewById(R.id.oxygenBed);
        icubed=findViewById(R.id.ICUBed);
        tBed=findViewById(R.id.totalBed);
        vnBed=findViewById(R.id.vNormalBed);
        voBed=findViewById(R.id.vOxygenbed);
        viBed=findViewById(R.id.vIcubed);
        BookNow=findViewById(R.id.bookNow);
        vtBed=findViewById(R.id.vTBed);

        String name = extras.getString("Name");
        String bed = extras.getString("bed");
        String type = extras.getString("type");
        String address = extras.getString("address");
        String contact = extras.getString("contact");
        String image = extras.getString("image");
        String Nbed=extras.getString("normalBed");
        String Obed=extras.getString("oxygenBed");
        String IBed=extras.getString("ICUBed");


        textView.setText(name);
        textView1.setText(type);
        textView2.setText(bed);
        textView3.setText(address);
        textView4.setText(contact);
        normalbed.setText(Nbed);
        oxygenbed.setText(Obed);
        icubed.setText(IBed);
        tBed.setText(bed);


        Picasso.get().load(image).into(dimageView);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Update Bed");

        reference.orderByChild("stre").equalTo(stre).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String upnbed=datas.child("upnormalbed").getValue().toString();
                    String upobed=datas.child("upoxygenbed").getValue().toString();
                    String upibed=datas.child("upicubed").getValue().toString();
                    String key=datas.child("key").getValue().toString();
                    vnBed.setText(upnbed);
                    voBed.setText(upobed);
                    viBed.setText(upibed);


                    //String totalv=Integer.parseInt(upibed)+Integer.parseInt(upnbed)+Integer.parseInt(upobed);
                    // String key =  dref.push().getKey();
                    // datas.child("key").setValue(key);

                    int totalv=Integer.valueOf(upibed)+Integer.valueOf(upnbed)+Integer.valueOf(upobed);

                    vtBed.setText(String.valueOf(totalv));

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        BookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),bookNOW.class));
            }
        });





    }




}