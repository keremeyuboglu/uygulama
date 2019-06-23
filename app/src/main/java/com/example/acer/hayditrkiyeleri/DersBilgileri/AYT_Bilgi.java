package com.example.acer.hayditrkiyeleri.DersBilgileri;
import android.util.Log;

import java.util.LinkedHashMap;

import static android.content.ContentValues.TAG;

public class AYT_Bilgi {
    private static LinkedHashMap<String, Ders_Bilgi> ders_map=null;
    private static int ders_sayi = 4;

    private static void Init(){
        /* Burda gereken bilgileri initialize ediyoruz
         *
         *   Bu bilgiler veritabanından da gelebilir. Böyle hardcoded da olabilir.
         *   Sonuçta her sene belli olacak senede bir kere güncellenecek.
         *
         *
         * */
        String bolum = "MF"; // Değişecek
        ders_map=new LinkedHashMap<>();

        if(bolum == "MF"){   // MF
            Log.d(TAG, "Init: sdada");
            Ders_Bilgi mat_2=new Ders_Bilgi();
            mat_2.setSoru_sayi(40);
            mat_2.add_konu("Toplama");
            mat_2.add_konu("Çıkarma");
            mat_2.add_konu("Bölme");

            ders_map.put("Matematik-2", mat_2);

            Ders_Bilgi fiz_2=new Ders_Bilgi();
            fiz_2.setSoru_sayi(14);
            fiz_2.add_konu("Dinamik");
            fiz_2.add_konu("Kaldırma");

            ders_map.put("Fizik-2", fiz_2);

            Ders_Bilgi kim_2=new Ders_Bilgi();
            kim_2.setSoru_sayi(14);
            kim_2.add_konu("Deterjan");
            kim_2.add_konu("Bağlar");
            kim_2.add_konu("Sabun");

            ders_map.put("Kimya-2", kim_2);

            Ders_Bilgi biy_2=new Ders_Bilgi();
            biy_2.setSoru_sayi(13);
            biy_2.add_konu("Hücre");
            biy_2.add_konu("Kalıtım");
            biy_2.add_konu("Zooloji");

            ders_map.put("Biyoloji-2", biy_2);

        }
        else if(bolum == "TM"){ // TM
            Ders_Bilgi tur_ayt=new Ders_Bilgi();
            tur_ayt.setSoru_sayi(24);
            tur_ayt.add_konu("Divan");
            tur_ayt.add_konu("Namık Kemal");
            tur_ayt.add_konu("Sadık Başıböyük");

            ders_map.put("Türk Dili ve Edebiyatı", tur_ayt);


            Ders_Bilgi tar_1=new Ders_Bilgi();
            tar_1.setSoru_sayi(10);
            tar_1.add_konu("Osmanlı");
            tar_1.add_konu("Selçuk");
            tar_1.add_konu("T.C.");

            ders_map.put("Tarih-1", tar_1);

            Ders_Bilgi cog_1=new Ders_Bilgi();
            cog_1.setSoru_sayi(5);
            cog_1.add_konu("Dağ");
            cog_1.add_konu("Tepe");
            cog_1.add_konu("Deniz");

            ders_map.put("Coğrafya-1", cog_1);

            Ders_Bilgi mat_2=new Ders_Bilgi();
            mat_2.setSoru_sayi(40);
            mat_2.add_konu("Toplama");
            mat_2.add_konu("Çıkarma");
            mat_2.add_konu("Bölme");

            ders_map.put("Matematik-2", mat_2);

            Ders_Bilgi fiz=new Ders_Bilgi();
            fiz.setSoru_sayi(14);
            fiz.add_konu("Işık");
            fiz.add_konu("Dinamik");
            fiz.add_konu("Kaldırma");

            ders_map.put("Fizik", fiz);

            Ders_Bilgi kim=new Ders_Bilgi();
            kim.setSoru_sayi(14);
            kim.add_konu("Deterjan");
            kim.add_konu("Bağlar");
            kim.add_konu("Sabun");

            ders_map.put("Kimya", kim);

            Ders_Bilgi biy=new Ders_Bilgi();
            biy.setSoru_sayi(13);
            biy.add_konu("Hücre");
            biy.add_konu("Kalıtım");
            biy.add_konu("Zooloji");

            ders_map.put("Biyoloji", biy);
        }
        else if(bolum == "TS") {  // TS
            Ders_Bilgi tur_ayt=new Ders_Bilgi();
            tur_ayt.setSoru_sayi(24);
            tur_ayt.add_konu("Divan");
            tur_ayt.add_konu("Namık Kemal");
            tur_ayt.add_konu("Sadık Başıböyük");

            ders_map.put("Türk Dili ve Edebiyatı", tur_ayt);

            Ders_Bilgi tar_1=new Ders_Bilgi();
            tar_1.setSoru_sayi(10);
            tar_1.add_konu("Osmanlı");
            tar_1.add_konu("Selçuk");
            tar_1.add_konu("T.C.");

            ders_map.put("Tarih-1", tar_1);

            Ders_Bilgi cog_1=new Ders_Bilgi();
            cog_1.setSoru_sayi(5);
            cog_1.add_konu("Dağ");
            cog_1.add_konu("Tepe");
            cog_1.add_konu("Deniz");

            ders_map.put("Coğrafya-1", cog_1);


            Ders_Bilgi tar_2=new Ders_Bilgi();
            tar_2.setSoru_sayi(11);
            tar_2.add_konu("Osmanlı");
            tar_2.add_konu("Osmanlı");
            tar_2.add_konu("Osmanlı");

            ders_map.put("Tarih-2", tar_2);

            Ders_Bilgi cog_2=new Ders_Bilgi();
            cog_2.setSoru_sayi(11);
            cog_2.add_konu("Dağ2");
            cog_2.add_konu("Tepe2");
            cog_2.add_konu("Deniz2");

            ders_map.put("Coğrafya-2", cog_2);

            Ders_Bilgi fel_2=new Ders_Bilgi();
            fel_2.setSoru_sayi(12);
            fel_2.add_konu("Platon");
            fel_2.add_konu("Locke");
            fel_2.add_konu("Sadık");

            ders_map.put("Felsefe-2", fel_2);

            Ders_Bilgi din_2=new Ders_Bilgi();
            din_2.setSoru_sayi(6);
            din_2.add_konu("Allah");
            din_2.add_konu("Peygamber");
            din_2.add_konu("Uhud");

            ders_map.put("Din-2", din_2);
        }

        else {
            Ders_Bilgi ing=new Ders_Bilgi();
            ing.setSoru_sayi(80);
            ing.add_konu("Grammar");
            ing.add_konu("Vocab");
            ing.add_konu("Çeviri");

            ders_map.put("Din-2", ing);

        }

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
    public void setDersSayisi(){
        if(true) // MF
            ders_sayi = 4;
        else if(false) // TM
            ders_sayi = 4;
        else if(false) // TS
            ders_sayi = 7;
        else if(false)  // Dil
            ders_sayi = 1;
    }
    public static String[] getDersString(String bolum){
        String[] dersler;
        if(bolum == "MF"){
            dersler = new String[]{"Matematik-2","Fizik-2","Kimya-2","Biyoloji-2"};
        }
        else if(bolum == "TS"){
            dersler = new String[]{"Türk Dili ve Edebiyatı","Tarih-1","Coğrafya-1","Tarih-2","Coğrafya-2","Felsefe-2","Din-2"};

        }
        else if(bolum == "TM")
            {
            dersler = new String[]{"Matematik-2","Fizik-2","Kimya-2","Biyoloji-2","Türk Dili ve Edebiyatı","Tarih-1","Coğrafya-1"};
            }
        else
        {
            dersler = new String[]{"Ingilizce"};
        }
        return dersler;
    }
}