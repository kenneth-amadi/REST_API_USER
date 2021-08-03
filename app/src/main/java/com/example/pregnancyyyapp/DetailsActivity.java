package com.example.pregnancyyyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {
    private RadioGroup radioSexGroup, radioSexgroup1;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private RadioButton radioSexButton, radioSexButton1, radioSexButton2,radioduebutton, radiolastbutton;
    DatePicker simpleDatePicker;
    ImageButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        firebaseAuth = FirebaseAuth.getInstance();
        radioSexGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioSexgroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        ImageButton img1 = findViewById(R.id.img1);
        img1.setVisibility(View.VISIBLE);
        submit = (ImageButton) findViewById(R.id.img1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender;
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);
                radioSexButton1 = (RadioButton) findViewById(selectedId);
                radioSexButton2=(RadioButton) findViewById(selectedId);


                if(selectedId==radioSexButton.getId()){
                    gender = "male";
                    Toast.makeText(getApplicationContext(),"Male selected", Toast.LENGTH_SHORT).show();
                }
                else if(selectedId==radioSexButton1.getId()){
                    gender="Female";
                    Toast.makeText(getApplicationContext(),"FeMale selected", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    gender="Don't Know";
                    Toast.makeText(getApplicationContext(),"Don't Know selected", Toast.LENGTH_SHORT).show();

                }
                int selectedId1 = radioSexgroup1.getCheckedRadioButtonId();
                radioduebutton = (RadioButton) findViewById(selectedId);
                radiolastbutton = (RadioButton) findViewById(selectedId);

                simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
                String day = "Day = " + simpleDatePicker.getDayOfMonth();
                String month = "Month = " + (simpleDatePicker.getMonth() + 1);
                String year = "Year = " + simpleDatePicker.getYear();


                if(selectedId==radioduebutton.getId()){

                    Calendar cal = Calendar.getInstance();
                    cal.set(simpleDatePicker.getYear(), simpleDatePicker.getMonth(), simpleDatePicker.getDayOfMonth());
                    Date tempDate = cal.getTime();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String strInsertionval = formatter.format(tempDate);



                }
                else {
                    Calendar cal = Calendar.getInstance();
                    cal.set(simpleDatePicker.getYear(), simpleDatePicker.getMonth(), simpleDatePicker.getDayOfMonth());
                    Date tempDate = cal.getTime();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String strInsertionval1 = formatter.format(tempDate);


                }
                // display the values by using a toast
                // Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();
//database.child("users").child(userId).child("username").setValue(name)  example
                FirebaseUser ruser = firebaseAuth.getCurrentUser();
                String userId = ruser.getUid();

                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("Gender",gender);

                String strInsertionval = "";
                hashMap.put("Duedate", strInsertionval);
                String strInsertionval1 = "";
                hashMap.put("Last Period",strInsertionval1);

                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Log.e("onClick: ", userId);

                            Intent in=new Intent(DetailsActivity.this,StartActivity.class);
                            Toast.makeText(DetailsActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                        }else
                        {
                            Toast.makeText(DetailsActivity.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }


}