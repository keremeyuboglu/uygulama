package com.example.acer.hayditrkiyeleri.Database;

import androidx.room.TypeConverter;


import com.example.acer.hayditrkiyeleri.Database.Entities.Deneme_ders;
import com.example.acer.hayditrkiyeleri.Database.Entities.Deneme_konu;
import com.example.acer.hayditrkiyeleri.Database.Entities.Stat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class Converters {


    /*@TypeConverter
    public Deneme_konuders[] ListToArray(ArrayList<Deneme_konuders> veriler){

        Deneme_konuders[] veriler_array;
        veriler_array=veriler.toArray(new Deneme_konuders[veriler.size()]);

        return veriler_array;
    }

    @TypeConverter
    public ArrayList<Deneme_konuders> ArrayToList(Deneme_konuders[] veriler_array){

        return (ArrayList<Deneme_konuders>) Arrays.asList(veriler_array);

    }*/

    @TypeConverter
    public String konuToString(ArrayList<Deneme_konu> veriler){
        Gson gson=new Gson();

        return gson.toJson(veriler);
    }

    @TypeConverter
    public ArrayList<Deneme_konu> StringToArraylist_konu(String json){

        Gson gson=new Gson();

        Type arrayListType= new TypeToken<ArrayList<Deneme_konu>>(){}.getType();
        return gson.fromJson(json, arrayListType);
    }



    @TypeConverter
    public String konudersToString_ders(ArrayList<Deneme_ders> veriler){
        Gson gson=new Gson();

        return gson.toJson(veriler);
    }

    @TypeConverter
    public ArrayList<Deneme_ders> StringToArraylist_ders(String json){

        Gson gson=new Gson();

        Type arrayListType= new TypeToken<ArrayList<Deneme_ders>>(){}.getType();
        return gson.fromJson(json, arrayListType);
    }

    @TypeConverter
    public ArrayList<Stat> StringToArrayList_stat(String json){

        Gson gson=new Gson();

        Type arrayListType= new TypeToken<ArrayList<Stat>>(){}.getType();
        return gson.fromJson(json, arrayListType);
    }

    @TypeConverter
    public String ArrayList_statToString(ArrayList<Stat> arrayList){
        Gson gson=new Gson();

        return gson.toJson(arrayList);
    }
}
