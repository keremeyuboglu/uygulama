package com.example.acer.hayditrkiyeleri;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.Util.SoruAdapter;

import java.util.ArrayList;

public class FragmentMenuAracGerec extends Fragment {

    SoruAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_deneme_ekle2_generic,container, false);
        super.onCreate(savedInstanceState);

        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        ArrayList<String> popo = new ArrayList<>();
        popo.add("fragment_deneme_ekle1_popup2");
        popo.add("Cow");
        popo.add("Camel");
        popo.add("Sheep");
        popo.add("Goat");


        // set up the RecyclerView
        RecyclerView recyclerView = v.findViewById(R.id.Srecycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SoruAdapter(getContext(), animalNames);
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Araç Gereç Fragment");
    }
}
