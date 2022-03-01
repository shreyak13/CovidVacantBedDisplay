package com.example.mytext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class signin extends AppCompatActivity {
    EditText email,password;
    boolean valid=true;
    Button signin;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    TextView textView;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signin=findViewById(R.id.signin);

        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

       signin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

                  EditText  email=(EditText) findViewById(R.id.username);
                  EditText  password=(EditText) findViewById(R.id.password1);
                  String email1=email.getText().toString();
                  String password1= password.getText().toString();


               if(valid)

              fauth.signInWithEmailAndPassword(email1, password1).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                  @Override
                  public void onSuccess(AuthResult authResult) {
                      Toast.makeText(signin.this, "success", Toast.LENGTH_SHORT).show();
                      checkUserLevel(authResult.getUser().getUid());
                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(signin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              });
           }
       });
        textView=findViewById(R.id.register1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }

    private void checkUserLevel(String uid) {
        DocumentReference df=fstore.collection("user").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","Onsuccess"+documentSnapshot.getData());

                if(null != documentSnapshot.getString("isadmin")){

                    startActivity(new Intent(getApplicationContext(),admin.class));
                    finish();
                }
                else {
                    startActivity(new Intent(getApplicationContext(),user.class));
                    finish();
                }
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

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),user.class));
         finish();
        }
    }
}