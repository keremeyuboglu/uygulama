package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.R;
import com.llollox.androidprojects.compoundbuttongroup.CompoundButtonGroup;

import java.util.List;

public class FragmentSignupSecond extends Fragment {

    private UserInfo userInfo;
    private CompoundButtonGroup bolum;
    private CompoundButtonGroup hedef;
    private CompoundButtonGroup saat;

    public FragmentSignupSecond(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_signup2, container, false);

        Button changeFragment = v.findViewById(R.id.button3);
        bolum = v.findViewById(R.id.radio_bolum);
        hedef = v.findViewById(R.id.radio_hedef);
        saat = v.findViewById(R.id.radio_saat);

        setRetainInstance(true);

        changeFragment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                List<Integer> bol= bolum.getCheckedPositions();
                List<Integer> hed= hedef.getCheckedPositions();
                List<Integer> sat= saat.getCheckedPositions();

                if(bol.size() != 0){ //If a radio selected
                    userInfo.setBolum(bol.get(0));
                }else{
                    //ERROR!!!!
                }

                if(hed.size() != 0){ //If a radio selected
                    userInfo.setBolum(bol.get(0));
                }else{
                    //ERROR!!!!
                }

                if(sat.size() != 0){ //If a radio selected
                    userInfo.setBolum(bol.get(0));
                }else{
                    //ERROR!!!!
                }
                /*Fonksiyon da yazabilirim ama altıüstü 3 tane koplaya yapıştır.
                Zaten bir taslak bu*/


                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                FragmentSignupThird newGamefragment = new FragmentSignupThird();
                fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });


        return v;
    }

}
