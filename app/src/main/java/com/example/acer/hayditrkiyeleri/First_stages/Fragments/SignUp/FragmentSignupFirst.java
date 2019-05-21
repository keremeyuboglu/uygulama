package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.R;

public class FragmentSignupFirst extends Fragment {


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup1, container, false);


        Button changeFragment2 = view.findViewById(R.id.button2);

        setRetainInstance(true);

        changeFragment2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                FragmentSignupSecond newGamefragment = new FragmentSignupSecond();
                fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }


        });

        return view;
    }
}
