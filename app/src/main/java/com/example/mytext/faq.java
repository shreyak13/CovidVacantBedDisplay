package com.example.mytext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class faq extends AppCompatActivity {
    TextView answerl;
    LinearLayout linLayout;
    Button but;
    CardView cardView;
    TextView text;


    RecyclerView recyclerView;
    ArrayList<String> question=new ArrayList<>();
    ArrayList<String> answer=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_faq);
    recyclerView=findViewById(R.id.faqRecycler);
     answerl=findViewById(R.id.faqAnswer);
     linLayout=findViewById(R.id.ansLayout);
     but=(Button)findViewById(R.id.expandAns);
     cardView=findViewById(R.id.card);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        text=findViewById(R.id.faqQuestion);
       /* but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(linLayout.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                    linLayout.setVisibility(View.VISIBLE);

                }
                else{
                    TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                    linLayout.setVisibility(View.GONE);
                }
            }
        });*/


        try {
            JSONObject jsonObject=new JSONObject(JsonDataFromAsset());
            JSONArray jsonArray=jsonObject.getJSONArray("faq");

            for(int i=0;i<15;i++){
                JSONObject userdata=jsonArray.getJSONObject(i);
                question.add(userdata.getString("Question"));
                answer.add(userdata.getString("Answer"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        faqAdapter faqAdapter=new faqAdapter(question,answer,faq.this);
        recyclerView.setAdapter(faqAdapter);



    }

    private String JsonDataFromAsset() {

        String json=null;
        try {
            InputStream inputStream=getAssets().open("faq.json");
            int sizeOfFile=inputStream.available();
            byte[] bufferData=new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json=new String(bufferData,"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
   /* public void expand(View v){
        if(linLayout.getVisibility() == View.GONE){
            TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
            linLayout.setVisibility(View.VISIBLE);

        }
        else{
            TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
            linLayout.setVisibility(View.GONE);
        }

    }*/
    }

