package com.example.acer.hayditrkiyeleri.Util.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle1;

import java.util.ArrayList;

public class DenemeEkle1ViewModel extends ViewModel {

    private ArrayList<Item_DenemeEkle1> rv_items;

    public ArrayList<Item_DenemeEkle1> get_rvitems(){
        if(rv_items==null){
            String[] ders_isimleri={"Türkçe", "Sosyal", "Matematik", "Fen"};

            ArrayList<Item_DenemeEkle1> result=new ArrayList<>(4);
            Item_DenemeEkle1 temp_item;

            for(String isim: ders_isimleri){
                temp_item=new Item_DenemeEkle1();
                temp_item.setDers_isim(isim);

                result.add(temp_item);
            }

            result.trimToSize();
            rv_items=result;
        }


        return rv_items;
    }
}
