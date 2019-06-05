package com.example.acer.hayditrkiyeleri.First_stages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle.FragmentDenemeEkleFirst;
import com.example.acer.hayditrkiyeleri.R;
import com.example.acer.hayditrkiyeleri.Util.RVItemGenerator;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle2_outer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ActivityDenemeEkle extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme_ekle);


        final ArrayList<Item_DenemeEkle2_outer> mDers = RVItemGenerator.pump_Item();

        Bundle args = new Bundle();
        args.putParcelableArrayList("ders", mDers);


        if(null == savedInstanceState) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            FragmentDenemeEkleFirst fragment = new FragmentDenemeEkleFirst();
            fragment.setArguments(args);
            fragmentTransaction.add(R.id.deneme_container, fragment);
            fragmentTransaction.commit();
        }

        FloatingActionButton mButton = findViewById(R.id.floatingActionButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityDenemeEkle.this, mDers.get(0).getDers_dogru(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void addDeneme(View view){} //Will be implemented
}
