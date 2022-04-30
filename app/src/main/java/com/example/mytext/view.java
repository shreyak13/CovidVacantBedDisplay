package com.example.mytext;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    View v;

    FirebaseDatabase fbase=FirebaseDatabase.getInstance();
    DatabaseReference dref=fbase.getReference().child("Update Bed").child("-N-ES-1JCGh044Xar-xM");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

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


        Picasso.get().load(image).into(dimageView);
      /*  int number1=Integer.parseInt(nBed);
        int number2=Integer.parseInt(oBed);
        int number3=Integer.parseInt(iBed);
        int sum=number1+number2+number3;
        tBed.setText(String.valueOf(sum));*/

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String vnbed=snapshot.child("upnormalbed").getValue().toString();
                String vobed=snapshot.child("upoxygenbed").getValue().toString();
                String vibed=snapshot.child("upicubed").getValue().toString();

                vnBed.setText(vnbed);
                voBed.setText(vobed);
                viBed.setText(vibed);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}