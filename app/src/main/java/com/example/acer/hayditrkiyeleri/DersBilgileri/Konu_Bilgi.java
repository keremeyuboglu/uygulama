package com.example.acer.hayditrkiyeleri.DersBilgileri;

public class Konu_Bilgi {


    private final String konu_isim;
    private final boolean is2denaz;

    public Konu_Bilgi(String konu_isim, boolean is2denaz) {
        this.konu_isim = konu_isim;
        this.is2denaz = is2denaz;
    }

    //Getters and Setters
    public String getKonu_isim() {
        return konu_isim;
    }

    public boolean isIs2denaz() {
        return is2denaz;
    }
}
