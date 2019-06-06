package com.example.acer.hayditrkiyeleri.Database;

import androidx.lifecycle.LiveData;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;

import java.util.List;

public class Repository {


    private DAO dao;

    public void setDao(DAO dao) {
        this.dao = dao;
    }


    public void insert_deneme(DenemeEntity deneme){
        dao.insert_deneme(deneme);
    }

    public LiveData<DenemeEntity> get_denemeLive(int deneme_id){
        return dao.retrieve_deneme(deneme_id);
    }


    public LiveData<List<DenemeEntity>> get_denemelerLive(){
        return dao.get_denemelerLive();
    }
}
