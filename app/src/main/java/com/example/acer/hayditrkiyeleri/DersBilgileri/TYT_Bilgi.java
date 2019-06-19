package com.example.acer.hayditrkiyeleri.DersBilgileri;


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

        Ders_Bilgi tur=new Ders_Bilgi(40);
        tur.add_konu("Paragrafta Anlam", false); //Değerleri şuan kafama göre koyuyorum
        tur.add_konu("Cümlede anlam", false);
        tur.add_konu("Sözcükte anlam", false);

        ders_map.put("Türkçe", tur);


        Ders_Bilgi sos=new Ders_Bilgi(20);
        sos.add_konu("Dağ", true); //Değerleri şuan kafama göre koyuyorum
        sos.add_konu("Tepe", true);
        sos.add_konu("Deniz", false);

        ders_map.put("Sosyal", sos);


        Ders_Bilgi mat=new Ders_Bilgi(40);
        mat.add_konu("Toplama", true);
        mat.add_konu("Çıkarma", false);
        mat.add_konu("Bölme", false);

        ders_map.put("Matematik", mat);


        Ders_Bilgi fen=new Ders_Bilgi(20);
        fen.add_konu("Hücre", false);
        fen.add_konu("Araba", false);
        fen.add_konu("Sabun", true);

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
