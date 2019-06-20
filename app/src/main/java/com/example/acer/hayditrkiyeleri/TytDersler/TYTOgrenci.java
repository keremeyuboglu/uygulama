package com.example.acer.hayditrkiyeleri.TytDersler;

public class TYTOgrenci {

    String Bolum,sinif;
    double OBP,puan;
    double turkce,matematik,fizik,kimya,biyoloji,tarih,cografya,felsefe,din;

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

    public TYTOgrenci(String bolum, String sinif, double OBP) {
        Bolum = bolum;
        this.sinif = sinif;
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

        if(this.sinif == "MezunTercih"){
            puan = puan + (this.OBP * 30/100);
        } else {
            puan = puan + (this.OBP * 60/100);
        }

        if(puan > 560)
            puan = 560;
        return puan;
    }

}
