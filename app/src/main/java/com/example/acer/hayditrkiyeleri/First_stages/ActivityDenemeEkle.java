package com.example.acer.hayditrkiyeleri.First_stages;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle.FragmentDenemeEkleFirst;
import com.example.acer.hayditrkiyeleri.R;

public class ActivityDenemeEkle extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme_ekle);


        if(null == savedInstanceState) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            FragmentDenemeEkleFirst fragment = new FragmentDenemeEkleFirst();
            fragmentTransaction.add(R.id.deneme_container, fragment);
            fragmentTransaction.commit();
        }



    }

    void addDeneme(View view){} //Will be implemented
}
