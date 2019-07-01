package com.example.hay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
MainFragment mainFragment;
MapFragment mapFragment;
FragmentManager fmanager;
Toolbar toolbar;
    NavigationView navView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
 navView = findViewById(R.id.nav_view);

        TextView login =navView.getHeaderView(0).findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
        navView.setItemIconTintList(null);
        BottomNavigationView bnavView = findViewById(R.id.bottom_navigationView);
        bnavView.setItemIconTintList(null);

        bnavView.setOnNavigationItemSelectedListener(bottomlistener);
        navView.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    mainFragment = new MainFragment();
    mapFragment=new MapFragment();
    fmanager=getSupportFragmentManager();
toolbar=findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        FragmentTransaction transaction = fmanager.beginTransaction();
        transaction.replace(R.id.content,mainFragment);
        transaction.commitAllowingStateLoss();


    }

    private NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.login:


                case R.id.question:

                    return true;
                case R.id.settings:
                    return true;

            }
            return false;
        }
    };
    private BottomNavigationView.OnNavigationItemSelectedListener bottomlistener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.like:

                    return true;
                case R.id.home:
                    FragmentTransaction transaction = fmanager.beginTransaction();
                    transaction.replace(R.id.content,mainFragment);
                    transaction.commitAllowingStateLoss();
                    return true;

                case R.id.map:

              //mapFragment.show(getSupportFragmentManager(),"example");
                    return  true;
                case R.id.reserved:
                    return true;
                case R.id.mypage:
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case android.R.id.home:
                DrawerLayout drawerLayout = findViewById(R.id.container);
                drawerLayout.openDrawer(GravityCompat.START);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.main_top,menu);
        return true;
    }
}
