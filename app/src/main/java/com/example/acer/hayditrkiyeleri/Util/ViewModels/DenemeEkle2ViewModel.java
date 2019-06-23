package com.example.acer.hayditrkiyeleri.Util.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Repository;
import com.example.acer.hayditrkiyeleri.Util.RVItemGenerator;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle2_outer;

import java.util.ArrayList;
import java.util.List;

public class DenemeEkle2ViewModel extends ViewModel {

    private Repository myRepo;
    private ArrayList<Item_DenemeEkle2_outer> items; //Should turn this into Livedata
    private DenemeEntity deneme;

    public void set_repo(Repository myRepo) {
        this.myRepo = myRepo;
    }

    public ArrayList<Item_DenemeEkle2_outer> get_itemsLive(){

        if(items==null){
            items= RVItemGenerator.pump_Item();
        }

        return items;
    }

    public DenemeEntity getDeneme() {
        return deneme;
    }

    public void setDeneme(DenemeEntity deneme) {
        if(this.deneme==null)
            this.deneme = deneme;
    }

    public void insert_deneme(DenemeEntity deneme){
        myRepo.insert_deneme(deneme);
    }


}
