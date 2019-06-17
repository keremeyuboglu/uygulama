package com.example.acer.hayditrkiyeleri.DersBilgileri;

import java.util.ArrayList;

public class Ders_Bilgi {

    private int soru_sayi;
    private ArrayList<String> konu_isimler=new ArrayList<>();

    public int getSoru_sayi() {
        return soru_sayi;
    }

    public void setSoru_sayi(int soru_sayi) {
        this.soru_sayi = soru_sayi;
    }

    public ArrayList<String> getKonu_isimler() {
        return konu_isimler;
    }

    public void setKonu_isimler(ArrayList<String> konu_isimler) {
        this.konu_isimler = konu_isimler;
    }

    public void add_konu(String isim){
        konu_isimler.add(isim);
        konu_isimler.trimToSize();
    }
}
