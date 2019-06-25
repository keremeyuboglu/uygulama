package com.example.acer.hayditrkiyeleri.Util.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.acer.hayditrkiyeleri.Database.Entities.AltBaslikEntity;
import com.example.acer.hayditrkiyeleri.Database.Repository;

import java.util.List;

public class SignUpFourthViewModel extends ViewModel {

    private Repository myRepo;

    public void setMyRepo(Repository myRepo) {
        this.myRepo = myRepo;
    }

    public List<AltBaslikEntity> get_altBasliksByKonuisim(String konuisim){
        return myRepo.getAltbasliksbyKonuisim(konuisim);
    }

    public void insert_altbasliks(List<AltBaslikEntity> altBaslikEntities){
        myRepo.insert_Altbaslikentities(altBaslikEntities);
    }
}
