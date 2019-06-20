package com.example.acer.hayditrkiyeleri.Database.Entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ALTBASLIK_TABLE")
public class AltBaslikEntity {

    private String konu_isim;

    @PrimaryKey
    @NonNull
    private String altbaslik_isim;

    private boolean isBiliniyor=false;

    public AltBaslikEntity(String konu_isim, String altbaslik_isim) {
        this.konu_isim = konu_isim;
        this.altbaslik_isim = altbaslik_isim;
    }

    public boolean isBiliniyor() {
        return isBiliniyor;
    }

    public void setBiliniyor(boolean biliniyor) {
        isBiliniyor = biliniyor;
    }
}
