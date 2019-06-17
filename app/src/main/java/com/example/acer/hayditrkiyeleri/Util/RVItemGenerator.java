package com.example.acer.hayditrkiyeleri.Util;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.Deneme_ders;
import com.example.acer.hayditrkiyeleri.Database.Entities.Deneme_konu;
import com.example.acer.hayditrkiyeleri.DersBilgileri.Ders_Bilgi;
import com.example.acer.hayditrkiyeleri.DersBilgileri.TYT_Bilgi;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle2_inner;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle2_outer;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeGoster.Item_DenemeGoster_inner;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeGoster.Item_DenemeGoster_outer;
import com.example.acer.hayditrkiyeleri.Util.RVItems.Denemelerim.Item_Denemelerim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public abstract class RVItemGenerator {




    public static ArrayList<Item_DenemeEkle2_outer> pump_Item(){

        ArrayList<Item_DenemeEkle2_outer> result=new ArrayList<>();

        final LinkedHashMap<String, Ders_Bilgi> ders_map= TYT_Bilgi.getDersmap();

        Set<String> dersisimleri=ders_map.keySet();

        ArrayList<String> konuisimleri;

        ArrayList<Item_DenemeEkle2_inner> temp_innerlist;
        Item_DenemeEkle2_inner temp_inner;
        Item_DenemeEkle2_outer temp_out;

        for(String dersisim: dersisimleri){
            temp_out=new Item_DenemeEkle2_outer();
            temp_out.setDers_isim(dersisim);
            temp_innerlist=new ArrayList<>();

            konuisimleri= ders_map.get(dersisim).getKonu_isimler();
            for(String konuisim: konuisimleri){
                temp_inner=new Item_DenemeEkle2_inner();
                temp_inner.setKonu_isim(konuisim);
                temp_innerlist.add(temp_inner);
            }

            temp_out.setInnerItems(temp_innerlist);
            result.add(temp_out);
        }

        result.trimToSize();
        return result;

    }

    public static ArrayList<Item_DenemeGoster_outer> pump_ItemGoster(DenemeEntity deneme){

        final int huge_number=100;

        ArrayList<Item_DenemeGoster_outer> result=new ArrayList<>();

        Item_DenemeGoster_outer temp_outer=null;
        ArrayList<Item_DenemeGoster_inner> temp_inner_list=null;
        Item_DenemeGoster_inner temp_inner;
        String temp_name;


        ArrayList<Deneme_ders> veriler_ders=deneme.getVeriler_ders();
        ArrayList<Deneme_konu> veriler_konu=deneme.getVeriler_konu();


        Deneme_ders temp_ders;
        Deneme_konu temp_konu;

        if(deneme.isAyrintili()){

            int ite=0;
            int end=veriler_ders.size();

            int ite2=0;
            int end2=veriler_konu.size();

            do{
                temp_outer=new Item_DenemeGoster_outer();

                temp_ders=veriler_ders.get(ite++);

                temp_name=temp_ders.getDers_isim();

                temp_outer.setDers_isim(temp_name);
                temp_outer.setDers_dogru(String.valueOf(temp_ders.getDers_dogru()));
                temp_outer.setDers_yanlis(String.valueOf(temp_ders.getDers_yanlis()));

                temp_inner_list=new ArrayList<>(huge_number);

                do{


                    temp_konu=veriler_konu.get(ite2++);

                    if(!temp_konu.getDers_isim().equals(temp_name)){
                        ite2--;
                        break;
                    }


                    temp_inner=new Item_DenemeGoster_inner();
                    temp_inner.setKonu_isim(temp_konu.getKonu_isim());
                    temp_inner.setKonu_dogru(String.valueOf(temp_konu.getKonu_dogru()));
                    temp_inner.setKonu_yanlis(String.valueOf(temp_konu.getKonu_yanlis()));

                    temp_inner_list.add(temp_inner);
                }while(ite2<end2);


                temp_inner_list.trimToSize();
                temp_outer.setInner_items(temp_inner_list);
                result.add(temp_outer);
            }while(ite<end);




        }else{

            for(Deneme_ders veri: veriler_ders){
                temp_outer=new Item_DenemeGoster_outer();
                temp_outer.setDers_isim(veri.getDers_isim()); //Set ders isim
                temp_outer.setDers_dogru(String.valueOf(veri.getDers_dogru())); //Set ders doğru
                temp_outer.setDers_yanlis(String.valueOf(veri.getDers_yanlis())); //Set ders yanlış
                temp_outer.setInner_items(null); //There is no innerlist item so assigning null

                result.add(temp_outer); //Add this to result

            }

        }

        result.trimToSize();
        return result;
    }

    public static ArrayList<Item_Denemelerim> pump_Item_denemelerim(List<DenemeEntity> denemeler){

        ArrayList<Item_Denemelerim> result=new ArrayList<>();
        Item_Denemelerim temp_item;

        int temp_id;

        for(DenemeEntity deneme: denemeler){
            temp_item=new Item_Denemelerim();

            temp_id=deneme.getDeneme_id();
            temp_item.setDeneme_id(temp_id);


            temp_item.setDeneme_isim("Deneme "+temp_id);

            result.add(temp_item);
        }

        result.trimToSize();
        return result;

    }


}
