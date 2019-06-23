package com.example.acer.hayditrkiyeleri.Util.ViewModels;

import androidx.lifecycle.MutableLiveData;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.EsasVeriEntity;
import com.example.acer.hayditrkiyeleri.Database.Repository;

import java.util.ArrayList;
import java.util.List;

public class SignUpThirdViewModel extends DenemelerimViewModel {

    private MutableLiveData<ArrayList<DenemeEntity>> items;
    private Repository myRepo;
    private int number;

    public void setMyRepo(Repository myRepo) {
        this.myRepo = myRepo;
    }

    public MutableLiveData<ArrayList<DenemeEntity>> getItems(){
        if(items==null){
            items=new MutableLiveData<>();
        }

        return items;
    }

    public void insert_items(List<DenemeEntity> denemeEntities){
        myRepo.insert_denemes(denemeEntities);
    }

    public void delete_itemById(int deneme_id){
        myRepo.delete_itemById(deneme_id);
    }

    public void insert_esasVeri(EsasVeriEntity esasVeriEntity){
        myRepo.insert_esasVeri(esasVeriEntity);
    }

}
