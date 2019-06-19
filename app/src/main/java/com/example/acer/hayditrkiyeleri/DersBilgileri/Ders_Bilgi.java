package com.example.acer.hayditrkiyeleri.DersBilgileri;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Ders_Bilgi {

    private int soru_sayi;
    private int konu_sayi=0;

    //Konu adı, 2 den az mı
    private LinkedHashMap<String, Boolean> konu_bilgiMap;


    public Ders_Bilgi(int soru_sayi) {
        this.soru_sayi = soru_sayi;
        konu_bilgiMap=new LinkedHashMap<>();
    }

    public void add_konu(String konu_ismi, boolean is2denaz){
        konu_bilgiMap.put(konu_ismi, is2denaz);
        konu_sayi++;
    }

    public final LinkedHashMap<String, Boolean> get_konubilgimap(){
        return konu_bilgiMap;
    }

    public int getSoru_sayi() {
        return soru_sayi;
    }

    public boolean get_is2denaz(String name){
        return konu_bilgiMap.get(name);
    }

    public int getKonu_sayi() {
        return konu_sayi;
    }
}
