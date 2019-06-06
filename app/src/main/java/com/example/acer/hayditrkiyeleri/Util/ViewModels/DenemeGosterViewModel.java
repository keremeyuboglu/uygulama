package com.example.acer.hayditrkiyeleri.Util.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Repository;

public class DenemeGosterViewModel extends ViewModel {

    private LiveData<DenemeEntity> deneme=null;
    private Repository myRepo;

    public void setMyRepo(Repository myRepo) {
        this.myRepo = myRepo;
    }

    public LiveData<DenemeEntity> get_denemeLive(int deneme_id){
        if(deneme==null){
            deneme= myRepo.get_denemeLive(deneme_id);
        }

        return deneme;
    }


}
