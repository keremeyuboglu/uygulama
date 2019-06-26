package com.example.acer.hayditrkiyeleri;

import java.security.PublicKey;
import java.util.ArrayList;

// Her bir recyclerview ögesini tutan sınıf
public class NavigationDrawerItem {

    String baslik;
    int resimID;

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public int getResimID() {
        return resimID;
    }

    public void setResimID(int resimID) {
        this.resimID = resimID;
    }

    public static ArrayList<NavigationDrawerItem> getData(){
        ArrayList<NavigationDrawerItem> dataList = new ArrayList<NavigationDrawerItem>();

        int[] resimler = {R.drawable.cam,R.drawable.fem,R.drawable.msg,R.drawable.upd,R.drawable.us,R.drawable.writ};
        String[] isimler = {"Programım","Konu Başlıkları","Denemelerim","Araç Gereç","Hesabım","Ayarlar"};

        for(int i =0;i <isimler.length ; i++){
            NavigationDrawerItem navigationDrawerItem = new NavigationDrawerItem();
            navigationDrawerItem.setBaslik(isimler[i]);
            navigationDrawerItem.setResimID(resimler[i]);
            dataList.add(navigationDrawerItem);
        }
        return dataList;
    }
}
