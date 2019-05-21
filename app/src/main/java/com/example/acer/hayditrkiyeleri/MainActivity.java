package com.example.acer.hayditrkiyeleri;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = findViewById(R.id.nav_bar);
        setSupportActionBar(mToolBar);
        //Setting toolbar

        mDrawerLayout = findViewById(R.id.drawer_layout);
        //Getting drawer

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        //Setting things about toolbar :(

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Gotta be filled when other fragments are done
        switch (item.getItemId()){

            case R.id.arac_gerec:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMenuAracGerec()).commit();
                break;
            case R.id.denemelerim:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMenuDenemelerim()).commit();
                break;
            case R.id.hesap:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentDenemeEkleSecondGeneric()).commit();
                break;
            case R.id.konu_basliklari:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMenuKonuBasliklari()).commit();
                break;
            case R.id.programim:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMenuProgramim()).commit();
                break;

        }


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        //When Item selected
        if(mToggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
