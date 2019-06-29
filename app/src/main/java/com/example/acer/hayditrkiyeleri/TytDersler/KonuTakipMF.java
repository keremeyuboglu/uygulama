package com.example.acer.hayditrkiyeleri.TytDersler;

import android.util.Log;

import java.util.LinkedHashMap;

public class KonuTakipMF {

    double Matematiknet,Fiziknet,Kimyanet,Biyolojinet;

    public KonuTakipMF(AYTOgrenciMF aytOgrenciMF) {
        this.Matematiknet = aytOgrenciMF.getMatematik();
        this.Fiziknet = aytOgrenciMF.getFizik();
        this.Kimyanet = aytOgrenciMF.getKimya();
        this.Biyolojinet = aytOgrenciMF.getBiyoloji();
    }
    public LinkedHashMap<String, String> matematikTakip(LinkedHashMap<String, String> hashMap){


        if(this.Matematiknet > 0 && this.Matematiknet < 8){



        }else if(this.Matematiknet >= 8 && this.Matematiknet < 16){
            hashMap.put("Yapı","Yeşil");
            hashMap.put("Niteliği","Yeşil");
            hashMap.put("Sonuçları","Yeşil");
            hashMap.put("Devletleri","Sarı");

        }else if(this.Matematiknet >= 16 && this.Matematiknet < 24){


        }else if(this.Matematiknet >= 24 && this.Matematiknet < 32){




        }else if(this.Matematiknet >= 32){

        }
        return hashMap;
    }
    public LinkedHashMap<String,String> fizikTakip(LinkedHashMap<String, String> hashMap){

        if(this.Fiziknet <= 0){

        }else if(this.Fiziknet > 0 && this.Fiziknet < 3){

        }else if(this.Fiziknet >= 3 && this.Fiziknet < 6){

        }else if(this.Fiziknet >= 6 && this.Fiziknet < 9){

        }else {

        }

        return hashMap;


    }
    public LinkedHashMap<String,String> kimyaTakip(LinkedHashMap<String, String> hashMap){

        if(this.Kimyanet <= 0){

        }else if(this.Kimyanet > 0 && this.Kimyanet < 3){

        }else if(this.Kimyanet >= 3 && this.Kimyanet < 6){

        }else if(this.Kimyanet >= 6 && this.Kimyanet < 9){

        }else {

        }

        return hashMap;


    }
    public LinkedHashMap<String,String> biyolojiTakip(LinkedHashMap<String, String> hashMap){

        if(this.Biyolojinet <= 0){

        }else if(this.Biyolojinet > 0 && this.Biyolojinet < 3){

        }else if(this.Biyolojinet >= 3 && this.Biyolojinet < 6){

        }else if(this.Biyolojinet >= 6 && this.Biyolojinet < 9){

        }else {

        }

        return hashMap;


    }
}
