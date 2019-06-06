package com.example.acer.hayditrkiyeleri.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;


@Database(entities = {DenemeEntity.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract DAO getDAO();

    private static volatile AppDatabase INSTANCE=null;

    public static synchronized AppDatabase getINSTANCE(Context context){
        if(INSTANCE == null){
            INSTANCE= Room.databaseBuilder(context, AppDatabase.class, "DENEME.db").build();
        }

        return INSTANCE;
    }
}
