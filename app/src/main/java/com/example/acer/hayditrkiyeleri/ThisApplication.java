package com.example.acer.hayditrkiyeleri;

import android.app.Application;

import com.example.acer.hayditrkiyeleri.Database.AppDatabase;
import com.example.acer.hayditrkiyeleri.Database.DAO;
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
}
