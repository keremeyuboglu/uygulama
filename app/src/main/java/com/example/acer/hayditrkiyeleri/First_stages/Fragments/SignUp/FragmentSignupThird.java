package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
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
import com.example.acer.hayditrkiyeleri.TytDersler.KonuTakip;
import com.example.acer.hayditrkiyeleri.TytDersler.NetHesabi;
import com.example.acer.hayditrkiyeleri.TytDersler.TYTOgrenci;

import java.util.HashMap;

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

                TYTOgrenci tytOgrenci = new TYTOgrenci("MF","Mezun",87.87);
                double[][] turkcenetler =  {
                        {27,3},{24,5},{21,6},{17,8},{34,5}
                };

                NetHesabi netHesabi= new NetHesabi();
                double x = netHesabi.dersneti(5,turkcenetler);
                tytOgrenci.setTurkce(39);
                tytOgrenci.setMatematik(30.5);
                tytOgrenci.setFizik(4.25);
                tytOgrenci.setKimya(0);
                tytOgrenci.setBiyoloji(0);
                tytOgrenci.setCografya(5.75);
                tytOgrenci.setTarih(3.75);
                tytOgrenci.setFelsefe(1.75);
                tytOgrenci.setDin(1.5);

                Resources resources = getResources();
                String[] arrayTurkce = resources.getStringArray(R.array.turkce);
                HashMap<String,String> hashMapDersler = new HashMap<String,String>();

                for(int i = 0 ; i < arrayTurkce.length ; i++){
                    hashMapDersler.put(arrayTurkce[i],"Kırmızı");
                }



                KonuTakip konuTakip = new KonuTakip(tytOgrenci);
                tytOgrenci.setPuan(tytOgrenci.tytPuan());
                hashMapDersler = konuTakip.turkcetakip(hashMapDersler);
                hashMapDersler = konuTakip.matematiktakip(hashMapDersler);
                hashMapDersler = konuTakip.fiziktakip(hashMapDersler);
                hashMapDersler = konuTakip.kimyatakip(hashMapDersler);
                hashMapDersler = konuTakip.biyolojitakip(hashMapDersler);
                hashMapDersler = konuTakip.tarihtakip(hashMapDersler);
                hashMapDersler = konuTakip.cografyatakip(hashMapDersler);
                hashMapDersler = konuTakip.felsefetakip(hashMapDersler);
                hashMapDersler = konuTakip.dintakip(hashMapDersler);

                Log.d("Sad","Key is " + hashMapDersler.get("Sözcük Anlamı"));


                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.signupContainer,new FragmentSignupFourth());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }


        });



        return v;


    }
}
