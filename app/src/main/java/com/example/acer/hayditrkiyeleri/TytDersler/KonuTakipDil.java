package com.example.acer.hayditrkiyeleri.TytDersler;

import java.util.LinkedHashMap;

public class KonuTakipDil {
    double Dilnet;

    public KonuTakipDil(AYTOgrenciDil aytOgrenciDil) {
        this.Dilnet = aytOgrenciDil.getDil();

    }
    public LinkedHashMap<String,String> dilTakip(LinkedHashMap<String, String> hashMap){

        if(this.Dilnet <= 0){

        }else if(this.Dilnet > 0 && this.Dilnet < 3){

        }else if(this.Dilnet >= 3 && this.Dilnet < 6){

        }else if(this.Dilnet >= 6 && this.Dilnet < 9){

        }else {

        }

        return hashMap;


    }
}
