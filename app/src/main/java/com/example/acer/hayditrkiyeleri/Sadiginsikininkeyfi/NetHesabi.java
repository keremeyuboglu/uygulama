package com.example.acer.hayditrkiyeleri.Sadiginsikininkeyfi;

import com.example.acer.hayditrkiyeleri.Database.Entities.Deneme_ders;
import com.example.acer.hayditrkiyeleri.Database.Entities.Stat;

import java.util.ArrayList;

public abstract class NetHesabi {

    // Gelmesi gereken veriler:
    // Deneme sayisi
    // Deneme netleri (her bir ders/konu için ayrı ayrı)

    public static double dersneti(int denemesayisi, ArrayList<Stat> veriler_ders) {


        // Doğru - yanlış/4

        if (denemesayisi == 0) {
            return 0;
        } else if (denemesayisi == 1) {

            return veriler_ders.get(0).getDogru() - (veriler_ders.get(0).getYanlis() / 4);

        } else if (denemesayisi == 2) {

            return ((veriler_ders.get(0).getDogru() - ((veriler_ders.get(0).getYanlis() / 4)) * 40 / 100) + ((veriler_ders.get(1).getDogru() - (veriler_ders.get(1).getYanlis()) / 4)) * 60 / 100);

        } else if (denemesayisi == 3) {

            return ((veriler_ders.get(0).getDogru() - (veriler_ders.get(0).getYanlis() / 4)) * 20 / 100) + ((veriler_ders.get(1).getDogru() - (veriler_ders.get(1).getYanlis() / 4)) * 30 / 100) + ((veriler_ders.get(2).getDogru() - (veriler_ders.get(2).getYanlis() / 4)) * 50 / 100);

        } else if (denemesayisi == 4) {

            return ((veriler_ders.get(0).getDogru() - (veriler_ders.get(0).getYanlis()/ 4)) * 15 / 100) + ((veriler_ders.get(1).getDogru() - (veriler_ders.get(1).getYanlis() / 4)) * 20 / 100) + ((veriler_ders.get(2).getDogru() - (veriler_ders.get(2).getYanlis() / 4)) * 25 / 100) + ((veriler_ders.get(3).getDogru() - (veriler_ders.get(3).getYanlis() / 4)) * 40 / 100);

        } else {
            int x = 7;
            return ((veriler_ders.get(x - 5).getDogru() - (veriler_ders.get(x - 5).getYanlis() / 4)) * 10 / 100) + ((veriler_ders.get(x - 4).getDogru() - (veriler_ders.get(x - 4).getYanlis() / 4)) * 15 / 100) + ((veriler_ders.get(x - 3).getDogru() - (veriler_ders.get(x - 3).getYanlis() / 4)) * 20 / 100) + ((veriler_ders.get(x - 2).getDogru() - (veriler_ders.get(x - 28).getYanlis() / 4)) * 25 / 100) + ((veriler_ders.get(x - 1).getDogru() - (veriler_ders.get(x - 1).getYanlis() / 4)) * 30 / 100);
        }


    }

