package com.example.acer.hayditrkiyeleri.TytDersler;

import java.util.LinkedHashMap;

public class KonuTakipTS {

    double Edebiyatnet, Tarih1net, Cografya1net, Tarih2net, Cografya2net, Felsefenet, Dinnet;

    public KonuTakipTS(AYTOgrenciTS aytOgrenciTS) {
        this.Edebiyatnet = aytOgrenciTS.getEdebiyat();
        this.Tarih1net = aytOgrenciTS.getTarih1();
        this.Cografya1net = aytOgrenciTS.getCografya1();
        this.Tarih2net = aytOgrenciTS.getTarih2();
        this.Cografya2net = aytOgrenciTS.getCografya2();
        this.Felsefenet = aytOgrenciTS.getFelsefe();
        this.Dinnet = aytOgrenciTS.getDin();
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

    public LinkedHashMap<String,String> cografya1Takip(LinkedHashMap<String, String> hashMap){

        if(this.Cografya1net <= 0){

        }else if(this.Cografya1net > 0 && this.Cografya1net < 3){

        }else if(this.Cografya1net >= 3 && this.Cografya1net < 6){

        }else if(this.Cografya1net >= 6 && this.Cografya1net < 9){

        }else {

        }

        return hashMap;


    }
    public LinkedHashMap<String,String> cografya2Takip(LinkedHashMap<String, String> hashMap){

        if(this.Cografya2net <= 0){

        }else if(this.Cografya2net > 0 && this.Cografya2net < 3){

        }else if(this.Cografya2net >= 3 && this.Cografya2net < 6){

        }else if(this.Cografya2net >= 6 && this.Cografya2net < 9){

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
    public LinkedHashMap<String,String> tarih2Takip(LinkedHashMap<String, String> hashMap){

        if(this.Tarih2net <= 0){

        }else if(this.Tarih2net > 0 && this.Tarih2net < 3){

        }else if(this.Tarih2net >= 3 && this.Tarih2net < 6){

        }else if(this.Tarih2net >= 6 && this.Tarih2net < 9){

        }else {

        }

        return hashMap;


    }
    public LinkedHashMap<String,String> felsefeTakip(LinkedHashMap<String, String> hashMap){

        if(this.Felsefenet <= 0){

        }else if(this.Felsefenet > 0 && this.Felsefenet < 3){

        }else if(this.Felsefenet >= 3 && this.Felsefenet < 6){

        }else if(this.Felsefenet >= 6 && this.Felsefenet < 9){

        }else {

        }

        return hashMap;


    }
    public LinkedHashMap<String,String> dinTakip(LinkedHashMap<String, String> hashMap){

        if(this.Dinnet <= 0){

        }else if(this.Dinnet > 0 && this.Dinnet < 3){

        }else if(this.Dinnet >= 3 && this.Dinnet < 6){

        }else if(this.Dinnet >= 6 && this.Dinnet < 9){

        }else {

        }

        return hashMap;


    }


}
