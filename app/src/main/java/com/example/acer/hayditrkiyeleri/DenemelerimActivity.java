package com.example.acer.hayditrkiyeleri;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class DenemelerimActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denemelerimactivity);
        // ToolBar oluşturma
        setUpToolbar();
        // Drawer oluşturma
        setUpDrawer();
    }

    private void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            toolbar.setElevation(5f);
        }
        toolbar.setTitle("Programcı");
    }


    private void setUpDrawer() {
        // NavigationDrawer sınıfından nesne üret, drawerlayout ile toolbarı o nesnedeki fonksiyona parametre olarak gönder
        NavigationDrawerFragment navigationDrawer = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer.setUpNavDrawer(drawerLayout,toolbar);
    }


}
