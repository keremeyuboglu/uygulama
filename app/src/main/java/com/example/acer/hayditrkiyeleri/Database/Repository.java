package com.example.acer.hayditrkiyeleri.Database;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.EsasVeriEntity;

import java.util.List;

public class Repository {


    private DAO dao;

    public void setDao(DAO dao) {
        this.dao = dao;
    }


    public void insert_deneme(DenemeEntity deneme){
        dao.insert_deneme(deneme);
    }

    public void insert_denemes(List<DenemeEntity> denemes){ dao.insert_denemes(denemes);}

    public LiveData<DenemeEntity> get_denemeLive(int deneme_id){
        return dao.retrieve_deneme(deneme_id);
    }


    public LiveData<List<DenemeEntity>> get_denemelerLive(){
        return dao.get_denemelerLive();
    }

    public void delete_itemById(int deneme_id) {
        dao.delete_itemById(deneme_id);
    }

    public EsasVeriEntity get_esasVeri(@NonNull String name){
        return dao.get_esasVeri(name);
    }

    public void insert_esasVeri(EsasVeriEntity esasVeriEntity){
        dao.insert_esasVeri(esasVeriEntity);
    }
}
