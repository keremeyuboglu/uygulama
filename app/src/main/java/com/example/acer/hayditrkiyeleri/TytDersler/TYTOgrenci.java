package com.example.acer.hayditrkiyeleri.TytDersler;

import android.util.Log;

import com.example.acer.hayditrkiyeleri.SiralamaSample;

import java.util.List;

public class TYTOgrenci {

    String Bolum,sinif;
    boolean isMezunTercih;
    double OBP,puan,siralama;
    double turkce,matematik,fizik,kimya,biyoloji,tarih,cografya,felsefe,din;

    public double getSiralama() {
        return siralama;
    }

    public void setSiralama(double siralama) {
        this.siralama = siralama;
    }

    public double getPuan() {
        return puan;
    }

    public void setPuan(double puan) {
        this.puan = puan;
    }

    public double getTurkce() {
        return turkce;
    }

    public void setTurkce(double turkce) {
        this.turkce = turkce;
    }

    public double getMatematik() {
        return matematik;
    }

    public void setMatematik(double matematik) {
        this.matematik = matematik;
    }



    public double getFizik() {
        return fizik;
    }

    public void setFizik(double fizik) {
        this.fizik = fizik;
    }

    public double getKimya() {
        return kimya;
    }

    public void setKimya(double kimya) {
        this.kimya = kimya;
    }

    public double getBiyoloji() {
        return biyoloji;
    }

    public void setBiyoloji(double biyoloji) {
        this.biyoloji = biyoloji;
    }

    public double getTarih() {
        return tarih;
    }

    public void setTarih(double tarih) {
        this.tarih = tarih;
    }

    public double getCografya() {
        return cografya;
    }

    public void setCografya(double cografya) {
        this.cografya = cografya;
    }

    public double getFelsefe() {
        return felsefe;
    }

    public void setFelsefe(double felsefe) {
        this.felsefe = felsefe;
    }

    public double getDin() {
        return din;
    }

    public void setDin(double din) {
        this.din = din;
    }

    public TYTOgrenci(boolean isMezunTercih, double OBP) {

        //Bolum ve sinif bir yerlerde kullanılmıyor o yüzden şimdilik böyle kalsın
        this.isMezunTercih=isMezunTercih;
        this.OBP = OBP;
    }

    public String getBolum() {
        return Bolum;
    }

    public void setBolum(String bolum) {
        Bolum = bolum;
    }

    public String getSinif() {
        return sinif;
    }

    public void setSinif(String sinif) {
        this.sinif = sinif;
    }

    public double getOBP() {
        return OBP;
    }

    public void setOBP(double OBP) {
        this.OBP = OBP;
    }




    public double tytPuan(){
        double puan = 99.435 + (this.turkce * 3.165325) + ((this.cografya+ this.tarih + this.felsefe + this.din) * 3.60495) +
                ((this.biyoloji + this.fizik + this.kimya) *3.3464) + (this.matematik * 3.472025);

        if(isMezunTercih){
            puan = puan + (this.OBP * 30/100);
        } else {
            puan = puan + (this.OBP * 60/100);
        }

        if(puan > 560)
            puan = 560;
        return puan;
    }

    public double siralamaHesabı(double puan, List<SiralamaSample> siralamaSample){

        // Önce puanı integera çevirelim
        int tempPuan = (int) puan;
        Log.d("mesaj", "bu " + tempPuan);

        // Listte bu puana ve bu puanın bir fazlasına denk gelen elemanları bulalım
        SiralamaSample sample = siralamaSample.get(500-tempPuan);
        SiralamaSample sample2 = siralamaSample.get(500-tempPuan-1);
        Log.d("mesaj","bu 3+ " + sample.getPuan());

        // Puanın ondalık kısmını hesaplayalım ex: 496.58 için 0.58
        double puaninOndalikKısmı = puan - Math.floor(puan);
        Log.d("mesaj","bu 3+ " + puaninOndalikKısmı);

        // iki puan arasında kaç puanda bir sıranın ne kadar artacağını hesaplamak için
        // örneğin 492 ile 493 arasında her 0.16 puanda 1 kişi artıyor
        double ondalikliSiralama = 1 / (sample.getTytsira() - sample2.getTytsira());
        Log.d("mesaj","bu 3+ " + ondalikliSiralama);

        // Bu ikisinin bölümüğ sayesinde kaç kişilik sıralama kaydedileceğini hesaplamış olduk
        int benimeklenecek = (int) ( puaninOndalikKısmı / ondalikliSiralama);
        Log.d("mesaj","bu 3+ " + benimeklenecek);

        return sample.getTytsira() - (double) benimeklenecek;
    }

}