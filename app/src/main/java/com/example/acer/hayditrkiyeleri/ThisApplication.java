package com.example.acer.hayditrkiyeleri;

import android.app.Application;
import android.content.res.Resources;

import com.example.acer.hayditrkiyeleri.Database.AppDatabase;
import com.example.acer.hayditrkiyeleri.Database.DAO;
import com.example.acer.hayditrkiyeleri.Database.Entities.AltBaslikEntity;
import com.example.acer.hayditrkiyeleri.Util.MyTask;

public class ThisApplication extends Application {

    private static AppDatabase appDatabase;
    private DAO dao;

    /*There will be general information cached*/
    private int numofdeneme;

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase=AppDatabase.getINSTANCE(this);
        dao=appDatabase.getDAO();

        MyTask task=new MyTask(()->{
           numofdeneme=dao.get_numofdeneme();
        });

        task.execute();
    }


    public DAO get_dao(){
        return dao;
    }

    public int get_numberofdeneme(){

        return numofdeneme++;
    }

    public void initialize_altbasliklar(){

        //Eğer adam bir telefonda iki hesap kullanacaksa sıkıntı

        MyTask task=new MyTask(()->{

            Resources resources= getResources();

            String[] altbaslik_array= resources.getStringArray(R.array.altbasliklar);
            int len=altbaslik_array.length;

            String temp_konuisim=altbaslik_array[0];
            String temp_item;


            AltBaslikEntity temp_altbaslik;

            for(int i=1; i<len; i++){

                temp_item=altbaslik_array[i];
                if(temp_item.equals("Bitti")){
                    temp_konuisim=altbaslik_array[(i++)+1];
                    continue;
                }

                temp_altbaslik=new AltBaslikEntity(temp_konuisim, temp_item);
                dao.insert_altbaslik(temp_altbaslik);

            }
        });

        task.execute();

    }


}
