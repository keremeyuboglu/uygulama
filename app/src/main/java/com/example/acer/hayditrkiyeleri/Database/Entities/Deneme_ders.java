package com.example.acer.hayditrkiyeleri.Database.Entities;

public class Deneme_ders {

    private String ders_isim;

    private int ders_dogru;
    private int ders_yanlis;


    public String getDers_isim() {
        return ders_isim;
    }

    public void setDers_isim(String ders_isim) {
        this.ders_isim = ders_isim;
    }

    public int getDers_dogru() {
        return ders_dogru;
    }

    public void setDers_dogru(int ders_dogru) {
        this.ders_dogru = ders_dogru;
    }

    public int getDers_yanlis() {
        return ders_yanlis;
    }

    public void setDers_yanlis(int ders_yanlis) {
        this.ders_yanlis = ders_yanlis;
    }
}
