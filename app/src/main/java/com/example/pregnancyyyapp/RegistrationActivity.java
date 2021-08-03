package com.example.pregnancyyyapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {
    private EditText username,email,address,password,mobile;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();

        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);
        password=findViewById(R.id.password);
        mobile=findViewById(R.id.mobile);
        Button register  =(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name=username.getText().toString();
                final String mail=email.getText().toString();
                final String adrs=address.getText().toString();
                final String pwd=password.getText().toString();
                final String ph=mobile.getText().toString();
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(mail)||TextUtils.isEmpty(adrs)||TextUtils.isEmpty(pwd)||TextUtils.isEmpty(ph))
                {
                    Toast.makeText(RegistrationActivity.this,"All Fields are Required",Toast.LENGTH_SHORT).show();
                }
                else
                    register(name, mail, adrs, pwd, ph);

            }

            private void register(String name, String mail, String adrs, String pwd, String ph) {
                firebaseAuth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser ruser=firebaseAuth.getCurrentUser();
                            String userId=ruser.getUid();

                            databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(userId);
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("userId",userId);
                            hashMap.put("name",name);
                            hashMap.put("mail",mail);
                            hashMap.put("adrs",adrs);
                            hashMap.put("pwd",pwd);
                            hashMap.put("ph",ph);
                            hashMap.put("newdata","");

                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                  Log.e("task",task.toString());
                                    if(task.isSuccessful())
                                    {
                                        Intent in=new Intent(RegistrationActivity.this,MainActivity.class);
                                        Toast.makeText(RegistrationActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(in);
                                    }else
                                    {
                                        Toast.makeText(RegistrationActivity.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else
                        {
                            Toast.makeText(RegistrationActivity.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}