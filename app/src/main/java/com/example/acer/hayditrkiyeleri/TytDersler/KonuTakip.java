package com.example.acer.hayditrkiyeleri.TytDersler;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.acer.hayditrkiyeleri.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

public class KonuTakip {

    double turkcenet,matematiknet,biyolojinet,fiziknet,kimyanet,tarihnet,
            cografyanet,felsefenet,dinnet;


    public KonuTakip(TYTOgrenci tytOgrenci) {
        this.turkcenet = tytOgrenci.getTurkce();
        this.matematiknet = tytOgrenci.getMatematik();
        this.biyolojinet = tytOgrenci.getBiyoloji();
        this.fiziknet = tytOgrenci.getFizik();
        this.kimyanet = tytOgrenci.getKimya();
        this.tarihnet = tytOgrenci.getTarih();
        this.cografyanet = tytOgrenci.getCografya();
        this.felsefenet = tytOgrenci.getFelsefe();
        this.dinnet = tytOgrenci.getDin();
    }

    public LinkedHashMap<String, String> turkcetakip(LinkedHashMap<String, String> hashMap){


        if(this.turkcenet > 0 && this.turkcenet < 8){

            hashMap.put("Zarf","Sarı");
            hashMap.put("Sıfat","Sarı");


        }else if(this.turkcenet >= 8 && this.turkcenet < 16){

        }else if(this.turkcenet >= 16 && this.turkcenet < 24){

            hashMap.put("Sözcük Anlamı","Yeşil");
            hashMap.put("Yazım Kuralları","Turuncu");
            hashMap.put("Söz Yorumu","Turuncu");
            hashMap.put("Noktalama İşaretleri","Turuncu");
            hashMap.put("Sözcüğün Yapısı","Turuncu");
            hashMap.put("Cümle Anlamı","Sarı");
            hashMap.put("Sözcük Türleri","Sarı");
            hashMap.put("Cümle Yorumu","Sarı");
            hashMap.put("Sözcük Grupları","Sarı");
            hashMap.put("Cümlenin Ögeleri","Sarı");


        }else if(this.turkcenet >= 24 && this.turkcenet < 32){

            hashMap.put("Sözcük Anlamı","Yeşil");
            hashMap.put("Yazım Kuralları","Yeşil");
            hashMap.put("Söz Yorumu","Yeşil");
            hashMap.put("Noktalama İşaretleri","Yeşil");
            hashMap.put("Sözcüğün Yapısı","Yeşil");
            hashMap.put("Cümle Anlamı","Sarı");
            hashMap.put("Sözcük Türleri","Sarı");
            hashMap.put("Cümle Yorumu","Sarı");
            hashMap.put("Sözcük Grupları","Sarı");
            hashMap.put("Cümlenin Ögeleri","Sarı");



        }else if(this.turkcenet >= 32){
            hashMap.put("Sözcük Anlamı","Yeşil");
            hashMap.put("Yazım Kuralları","Yeşil");
            hashMap.put("Söz Yorumu","Yeşil");
            hashMap.put("Noktalama İşaretleri","Yeşil");
            hashMap.put("Sözcüğün Yapısı","Yeşil");
            hashMap.put("Cümle Anlamı","Yeşil");
            hashMap.put("Sözcük Türleri","Yeşil");
            hashMap.put("Cümle Yorumu","Yeşil");
            hashMap.put("Sözcük Grupları","Yeşil");
            hashMap.put("Cümlenin Ögeleri","Yeşil");
            hashMap.put("Paragrafta Yapı","Yeşil");
            hashMap.put("Cümle Türleri","Yeşil");
            hashMap.put("Anlatım Bozukluğu","Yeşil");
            hashMap.put("Ses Bilgisi","Yeşil");
            hashMap.put("Deyim ve Atasözü","Yeşil");
            hashMap.put("Paragrafta Anlatım Tekniknleri","Yeşil");
            hashMap.put("Paragrafta Konu-Ana Düşünce","Yeşil");
            hashMap.put("Paragrafta Yardımcı Düşünce","Yeşil");
            hashMap.put("Fiiller","Yeşil");

            /*Iterator it = hashMap.entrySet().iterator();
            HashMap.Entry keyValue;
            while (it.hasNext()) {
                keyValue = (HashMap.Entry)it.next();
                keyValue.setValue("Yeşil");
                Log.d("Sad","Key" + keyValue.getKey());
            }*/


        }
        return hashMap;
    }

