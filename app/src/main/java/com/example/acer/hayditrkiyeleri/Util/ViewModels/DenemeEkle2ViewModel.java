package com.example.acer.hayditrkiyeleri.Util.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle2_outer;

import java.util.ArrayList;

public class DenemeEkle2ViewModel extends ViewModel {

    ArrayList<Item_DenemeEkle2_outer> items=null;

    public ArrayList<Item_DenemeEkle2_outer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item_DenemeEkle2_outer> items) {
        this.items=items;

    }
}
