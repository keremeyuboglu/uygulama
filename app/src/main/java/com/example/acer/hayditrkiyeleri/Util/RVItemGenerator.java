package com.example.acer.hayditrkiyeleri.Util;

import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle2_inner;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle2_outer;

import java.util.ArrayList;

public abstract class RVItemGenerator {


    public static ArrayList<Item_DenemeEkle2_outer> pump_Item(){

        ArrayList<Item_DenemeEkle2_outer> result=new ArrayList<>();

        String[] dersisimleri={"Matematik", "Fizik", "Beden EĞİTİMİ"};

        String[] konuisimleri={"Ayşe", "Fatma", "Hayriye"};

        ArrayList<Item_DenemeEkle2_inner> temp_innerlist;
        Item_DenemeEkle2_inner temp_inner;
        Item_DenemeEkle2_outer temp_out;

        for(String dersisim: dersisimleri){
            temp_out=new Item_DenemeEkle2_outer();
            temp_out.setDers_isim(dersisim);
            temp_innerlist=new ArrayList<>();
            for(String konuisim: konuisimleri){
                temp_inner=new Item_DenemeEkle2_inner();
                temp_inner.setKonu_isim(konuisim);
                temp_innerlist.add(temp_inner);
            }

            temp_out.setInnerItems(temp_innerlist);
            result.add(temp_out);
        }


        return result;

    }
}
