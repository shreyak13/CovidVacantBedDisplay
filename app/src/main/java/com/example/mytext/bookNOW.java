package com.example.mytext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class bookNOW extends AppCompatActivity {
    EditText name,address,contact,condition,ambulance,mail;
    Button bookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        name=findViewById(R.id.bName);
        address=findViewById(R.id.bAddress);
        contact=findViewById(R.id.bContact);
        condition=findViewById(R.id.bCondition);
        ambulance=findViewById(R.id.bAmbulance);
        bookNow=findViewById(R.id.bookNOW);




        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username="shreyaykhedekar13@dbatu.ac.in";
                final String password="11";
                String tomail="shreyaykhedekar13@gmail.com";
                String messageTo=name.getText().toString();


                String stringHost = "smtp.gmail.com";

                Properties properties = System.getProperties();

                properties.put("mail.smtp.host", stringHost);
                properties.put("mail.smtp.port", "465");
                properties.put("mail.smtp.ssl.enable", "true");
                properties.put("mail.smtp.auth", "true");

                Session session=Session.getInstance(properties,
                        new javax.mail.Authenticator(){

                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username,password);
                            }
                        });

                try{
                    Message message=new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipient(Message.RecipientType.TO,new InternetAddress(tomail));
                    message.setSubject("We want to book a bed");
                    message.setText("Name:-  "+messageTo+"\n"+"Address:-  "+address.getText().toString()+"\n"+"Contact:-  "+contact.getText().toString()+"\n"+"Patient Condition:-  "+condition.getText().toString()+"\n"+"Do We Need Ambulance?:-  "+ambulance.getText().toString());
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Transport.send(message);
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();

                } catch (AddressException e) {
                    e.printStackTrace();



                }
                catch (MessagingException e){
                    throw new RuntimeException(e);

                }



            }
        });



    }
}