    // Konuda gelen parametreler toplam soru ve yanlış+boş olacak
    // Toplamsoru [x][0], yanlış+boş ise [x][1] olsun
    public static int konuikidenaz(int denemesayisi, int[][] netler) {

        if (denemesayisi == 0) {
            return 0;
        } else if (denemesayisi == 1) {

            return ((netler[0][0] - netler[0][1]) / (netler[0][0]));

        } else if (denemesayisi == 2) {

            int ortalama = ((netler[0][0] - netler[0][1]) / (netler[0][0])) * 40 / 100 + ((netler[1][0] - netler[1][1]) / (netler[1][0])) * 60 / 100;
            int onceki_ortalama = ((netler[0][0] - netler[0][1]) / (netler[0][0]));

            return (ortalama + onceki_ortalama) / 2;

        } else if (denemesayisi == 3) {

            int ortalama = ((netler[0][0] - netler[0][1]) / (netler[0][0])) * 20 / 100 + ((netler[1][0] - netler[1][1]) / (netler[1][0])) * 30 / 100 + ((netler[2][0] - netler[2][1]) / (netler[2][0])) * 50 / 100;
            int onceki_ortalama = ((netler[0][0] - netler[0][1]) / (netler[0][0])) * 40 / 100 + ((netler[1][0] - netler[1][1]) / (netler[1][0])) * 60 / 100;

            return (ortalama + onceki_ortalama) / 2;

        } else if (denemesayisi == 4) {

            int ortalama = ((netler[0][0] - netler[0][1]) / (netler[0][0])) * 15 / 100 + ((netler[1][0] - netler[1][1]) / (netler[1][0])) * 20 / 100 + ((netler[2][0] - netler[2][1]) / (netler[2][0])) * 25 / 100 + ((netler[3][0] - netler[3][1]) / (netler[3][0])) * 40 / 100;
            int onceki_ortalama = ((netler[0][0] - netler[0][1]) / (netler[0][0])) * 20 / 100 + ((netler[1][0] - netler[1][1]) / (netler[1][0])) * 30 / 100 + ((netler[2][0] - netler[2][1]) / (netler[2][0])) * 50 / 100;

            return (ortalama + onceki_ortalama) / 2;

        } else {
            int x = 7;
            int ortalama = ((netler[x - 5][0] - netler[x - 5][1]) / (netler[x - 5][0])) * 10 / 100 + ((netler[x - 4][0] - netler[x - 4][1]) / (netler[x - 4][0])) * 15 / 100 + ((netler[x - 3][0] - netler[x - 3][1]) / (netler[x - 3][0])) * 20 / 100 + ((netler[x - 2][0] - netler[x - 2][1]) / (netler[x - 2][0])) * 25 / 100 + ((netler[x - 1][0] - netler[x - 1][1]) / (netler[x - 1][0])) * 30 / 100;
            int onceki_ortalama = ((netler[x - 5][0] - netler[x - 5][1]) / (netler[x - 5][0])) * 15 / 100 + ((netler[x - 4][0] - netler[x - 4][1]) / (netler[x - 4][0])) * 20 / 100 + ((netler[x - 3][0] - netler[x - 3][1]) / (netler[x - 3][0])) * 25 / 100 + ((netler[x - 2][0] - netler[x - 2][1]) / (netler[x - 2][0])) * 40 / 100;

            return (ortalama + onceki_ortalama) / 2;
        }
    }

    // Konuda gelen parametreler toplam soru ve yanlış+boş olacak
    // Toplamsoru [x][0], yanlış+boş ise [x][1] olsun

    public static int konuikidencok(int denemesayisi, int[][] netler) {

        if (denemesayisi == 0) {
            return 0;
        } else if (denemesayisi == 1) {

            return ((netler[0][0] - netler[0][1]) / (netler[0][0]));

        } else if (denemesayisi == 2) {

            int ortalama = ((netler[0][0] - netler[0][1]) / (netler[0][0])) * 40 / 100 + ((netler[1][0] - netler[1][1]) / (netler[1][0])) * 60 / 100;

            return ortalama;

        } else if (denemesayisi == 3) {

            int ortalama = ((netler[0][0] - netler[0][1]) / (netler[0][0])) * 20 / 100 + ((netler[1][0] - netler[1][1]) / (netler[1][0])) * 30 / 100 + ((netler[2][0] - netler[2][1]) / (netler[2][0])) * 50 / 100;

            return ortalama;

        } else if (denemesayisi == 4) {

            int ortalama = ((netler[0][0] - netler[0][1]) / (netler[0][0])) * 15 / 100 + ((netler[1][0] - netler[1][1]) / (netler[1][0])) * 20 / 100 + ((netler[2][0] - netler[2][1]) / (netler[2][0])) * 25 / 100 + ((netler[3][0] - netler[3][1]) / (netler[3][0])) * 40 / 100;

            return ortalama;

        } else {
            int x = 7;
            int ortalama = ((netler[x - 5][0] - netler[x - 5][1]) / (netler[x - 5][0])) * 10 / 100 + ((netler[x - 4][0] - netler[x - 4][1]) / (netler[x - 4][0])) * 15 / 100 + ((netler[x - 3][0] - netler[x - 3][1]) / (netler[x - 3][0])) * 20 / 100 + ((netler[x - 2][0] - netler[x - 2][1]) / (netler[x - 2][0])) * 25 / 100 + ((netler[x - 1][0] - netler[x - 1][1]) / (netler[x - 1][0])) * 30 / 100;

            return ortalama;
        }
    }

}