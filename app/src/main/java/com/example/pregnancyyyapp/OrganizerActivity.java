package com.example.pregnancyyyapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.VisitFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class OrganizerActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {
    Toolbar toolbar;
    ViewPagerAdapter1 viewPagerAdapter1;
    ViewPager viewPager;
    TabLayout tabLayout;
    PopupWindow popUp;
   private ImageButton img;
    boolean click = true;
    TextView t1,t2,textdte;
    private Spinner spinner;

    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer);
        handler = new Handler();
        viewPager = findViewById(R.id.view_pager);
        textdte=findViewById(R.id.textdate);

        img=findViewById(R.id.img);

img.setOnClickListener(new View.OnClickListener() {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (tabLayout.getSelectedTabPosition())
        {
            case 0:
            {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null,false);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        return true;
                    }
                });
                break;

            }
            case 1: {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window2, null, false);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        return true;
                    }
                });
                break;
            }
            case 2:
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_sugarrecord, null, false);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        return true;
                    }
                });
                break;
        }



       // popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));


    }
});
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        toolbar=findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }
setupToolBarItems();
        /*getSupportActionBar().setTitle("Organizer");
        getSupportActionBar().setSubtitle("Sub Title");*/
       // getSupportActionBar().setDisplayShowTitleEnabled(false);
        @SuppressLint("WrongViewCast") DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        TextView title = findViewById(R.id.toolbar_text);
        title.setText("Organizer");
        title.setOnClickListener(view -> handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.performClick();
            }
        },5000));

        title.setVisibility(View.VISIBLE);

        drawer.addDrawerListener(toggle);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
               R.array.spinner_items, R.layout.spinner_item_bg);
        spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(OnCatSpinnerCL);
        spinner.setVisibility(View.VISIBLE);
       // adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toggle.syncState();
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // setting up the adapter
        viewPagerAdapter1 = new ViewPagerAdapter1(getSupportFragmentManager());


        // add the fragments
        viewPagerAdapter1.add(new VisitFragment(), "M.D VISIT PLANNER");
        viewPagerAdapter1.add(new TodoFragment(), "TO DO");
        viewPagerAdapter1.add(new RecordsFragment(),"SUGAR-LEVEL");



        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter1);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupToolBarItems() {
        TextView title = findViewById(R.id.toolbar_text);
        title.setText("Organizer");
        title.setVisibility(View.VISIBLE);
        ImageButton img = findViewById(R.id.img);
        img.setVisibility(View.VISIBLE);
        ImageView image=findViewById(R.id.imageview);
        image.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.organizer:
                Intent intent = new Intent(OrganizerActivity.this,OrganizerActivity.class);
                startActivity(intent);
                break;

            case R.id.calltaxi:
                Intent i = new Intent(OrganizerActivity.this,CalltaxiActivity.class);
                startActivity(i);
                break;

        }

        return true;
    }

    private AdapterView.OnItemSelectedListener OnCatSpinnerCL = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            ((TextView) parent.getChildAt(0)).setTextSize(15);


        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

public void t1(View v){
    AlertDialog.Builder builder = new AlertDialog.Builder(OrganizerActivity.this);
    ViewGroup viewGroup = findViewById(android.R.id.content);
    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.popup_window1, viewGroup, false);
    builder.setView(dialogView);
    AlertDialog alertDialog = builder.create();
    alertDialog.show();

}
public void check(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.Type)
                .setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }

                }).show();

    }


}









