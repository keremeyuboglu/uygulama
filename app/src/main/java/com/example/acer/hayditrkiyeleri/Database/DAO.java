package com.example.acer.hayditrkiyeleri.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.acer.hayditrkiyeleri.Database.Entities.AltBaslikEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.EsasVeriEntity;

import java.util.List;

@Dao
public interface DAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert_deneme(DenemeEntity deneme);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert_denemes(List<DenemeEntity> denemees);

    @Query("SELECT * FROM DENEME_TABLE WHERE deneme_id =:id")
    LiveData<DenemeEntity> retrieve_deneme(int id);

    @Query("SELECT COUNT(*) FROM DENEME_TABLE")
    int get_numofdeneme();

    @Query("SELECT * FROM DENEME_TABLE")
    LiveData<List<DenemeEntity>> get_denemelerLive();

    @Query("DELETE FROM DENEME_TABLE WHERE deneme_id =:id")
    void delete_itemById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert_esasVeri(EsasVeriEntity esasVeriEntity);

    @Query("SELECT * FROM ESASVERI_TABLE WHERE isim =:name")
    EsasVeriEntity get_esasVeri(String name);

    @Query("SELECT * FROM ALTBASLIK_TABLE WHERE konu_isim =:konuisim")
    List<AltBaslikEntity> get_altbasliksByKonuisim(String konuisim);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert_altbasliks(List<AltBaslikEntity> altBaslikEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Şimdilik çökmemesi için böyle durabilir
    void insert_altbaslik(AltBaslikEntity altBaslikEntity);
}
