package com.example.acer.hayditrkiyeleri.TytDersler;

import java.util.LinkedHashMap;

public class KonuTakipTM {

    double Matematiknet, Edebiyatnet, Tarih1net,Cografya1net;
    public KonuTakipTM(AYTOgrenciTM aytOgrenciTM) {
        this.Matematiknet = aytOgrenciTM.getMatematik();
        this.Edebiyatnet = aytOgrenciTM.getEdebiyat();
        this.Tarih1net = aytOgrenciTM.getTarih1();
        this.Cografya1net = aytOgrenciTM.getCografya1();
    }
    public LinkedHashMap<String,String> matematikTakip(LinkedHashMap<String, String> hashMap){

        if(this.Matematiknet <= 0){

        }else if(this.Matematiknet > 0 && this.Matematiknet < 3){

        }else if(this.Matematiknet >= 3 && this.Matematiknet < 6){

        }else if(this.Matematiknet >= 6 && this.Matematiknet < 9){

        }else {

        }

        return hashMap;


    }
    public LinkedHashMap<String,String> tarih1Takip(LinkedHashMap<String, String> hashMap){

        if(this.Tarih1net <= 0){

        }else if(this.Tarih1net > 0 && this.Tarih1net < 3){

        }else if(this.Tarih1net >= 3 && this.Tarih1net < 6){

        }else if(this.Tarih1net >= 6 && this.Tarih1net < 9){

        }else {

        }

        return hashMap;


    }
    public LinkedHashMap<String,String> cografya1Takip(LinkedHashMap<String, String> hashMap){

        if(this.Cografya1net <= 0){

        }else if(this.Cografya1net > 0 && this.Cografya1net < 3){

        }else if(this.Cografya1net >= 3 && this.Cografya1net < 6){

        }else if(this.Cografya1net >= 6 && this.Cografya1net < 9){

        }else {

        }

        return hashMap;


    }
    public LinkedHashMap<String,String> edebiyatTakip(LinkedHashMap<String, String> hashMap){

        if(this.Edebiyatnet <= 0){

        }else if(this.Edebiyatnet > 0 && this.Edebiyatnet < 3){

        }else if(this.Edebiyatnet >= 3 && this.Edebiyatnet < 6){

        }else if(this.Edebiyatnet >= 6 && this.Edebiyatnet < 9){

        }else {

        }

        return hashMap;


    }
}