    public LinkedHashMap<String,String> matematiktakip(LinkedHashMap<String, String> hashMap){
        if(this.matematiknet <= 0){

        }else if(this.matematiknet > 0 && this.matematiknet < 6){

        }else if(this.matematiknet >= 6 && this.matematiknet < 12){

        }else if(this.matematiknet >= 12 && this.matematiknet < 28){

        }else if(this.matematiknet >= 18 && this.matematiknet < 24){

        }else {

        }
        return hashMap;

    }



    public LinkedHashMap<String,String> fiziktakip(LinkedHashMap<String, String> hashMap){

        if(this.fiziknet <= 0){

        }else if(this.fiziknet > 0 && this.fiziknet < 3){

        }else if(this.fiziknet >= 3 && this.fiziknet < 6){

        }else if(this.fiziknet >= 6 && this.fiziknet < 9){

        }else {

        }

        return hashMap;


    }

    public LinkedHashMap<String,String> kimyatakip(LinkedHashMap<String, String> hashMap){

        if(this.kimyanet <= 0){

        }else if(this.kimyanet > 0 && this.kimyanet < 3){

        }else if(this.kimyanet >= 3 && this.kimyanet < 6){

        }else if(this.kimyanet >= 6 && this.kimyanet < 9){

        }else {

        }
        return hashMap;

    }

    public LinkedHashMap<String,String> biyolojitakip(LinkedHashMap<String, String> hashMap){

        if(this.biyolojinet <= 0){

        }else if(this.biyolojinet > 0 && this.biyolojinet < 3){

        }else if(this.biyolojinet >= 3 && this.biyolojinet < 6){

        }else if(this.biyolojinet >= 6 && this.biyolojinet < 9){

        }else {

        }
        return hashMap;

    }

    public LinkedHashMap<String,String> tarihtakip(LinkedHashMap<String, String> hashMap){

        if(this.tarihnet <= 0){

        }else if(this.tarihnet > 0 && this.tarihnet < 3){

        }else if(this.tarihnet >= 3 && this.tarihnet < 6){

        }else if(this.tarihnet >= 6 && this.tarihnet < 9){

        }else {

        }
        return hashMap;

    }

    public LinkedHashMap<String,String> cografyatakip(LinkedHashMap<String, String> hashMap){

        if(this.cografyanet <= 0){

        }else if(this.cografyanet > 0 && this.cografyanet < 3){

        }else if(this.cografyanet >= 3 && this.cografyanet < 6){

        }else if(this.cografyanet >= 6 && this.cografyanet < 9){

        }else {

        }
        return hashMap;

    }

    public LinkedHashMap<String,String> felsefetakip(LinkedHashMap<String, String> hashMap){

        if(this.felsefenet <= 0){

        }else if(this.felsefenet > 0 && this.felsefenet < 3){

        }else if(this.felsefenet >= 3 && this.felsefenet < 6){

        }else if(this.felsefenet >= 6 && this.felsefenet < 9){

        }else {

        }
        return hashMap;

    }

    public LinkedHashMap<String,String> dintakip(LinkedHashMap<String, String> hashMap){

        if(this.dinnet <= 0){

        }else if(this.dinnet > 0 && this.dinnet < 3){

        }else if(this.dinnet >= 3 && this.dinnet < 6){

        }else if(this.dinnet >= 6 && this.dinnet < 9){

        }else {

        }
        return hashMap;

    }


}