package com.example.acer.hayditrkiyeleri.Util.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.acer.hayditrkiyeleri.DersBilgileri.TYT_Bilgi;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle1;

import java.util.ArrayList;
import java.util.Set;

public class DenemeEkle1ViewModel extends ViewModel {

    private ArrayList<Item_DenemeEkle1> rv_items;

    public ArrayList<Item_DenemeEkle1> get_rvitems(){
        if(rv_items==null){
            Set<String> ders_isimleri= TYT_Bilgi.getDersmap().keySet();

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

    public void reset_items(){

        for(Item_DenemeEkle1 item: rv_items){


            item.setDogru(0);
            item.setYanlis(0);
        }


    }
}
