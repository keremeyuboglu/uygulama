package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.First_stages.ActivityDenemeEkle;
import com.example.acer.hayditrkiyeleri.R;

public class FragmentSignupThird extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_signup3, container, false);

        Button finishSignup = v.findViewById(R.id.son);

        final CardView cardView = v.findViewById(R.id.denemecard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent myIntent = new Intent(getActivity(), ActivityDenemeEkle.class); // Deneme Ekleme Ekrani Aktivitesi yap
                myIntent.putExtra("key", 5); //Optional parameters
                getActivity().startActivity(myIntent);
            }
        });

        setRetainInstance(true);


        finishSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.signupContainer,new FragmentSignupFourth());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }


        });



        return v;


    }
}
