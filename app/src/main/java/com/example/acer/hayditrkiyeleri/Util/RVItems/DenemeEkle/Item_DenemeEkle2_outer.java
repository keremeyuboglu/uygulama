package com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Item_DenemeEkle2_outer implements Parcelable {

    private String ders_isim;
    private String ders_dogru="0";
    private String ders_yanlisbos="0";
    private ArrayList<Item_DenemeEkle2_inner> innerItems;


    public String getDers_isim() {
        return ders_isim;
    }

    public void setDers_isim(String ders_isim) {
        this.ders_isim = ders_isim;
    }

    public String getDers_dogru() {
        return ders_dogru;
    }

    public void setDers_dogru(String ders_dogru) {
        this.ders_dogru = ders_dogru;
    }

    public String getDers_yanlisbos() {
        return ders_yanlisbos;
    }

    public void setDers_yanlisbos(String ders_yanlisbos) {
        this.ders_yanlisbos = ders_yanlisbos;
    }

    public ArrayList<Item_DenemeEkle2_inner> getInnerItems() {
        return innerItems;
    }

    public void setInnerItems(ArrayList<Item_DenemeEkle2_inner> innerItems) {
        this.innerItems = innerItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
