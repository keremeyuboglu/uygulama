package com.example.acer.hayditrkiyeleri.Sadiginsikininkeyfi;

import android.util.Log;

import com.example.acer.hayditrkiyeleri.Database.Entities.EsasVeriEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.Stat;

import java.util.ArrayList;

public abstract class Hesaplayici {

    public static void hadi_bakalim(EsasVeriEntity esasVeriEntity){

        double ders_sonuc=-64564641;
        ArrayList<Stat> gecmis= esasVeriEntity.getStatlar();

        if(esasVeriEntity.isDers()){

            ders_sonuc=NetHesabi.dersneti(gecmis.size(), gecmis);
        }

        Log.i("sadiginsikininkeyfi", esasVeriEntity.getIsim()+ders_sonuc);



    }
}
