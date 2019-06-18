package com.example.acer.hayditrkiyeleri.DersBilgileri;


import java.util.HashMap;
import java.util.LinkedHashMap;


public abstract class TYT_Bilgi {

    private static LinkedHashMap<String, Ders_Bilgi> ders_map=null;
    private static int ders_sayi = 10;

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


        Ders_Bilgi tar=new Ders_Bilgi();
        tar.setSoru_sayi(5);
        tar.add_konu("Osmanlı");
        tar.add_konu("Selçuk");
        tar.add_konu("T.C.");

        ders_map.put("Tarih", tar);

        Ders_Bilgi cog=new Ders_Bilgi();
        cog.setSoru_sayi(5);
        cog.add_konu("Dağ");
        cog.add_konu("Tepe");
        cog.add_konu("Deniz");

        ders_map.put("Coğrafya", cog);

        Ders_Bilgi fel=new Ders_Bilgi();
        fel.setSoru_sayi(5);
        fel.add_konu("Platon");
        fel.add_konu("Locke");
        fel.add_konu("Sadık");

        ders_map.put("Felsefe", fel);

        Ders_Bilgi din=new Ders_Bilgi();
        din.setSoru_sayi(5);
        din.add_konu("Allah");
        din.add_konu("Peygamber");
        din.add_konu("Uhud");

        ders_map.put("Din", din);

        Ders_Bilgi mat=new Ders_Bilgi();
        mat.setSoru_sayi(29);
        mat.add_konu("Toplama");
        mat.add_konu("Çıkarma");
        mat.add_konu("Bölme");

        ders_map.put("Matematik", mat);

        Ders_Bilgi geo=new Ders_Bilgi();
        geo.setSoru_sayi(11);
        geo.add_konu("Üçgen");
        geo.add_konu("Beşgen");
        geo.add_konu("Silindir");

        ders_map.put("Geometri", geo);


        Ders_Bilgi fiz=new Ders_Bilgi();
        fiz.setSoru_sayi(7);
        fiz.add_konu("Işık");
        fiz.add_konu("Dinamik");
        fiz.add_konu("Kaldırma");

        ders_map.put("Fizik", fiz);

        Ders_Bilgi kim=new Ders_Bilgi();
        kim.setSoru_sayi(7);
        kim.add_konu("Deterjan");
        kim.add_konu("Bağlar");
        kim.add_konu("Sabun");



        ders_map.put("Kimya", kim);

        Ders_Bilgi biy=new Ders_Bilgi();
        biy.setSoru_sayi(6);
        biy.add_konu("Hücre");
        biy.add_konu("Kalıtım");
        biy.add_konu("Zooloji");

        ders_map.put("Biyoloji", biy);

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
    public static int getDersSayisi(){
        return ders_sayi;
    }

}
