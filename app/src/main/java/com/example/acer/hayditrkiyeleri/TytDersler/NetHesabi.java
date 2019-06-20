package com.example.acer.hayditrkiyeleri.TytDersler;

import android.util.Log;

public class NetHesabi {

    // Gelmesi gereken veriler:
    // Deneme sayisi
    // Deneme netleri (her bir ders/konu için ayrı ayrı)

    public double dersneti(int denemesayisi, double[][] netler){



        // Doğru - yanlış/4

        if(denemesayisi == 0){
            return 0;
        }else if(denemesayisi == 1){

            return netler[0][0] - (netler[0][1]/4);

        }else if(denemesayisi == 2){

            return ((netler[0][0] - (netler[0][1]/4)) * 40/100) + ((netler[1][0] - (netler[1][1]/4)) * 60/100);

        }else if(denemesayisi == 3){

            return ((netler[0][0] - (netler[0][1]/4)) * 20/100) + ((netler[1][0] - (netler[1][1]/4)) * 30/100) + ((netler[2][0] - (netler[2][1]/4)) * 50/100);

        }else if(denemesayisi == 4){

            return ((netler[0][0] - (netler[0][1]/4)) * 15/100) + ((netler[1][0] - (netler[1][1]/4)) * 20/100) + ((netler[2][0] - (netler[2][1]/4)) * 25/100) + ((netler[3][0] - (netler[3][1]/4)) * 40/100);

        }else {
            int x = denemesayisi;
            return ((netler[x-5][0] - (netler[x-5][1]/4)) * 10/100) + ((netler[x-4][0] - (netler[x-4][1]/4)) * 15/100) + ((netler[x-3][0] - (netler[x-3][1]/4)) * 20/100) + ((netler[x-2][0] - (netler[x-2][1]/4)) * 25/100) + ((netler[x-1][0] - (netler[x-1][1]/4)) * 30/100);
        }


    }

    // Konuda gelen parametreler toplam soru ve yanlış+boş olacak
    // Toplamsoru [x][0], yanlış+boş ise [x][1] olsun

    public int konuikidenaz(int denemesayisi, int[][] netler){

        if(denemesayisi == 0){
            return 0;
        }else if(denemesayisi == 1){

            return ((netler[0][0] - netler[0][1])/(netler[0][0]));

        }else if(denemesayisi == 2){

            int ortalama = ((netler[0][0] - netler[0][1])/(netler[0][0])) * 40/100 + ((netler[1][0] - netler[1][1])/(netler[1][0])) * 60/100;
            int onceki_ortalama = ((netler[0][0] - netler[0][1])/(netler[0][0]));
            return (ortalama + onceki_ortalama) /2;

        }else if(denemesayisi == 3){

            int ortalama = ((netler[0][0] - netler[0][1])/(netler[0][0])) * 20/100 + ((netler[1][0] - netler[1][1])/(netler[1][0])) * 30/100 + ((netler[2][0] - netler[2][1])/(netler[2][0])) * 50/100;
            int onceki_ortalama = ((netler[0][0] - netler[0][1])/(netler[0][0])) * 40/100 + ((netler[1][0] - netler[1][1])/(netler[1][0])) * 60/100;

            return (ortalama + onceki_ortalama) /2;

        }else if(denemesayisi == 4){

            int ortalama = ((netler[0][0] - netler[0][1])/(netler[0][0])) * 15/100 + ((netler[1][0] - netler[1][1])/(netler[1][0])) * 20/100 + ((netler[2][0] - netler[2][1])/(netler[2][0])) * 25/100 + ((netler[3][0] - netler[3][1])/(netler[3][0])) * 40/100;
            int onceki_ortalama = ((netler[0][0] - netler[0][1])/(netler[0][0])) * 20/100 + ((netler[1][0] - netler[1][1])/(netler[1][0])) * 30/100 + ((netler[2][0] - netler[2][1])/(netler[2][0])) * 50/100;

            return (ortalama + onceki_ortalama) /2;

        }else {
            int x = denemesayisi;
            int ortalama = ((netler[x-5][0] - netler[x-5][1])/(netler[x-5][0])) * 10/100 + ((netler[x-4][0] - netler[x-4][1])/(netler[x-4][0])) * 15/100 + ((netler[x-3][0] - netler[x-3][1])/(netler[x-3][0])) * 20/100 + ((netler[x-2][0] - netler[x-2][1])/(netler[x-2][0])) * 25/100 + ((netler[x-1][0] - netler[x-1][1])/(netler[x-1][0])) * 30/100;
            int onceki_ortalama = ((netler[x-5][0] - netler[x-5][1])/(netler[x-5][0])) * 15/100 + ((netler[x-4][0] - netler[x-4][1])/(netler[x-4][0])) * 20/100 + ((netler[x-3][0] - netler[x-3][1])/(netler[x-3][0])) * 25/100 + ((netler[x-2][0] - netler[x-2][1])/(netler[x-2][0])) * 40/100;

            return (ortalama + onceki_ortalama) /2;
        }
    }

    // Konuda gelen parametreler toplam soru ve yanlış+boş olacak
    // Toplamsoru [x][0], yanlış+boş ise [x][1] olsun

    public int konuikidencok(int denemesayisi, int[][] netler){

        if(denemesayisi == 0){
            return 0;
        }else if(denemesayisi == 1){

            return ((netler[0][0] - netler[0][1])/(netler[0][0]));

        }else if(denemesayisi == 2){

            int ortalama = ((netler[0][0] - netler[0][1])/(netler[0][0])) * 40/100 + ((netler[1][0] - netler[1][1])/(netler[1][0])) * 60/100;

            return ortalama ;

        }else if(denemesayisi == 3){

            int ortalama = ((netler[0][0] - netler[0][1])/(netler[0][0])) * 20/100 + ((netler[1][0] - netler[1][1])/(netler[1][0])) * 30/100 + ((netler[2][0] - netler[2][1])/(netler[2][0])) * 50/100;

            return ortalama ;

        }else if(denemesayisi == 4){

            int ortalama = ((netler[0][0] - netler[0][1])/(netler[0][0])) * 15/100 + ((netler[1][0] - netler[1][1])/(netler[1][0])) * 20/100 + ((netler[2][0] - netler[2][1])/(netler[2][0])) * 25/100 + ((netler[3][0] - netler[3][1])/(netler[3][0])) * 40/100;

            return ortalama ;

        }else {
            int x = denemesayisi;
            int ortalama = ((netler[x-5][0] - netler[x-5][1])/(netler[x-5][0])) * 10/100 + ((netler[x-4][0] - netler[x-4][1])/(netler[x-4][0])) * 15/100 + ((netler[x-3][0] - netler[x-3][1])/(netler[x-3][0])) * 20/100 + ((netler[x-2][0] - netler[x-2][1])/(netler[x-2][0])) * 25/100 + ((netler[x-1][0] - netler[x-1][1])/(netler[x-1][0])) * 30/100;

            return ortalama ;
        }
    }
}
