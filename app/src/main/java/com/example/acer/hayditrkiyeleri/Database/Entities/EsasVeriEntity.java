package com.example.acer.hayditrkiyeleri.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "ESASVERI_TABLE")
public class EsasVeriEntity {

    @NonNull
    @PrimaryKey
    public String isim;
    boolean isDers, isAz;
    private ArrayList<Stat> statlar=new ArrayList<>();


    public EsasVeriEntity(@NonNull String isim, boolean isDers, boolean isAz) {
        this.isim = isim;
        this.isDers = isDers;
        this.isAz = isAz;
    }



    public void add_stat(Stat stat){
        statlar.add(stat); //Sadece belli bir sayıda olması gerekiyor.
    }

    //Getters and Setters
    @NonNull
    public String getIsim() {
        return isim;
    }

    public ArrayList<Stat> getStatlar() {
        return statlar;
    }

    public void setStatlar(ArrayList<Stat> statlar) {
        this.statlar = statlar;
    }

    public boolean isDers() {
        return isDers;
    }

    public boolean isAz() {
        return isAz;
    }

}
