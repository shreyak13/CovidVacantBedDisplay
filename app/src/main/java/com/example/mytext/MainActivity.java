package com.example.mytext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    EditText fullname,email,password,phone;
    Button register,login;
    boolean valid=true;
     FirebaseAuth fauth;
     FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        register=findViewById(R.id.create);
        login=findViewById(R.id.login);

        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFiels(fullname);
                checkFiels(email);
                checkFiels(password);
                checkFiels(phone);
                if(valid){
                    fauth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user=fauth.getCurrentUser();
                            Toast.makeText(MainActivity.this,"account created",Toast.LENGTH_SHORT).show();
                            DocumentReference df=fstore.collection("user").document(user.getUid());
                            Map<String,Object> userinfo=new HashMap<>();
                            userinfo.put("fullname",fullname.getText().toString());
                            userinfo.put("email",email.getText().toString());
                            userinfo.put("phone",phone.getText().toString());

                            userinfo.put("isuser","1");
                            df.set(userinfo);

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),signin.class));
            }
        });



    }
    public boolean checkFiels(EditText editText){
        if(editText.getText().toString().isEmpty()){
            editText.setError("error");
            valid=false;
        }
        else{
            valid=true;
        }
        return valid;
    }
}