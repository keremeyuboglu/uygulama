package com.example.acer.hayditrkiyeleri.TytDersler;

import android.util.Log;

import com.example.acer.hayditrkiyeleri.SiralamaSample;

import java.util.List;

public class AYTOgrenciMF {

    boolean isMezunTercih;
    double Matematik,Fizik,Kimya,Biyoloji;
    double OBP, obpsizPuan ,obpliPuan, obpsizSiralama,obpliSiralama,TYTHam;

    public AYTOgrenciMF(double TYTHam, double OBP, boolean isMezunTercih) {
        this.TYTHam = TYTHam;
        this.OBP = OBP;
        this.isMezunTercih = isMezunTercih;

    }
    public double getTYTHam() {
        return TYTHam;
    }

    public void setTYTHam(double TYTHam) {
        this.TYTHam = TYTHam;
    }

    public double getOBP() {
        return OBP;
    }

    public void setOBP(double OBP) {
        this.OBP = OBP;
    }

    public double getObpsizPuan() {
        return obpsizPuan;
    }

    public void setObpsizPuan(double obpsizPuan) {
        this.obpsizPuan = obpsizPuan;
    }

    public double getObpliPuan() {
        return obpliPuan;
    }

    public void setObpliPuan(double obpliPuan) {
        this.obpliPuan = obpliPuan;
    }

    public double getObpsizSiralama() {
        return obpsizSiralama;
    }

    public void setObpsizSiralama(double obpsizSiralama) {
        this.obpsizSiralama = obpsizSiralama;
    }

    public double getObpliSiralama() {
        return obpliSiralama;
    }

    public void setObpliSiralama(double obpliSiralama) {
        this.obpliSiralama = obpliSiralama;
    }

    public double getMatematik() {
        return Matematik;
    }

    public void setMatematik(double matematik) {
        Matematik = matematik;
    }

    public double getFizik() {
        return Fizik;
    }

    public void setFizik(double fizik) {
        Fizik = fizik;
    }

    public double getKimya() {
        return Kimya;
    }

    public void setKimya(double kimya) {
        Kimya = kimya;
    }

    public double getBiyoloji() {
        return Biyoloji;
    }

    public void setBiyoloji(double biyoloji) {
        Biyoloji = biyoloji;
    }

    public void aytPuan() {
        double puan = 99.633 + (this.Matematik * 3.1654) + (this.Fizik * 3.60335) +
                (this.Kimya * 2.86984) + (this.Biyoloji * 2.86292) + (this.TYTHam * 37.14119/100);

        if(puan < 100)
            puan = 100;
        this.obpsizPuan = puan;
        if (isMezunTercih) {
            puan = puan + (this.OBP * 30 / 100);
        } else {
            puan = puan + (this.OBP * 60 / 100);
        }

        if (puan > 560)
            puan = 560;

        if(puan < 165)
            puan = 165;
        this.obpliPuan = puan;
    }

    public double siralamaHesabı(double puan, List<SiralamaSample> siralamaSample) {

        // Önce puanı integera çevirelim
        int tempPuan = (int) puan;
        Log.d("mesaj", "bu 22" + tempPuan);

        // Listte bu puana ve bu puanın bir fazlasına denk gelen elemanları bulalım
        SiralamaSample sample = siralamaSample.get(500 - tempPuan);
        SiralamaSample sample2 = siralamaSample.get(500 - tempPuan - 1);
        Log.d("mesaj", "bu 3+ " + sample.getPuan());

        // Puanın ondalık kısmını hesaplayalım ex: 496.58 için 0.58
        double puaninOndalikKısmı = puan - Math.floor(puan);
        Log.d("mesaj", "bu 3+ " + puaninOndalikKısmı);

        // iki puan arasında kaç puanda bir sıranın ne kadar artacağını hesaplamak için
        // örneğin 492 ile 493 arasında her 0.16 puanda 1 kişi artıyor
        double ondalikliSiralama = 1 / (sample.getSayisalsira() - sample2.getSayisalsira());
        Log.d("mesaj", "bu 3+ " + ondalikliSiralama);

        // Bu ikisinin bölümüğ sayesinde kaç kişilik sıralama kaydedileceğini hesaplamış olduk
        int benimeklenecek = (int) (puaninOndalikKısmı / ondalikliSiralama);
        Log.d("mesaj", "bu 3+ " + benimeklenecek);

        return sample.getSayisalsira() - (double) benimeklenecek;
    }

    public double obpsiralamaHesabı(double puan, List<SiralamaSample> siralamaSample) {

        // Önce puanı integera çevirelim
        int tempPuan = (int) puan;
        Log.d("mesaj", "bu 22" + tempPuan);

        // Listte bu puana ve bu puanın bir fazlasına denk gelen elemanları bulalım
        SiralamaSample sample = siralamaSample.get(560 - tempPuan);
        SiralamaSample sample2 = siralamaSample.get(560 - tempPuan - 1);
        Log.d("mesaj", "bu 3+ " + sample.getPuan());

        // Puanın ondalık kısmını hesaplayalım ex: 496.58 için 0.58
        double puaninOndalikKısmı = puan - Math.floor(puan);
        Log.d("mesaj", "bu 3+ " + puaninOndalikKısmı);

        // iki puan arasında kaç puanda bir sıranın ne kadar artacağını hesaplamak için
        // örneğin 492 ile 493 arasında her 0.16 puanda 1 kişi artıyor
        double ondalikliSiralama = 1 / (sample.getSayisalsira() - sample2.getSayisalsira());
        Log.d("mesaj", "bu 3+ " + ondalikliSiralama);

        // Bu ikisinin bölümüğ sayesinde kaç kişilik sıralama kaydedileceğini hesaplamış olduk
        int benimeklenecek = (int) (puaninOndalikKısmı / ondalikliSiralama);
        Log.d("mesaj", "bu 3+ " + benimeklenecek);

        return sample.getSayisalsira() - (double) benimeklenecek;
    }
}
