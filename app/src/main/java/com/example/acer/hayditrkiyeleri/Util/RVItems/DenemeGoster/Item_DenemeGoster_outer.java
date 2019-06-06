package com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeGoster;

import java.util.ArrayList;

public class Item_DenemeGoster_outer {

    private String ders_isim;
    private String ders_dogru;
    private String ders_yanlis;

    private ArrayList<Item_DenemeGoster_inner> inner_items;


    public String getDers_dogru() {
        return ders_dogru;
    }

    public void setDers_dogru(String ders_dogru) {
        this.ders_dogru = ders_dogru;
    }

    public String getDers_yanlis() {
        return ders_yanlis;
    }

    public void setDers_yanlis(String ders_yanlis) {
        this.ders_yanlis = ders_yanlis;
    }

    public String getDers_isim() {
        return ders_isim;
    }

    public void setDers_isim(String ders_isim) {
        this.ders_isim = ders_isim;
    }

    public ArrayList<Item_DenemeGoster_inner> getInner_items() {
        return inner_items;
    }

    public void setInner_items(ArrayList<Item_DenemeGoster_inner> inner_items) {
        this.inner_items = inner_items;
    }
}
