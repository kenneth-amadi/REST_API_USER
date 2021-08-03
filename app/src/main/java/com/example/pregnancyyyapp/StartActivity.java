package com.example.pregnancyyyapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import  android.widget.RelativeLayout;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class StartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager mViewPager;
    Toolbar toolbar;
LinearLayout l1;

    // images array
    int[] images = {R.drawable.week2, R.drawable.week3, R.drawable.week4, R.drawable.week5,
            R.drawable.week6, R.drawable.week7, R.drawable.week8, R.drawable.week9, R.drawable.week10,R.drawable.week11,R.drawable.week12,
            R.drawable.week13, R.drawable.week14,R.drawable.week15,R.drawable.week16,R.drawable.week17,R.drawable.week18,R.drawable.week19,
            R.drawable.week20,R.drawable.week21,R.drawable.week22,R.drawable.week23,R.drawable.week24,R.drawable.week25,R.drawable.week26,R.drawable.week27,
            R.drawable.week28,  R.drawable.week29,  R.drawable.week30,  R.drawable.week31,  R.drawable.week32,  R.drawable.week33,  R.drawable.week34,
            R.drawable.week35,R.drawable.week36,R.drawable.week37,R.drawable.week38,R.drawable.week39,R.drawable.week40,R.drawable.week41
    };

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mViewPager = (ViewPager) findViewById(R.id.viewPagerMain);
toolbar=findViewById(R.id.toolbar);
ImageView img=findViewById(R.id.imageview);
img.setVisibility(View.VISIBLE);
      //  ((TextView)toolbar.findViewById(R.id.toolbar_text)).setText("Organizer");

l1=findViewById(R.id.l1);

        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }
        @SuppressLint("WrongViewCast") DrawerLayout drawer = findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(toggle);
       getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toggle.syncState();
        NavigationView navigationView=findViewById(R.id.nav_view);
navigationView.setNavigationItemSelectedListener(this);
        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(StartActivity.this, images);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                TextView title= findViewById(R.id.toolbar_text);
                title.setVisibility(View.VISIBLE);
                if (position == 0) {
                    title.setText("My Baby at week2");
                } else if (position ==1) {
                    title.setText("My Baby at week3");

                }else if(position==2){
                    title.setText("My Baby at week4");
                }else if(position==3){
                    title.setText("My Baby at week5");
                }else if(position==4){
                    title.setText("My Baby at week6");
                }else if(position==5){
                    title.setText("My Baby at week7");
                }else if(position==6){
                    title.setText("My Baby at week8");

                }else if(position==7)
                {
                    title.setText("My Baby at week9");
                }else if(position==8)
                {
                    title.setText("My Baby at week10");
                }else if(position==9)
                {
                    title.setText("My Baby at week11");
                }else if(position==10){
                    title.setText("My Baby at week12");
                }else if(position==11){
                    title.setText("My Baby at week13");
                }else if(position==12){
                    title.setText("My Baby at week14");
                }else if(position==13){
                    title.setText("My Baby at week15");
                }else if(position==14){
                    title.setText("My Baby at week16");
                } else if (position == 15) {
                    title.setText("My Baby at week17");
                }
                else if (position == 16) {
                    title.setText("My Baby at week18");
                }
                else if (position == 17) {
                    title.setText("My Baby at week19");
                }
                else if (position == 18) {
                    title.setText("My Baby at week20");
                }
                else if (position == 19) {
                    title.setText("My Baby at week21");
                } else if (position == 20) {
                    title.setText("My Baby at week22");
                } else if (position == 21) {
                    title.setText("My Baby at week23");
                } else if (position == 22) {
                    title.setText("My Baby at week24");
                } else if (position == 23) {
                    title.setText("My Baby at week25");
                }
                else if (position == 24) {
                    title.setText("My Baby at week26");
                } else if (position == 25) {
                    title.setText("My Baby at week27");
                } else if (position == 26) {
                    title.setText("My Baby at week28");
                } else if (position == 27) {
                    title.setText("My Baby at week29");
                } else if (position == 28) {
                    title.setText("My Baby at week30");
                } else if (position == 29) {
                    toolbar.setTitle("My Baby at week31");
                } else if (position == 30) {
                    title.setText("My Baby at week32");
                }
                else if (position == 31) {
                    title.setText("My Baby at week33");
                } else if (position == 32) {
                    title.setText("My Baby at week34");
                } else if (position == 33) {
                    title.setText("My Baby at week35");
                } else if (position == 34) {
                    title.setText("My Baby at week36");
                } else if (position == 35) {
                    title.setText("My Baby at week37");
                }
                else if (position == 36) {
                    title.setText("My Baby at week38");
                }
                else if (position == 37) {
                    title.setText("My Baby at week39");
                } else if (position == 38) {
                    title.setText("My Baby at week40");
                } else if (position == 39) {
                    title.setText("My Baby at week41");
                }


            }
            @SuppressLint("RestrictedApi")
            public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.activity_main_drawer, menu);
                if (menu instanceof MenuBuilder) {
                    MenuBuilder m = (MenuBuilder) menu;
                    m.setOptionalIconsVisible(true);

                }
                return true;
            }


            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.organizer:
                Intent intent = new Intent(StartActivity.this,OrganizerActivity.class);
                startActivity(intent);
                break;

            case R.id.calltaxi:
                Intent i = new Intent(StartActivity.this,CalltaxiActivity.class);
                startActivity(i);
                break;

        }

        return true;
    }
}