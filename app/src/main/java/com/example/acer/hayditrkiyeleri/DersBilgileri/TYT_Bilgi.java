package com.example.acer.hayditrkiyeleri.DersBilgileri;


import java.util.HashMap;
import java.util.LinkedHashMap;


public abstract class TYT_Bilgi {

    private static LinkedHashMap<String, Ders_Bilgi> ders_map=null;

    private static void Init(){
/* Burda gereken bilgileri initialize ediyoruz
*
*   Bu bilgiler veritabanından da gelebilir. Böyle hardcoded da olabilir.
*   Sonuçta her sene belli olacak senede bir kere güncellenecek.
*
*
* */

        ders_map=new LinkedHashMap<>();

        Ders_Bilgi tur=new Ders_Bilgi();
        tur.setSoru_sayi(40);
        tur.add_konu("Paragrafta Anlam");
        tur.add_konu("Cümlede anlam");
        tur.add_konu("Sözcükte anlam");

        ders_map.put("Türkçe", tur);


        Ders_Bilgi sos=new Ders_Bilgi();
        sos.setSoru_sayi(20);
        sos.add_konu("Dağ");
        sos.add_konu("Tepe");
        sos.add_konu("Deniz");

        ders_map.put("Sosyal", sos);


        Ders_Bilgi mat=new Ders_Bilgi();
        mat.setSoru_sayi(40);
        mat.add_konu("Toplama");
        mat.add_konu("Çıkarma");
        mat.add_konu("Bölme");

        ders_map.put("Matematik", mat);


        Ders_Bilgi fen=new Ders_Bilgi();
        fen.setSoru_sayi(20);
        fen.add_konu("Hücre");
        fen.add_konu("Araba");
        fen.add_konu("Sabun");

        ders_map.put("Fen", fen);

    }

    public static Ders_Bilgi get_dersbilgi(String isim){
        if(ders_map==null){
            Init();
        }

        Ders_Bilgi result;

        try{
            result= ders_map.get(isim);
        }catch (Exception e){
            result=null; //If there is no key
        }

        return result;
    }

    public static LinkedHashMap<String, Ders_Bilgi> getDersmap(){
        if(ders_map==null){
            Init();
        }

        return ders_map;
    }

}
