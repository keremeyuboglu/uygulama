package com.example.acer.hayditrkiyeleri.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;

import java.util.List;

@Dao
public interface DAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert_deneme(DenemeEntity deneme);

    @Query("SELECT * FROM DENEME_TABLE WHERE deneme_id =:id")
    LiveData<DenemeEntity> retrieve_deneme(int id);

    @Query("SELECT COUNT(*) FROM DENEME_TABLE")
    int get_numofdeneme();

    @Query("SELECT * FROM DENEME_TABLE")
    LiveData<List<DenemeEntity>> get_denemelerLive();

}
