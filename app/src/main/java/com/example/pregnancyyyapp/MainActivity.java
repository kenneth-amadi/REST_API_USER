package com.example.pregnancyyyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private EditText mail,password;
    private ProgressBar progressBar;

    private TextView forgetPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button registerBtn=findViewById(R.id.register);
        Button loginBtn=findViewById(R.id.login);
        mail=findViewById(R.id.email);
        firebaseAuth=FirebaseAuth.getInstance();
        password=findViewById(R.id.password);
        progressBar=findViewById(R.id.progressBar);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tex_email=mail.getText().toString();
                String tex_password=password.getText().toString();
                if(TextUtils.isEmpty(tex_email)||TextUtils.isEmpty(tex_password))
                {
                    Toast.makeText(MainActivity.this,"All Fields Required",Toast.LENGTH_SHORT).show();
                }
                else {
                    login(tex_email,tex_password);
                }
            }

            private void login(String tex_email, String tex_password) {
                progressBar.setVisibility(View.VISIBLE);
              firebaseAuth.signInWithEmailAndPassword(tex_email,tex_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()){
                            Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }else
                        {
                            Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


    }
}