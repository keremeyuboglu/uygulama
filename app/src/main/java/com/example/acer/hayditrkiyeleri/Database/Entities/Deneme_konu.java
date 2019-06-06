package com.example.acer.hayditrkiyeleri.Database.Entities;

public class Deneme_konu {

    private String konu_isim;
    private String ders_isim;

    private int konu_dogru;
    private int konu_yanlis;


    public String getKonu_isim() {
        return konu_isim;
    }

    public void setKonu_isim(String konu_isim) {
        this.konu_isim = konu_isim;
    }

    public String getDers_isim() {
        return ders_isim;
    }

    public void setDers_isim(String ders_isim) {
        this.ders_isim = ders_isim;
    }

    public int getKonu_dogru() {
        return konu_dogru;
    }

    public void setKonu_dogru(int konu_dogru) {
        this.konu_dogru = konu_dogru;
    }

    public int getKonu_yanlis() {
        return konu_yanlis;
    }

    public void setKonu_yanlis(int konu_yanlis) {
        this.konu_yanlis = konu_yanlis;
    }
}
