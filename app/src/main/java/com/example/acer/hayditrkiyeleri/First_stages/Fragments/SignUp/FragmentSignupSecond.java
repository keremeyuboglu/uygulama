package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.R;
import com.llollox.androidprojects.compoundbuttongroup.CompoundButtonGroup;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.List;

import static android.content.ContentValues.TAG;
import static android.view.View.GONE;


public class FragmentSignupSecond extends Fragment {


    private CompoundButtonGroup bolum;
    private CompoundButtonGroup hedef;
    private CompoundButtonGroup sinif, tercihyaptinmi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_signup2, container, false);

        Button changeFragment = v.findViewById(R.id.button3);
        bolum = v.findViewById(R.id.radio_bolum);
        hedef = v.findViewById(R.id.radio_hedef);
        sinif = v.findViewById(R.id.radio_sinif);
        tercihyaptinmi = v.findViewById(R.id.mezun_soru);



        NumberPicker numberPicker1 = v.findViewById(R.id.obp_1);
        NumberPicker numberPicker2 = v.findViewById(R.id.obp_2);

        setRetainInstance(true);

        changeFragment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                UserInfo userInfo= FragmentSignupFirst.userinfo;
                List<Integer> bol= bolum.getCheckedPositions();
                List<Integer> hed= hedef.getCheckedPositions();
                List<Integer> sin= sinif.getCheckedPositions();
                List<Integer> isTercih= tercihyaptinmi.getCheckedPositions();


                if(bol.size() != 0){ //If a radio selected
                    userInfo.setBolum(bol.get(0));
                }else{
                    //ERROR!!!!
                }

                if(hed.size() != 0){ //If a radio selected
                    userInfo.setHedef(hed.get(0));
                }else{
                    //ERROR!!!!
                }

                if(sin.size() != 0){ //If a radio selected

                    int sinif=sin.get(0);

                    userInfo.setSinif(sinif);

                    if(sinif==1){ //Mezun ise

                        if(isTercih.size()!=0){

                            userInfo.setIstercih(isTercih.get(0));

                        }else{
                            //ERROR!!!!
                        }

                    }


                }else{
                    //ERROR!!!!
                }
                /*Fonksiyon da yazabilirim ama altıüstü 3 tane koplaya yapıştır.
                Zaten bir taslak bu*/

                // float obp = numberPicker1.getValue() + (numberPicker2.getValue() / 100);
                //  userInfo.setObp(obp);
                //  Mezun falan da olayi lazim da bakaaaaaalim

                double obp= numberPicker1.getValue()+numberPicker2.getValue()/100.0;
                userInfo.setObp(obp);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                FragmentSignupThird newGamefragment = new FragmentSignupThird();
                fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });

        LinearLayout sayilar = v.findViewById(R.id.hedef_sayilar);
        Spinner uniler = v.findViewById(R.id.hedef_uni); // Bir yerden datalari aliriz artik
        Switch mSwitch = v.findViewById(R.id.swicherino);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Log.d(TAG, "onCreateView: b';'");
                    uniler.setVisibility(View.GONE);
                    sayilar.setVisibility(View.VISIBLE);
                }
                else{

                    uniler.setVisibility(View.VISIBLE);
                    sayilar.setVisibility(View.GONE);
                }
            }
        });

        LinearLayout mezunExtra = v.findViewById(R.id.mezun_expand);

        sinif.setOnButtonSelectedListener(new CompoundButtonGroup.OnButtonSelectedListener() {
            @Override
            public void onButtonSelected(int position, String value, boolean isChecked) {
                if(position == 1)
                    mezunExtra.setVisibility(View.VISIBLE);
                else
                    mezunExtra.setVisibility(GONE);
            }
        });


        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // UI

                if(newVal == 100) {
                    numberPicker2.setValue(0);
                    numberPicker2.setEnabled(false);
                }
                else
                    numberPicker2.setEnabled(true);
            }
        });


        return v;
    }

}
