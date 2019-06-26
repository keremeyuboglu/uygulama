package com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.R;

// PopUp for Karekok
public class FragmentKarekokPopUp extends DialogFragment {
    String[] deneme = new String[] {
            "Deneme #2222222222222451",
            "Deneme #2",
            "Deneme #3",
            "Deneme #4",
            "Deneme #4",
            "Deneme #4"
            ,
            "Deneme #4",
            "Deneme #4",
            "Deneme #4",
            "Deneme #4",
            "Deneme #4",
            "Deneme #4",
            "Deneme #4",
            "Deneme #4",
            "Deneme #4",


    };
    Button button;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deneme_ekle1_popup2,null);
        listView = v.findViewById(R.id.listview);
        button = v.findViewById(R.id.popup2button);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice,deneme);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                FragmentDenemeEkleSecondKarekok newGamefragment = new FragmentDenemeEkleSecondKarekok();
                fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return v;
    }


}