package com.example.acer.hayditrkiyeleri.Util.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Repository;

import java.util.List;

public class DenemelerimViewModel extends ViewModel {

    private LiveData<List<DenemeEntity>> denemeler;
    private Repository myRepo;

    public void setMyRepo(Repository myRepo) {
        this.myRepo = myRepo;
    }

    public LiveData<List<DenemeEntity>> get_denemelerLive(){
        if(denemeler==null){
            denemeler=myRepo.get_denemelerLive();
        }

        return denemeler;
    }
}
