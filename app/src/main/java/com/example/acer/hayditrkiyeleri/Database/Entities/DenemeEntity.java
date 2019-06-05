package com.example.acer.hayditrkiyeleri.Database.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.ArrayList;

@Entity(tableName = "DENEME_TABLE")
public class DenemeEntity {

    @PrimaryKey
    @ColumnInfo(name = "deneme_id")
    public int deneme_id;



    private ArrayList<Deneme_konu> veriler_konu;
    private ArrayList<Deneme_ders> veriler_ders;

    private boolean isAyrintili;


    public int getDeneme_id() {
        return deneme_id;
    }

    public void setDeneme_id(int deneme_id) {
        this.deneme_id = deneme_id;
    }

    public ArrayList<Deneme_konu> getVeriler_konu() {
        return veriler_konu;
    }

    public void setVeriler_konu(ArrayList<Deneme_konu> veriler_konu) {
        this.veriler_konu = veriler_konu;
    }

    public ArrayList<Deneme_ders> getVeriler_ders() {
        return veriler_ders;
    }

    public void setVeriler_ders(ArrayList<Deneme_ders> veriler_ders) {
        this.veriler_ders = veriler_ders;
    }

    public boolean isAyrintili() {
        return isAyrintili;
    }

    public void setAyrintili(boolean ayrintili) {
        isAyrintili = ayrintili;
    }
}
