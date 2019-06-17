package com.example.acer.hayditrkiyeleri.Database.Entities;

public class Stat {

    //Deneme ismi ve idsi de kayıtlı olacak

    private int toplam_soru;
    private int yanlis;

    public Stat(int toplam_soru, int yanlis) {
        this.toplam_soru = toplam_soru;
        this.yanlis = yanlis;
    }

    //Getters and Setters
    public int getToplam_soru() {
        return toplam_soru;
    }

    public void setToplam_soru(int toplam_soru) {
        this.toplam_soru = toplam_soru;
    }

    public double getYanlis() {
        return yanlis;
    }

    public void setYanlis(int yanlis) {
        this.yanlis = yanlis;
    }

    public double getDogru(){return toplam_soru-yanlis;}
}
