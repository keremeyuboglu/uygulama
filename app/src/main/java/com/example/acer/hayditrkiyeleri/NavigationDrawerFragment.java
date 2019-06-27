package com.example.acer.hayditrkiyeleri;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NavigationDrawerFragment extends Fragment {

    // Navigation menü fragmentı
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_navigation_layout,container,false);
        setUpRecyclerView(v);
        return v;
    }

    // Recylclerview oluşturan sınıf
    private void setUpRecyclerView(View v) {

        RecyclerView recyclerView = v.findViewById(R.id.drawer_list);
        NavigationAdapter adapter = new NavigationAdapter(getActivity(),NavigationDrawerItem.getData(),getFragmentManager());
        Log.d("mesaj","bu ne bu ne");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // Üç çizgili Drawer oluşturmak için
    public void setUpNavDrawer(DrawerLayout drawer, Toolbar toolbar){
        mDrawerLayout = drawer;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        // Açık kapalı durumlarda Drawerı dinleme fonksiyonu
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // Açılırken 3 çizginin animasyona sahip olması
                mDrawerToggle.onDrawerSlide(drawerView,slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
}