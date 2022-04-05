package com.example.mytext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

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
    RecyclerView recyclerView;
    ArrayList<String> question=new ArrayList<>();
    ArrayList<String> answer=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
    recyclerView=findViewById(R.id.faqRecycler);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject=new JSONObject(JsonDataFromAsset());
            JSONArray jsonArray=jsonObject.getJSONArray("faq");

            for(int i=0;i<10;i++){
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


}