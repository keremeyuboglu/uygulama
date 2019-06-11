package com.example.acer.hayditrkiyeleri.TytDersler;

public class TYTOgrenci {

    String Bolum,sinif;
    float OBP,puan;
    float turkce,matematik,geometri,fizik,kimya,biyoloji,tarih,cografya,felsefe;

    public float getPuan() {
        return puan;
    }

    public void setPuan(float puan) {
        this.puan = puan;
    }

    public float getTurkce() {
        return turkce;
    }

    public void setTurkce(float turkce) {
        this.turkce = turkce;
    }

    public float getMatematik() {
        return matematik;
    }

    public void setMatematik(float matematik) {
        this.matematik = matematik;
    }

    public float getGeometri() {
        return geometri;
    }

    public void setGeometri(float geometri) {
        this.geometri = geometri;
    }

    public float getFizik() {
        return fizik;
    }

    public void setFizik(float fizik) {
        this.fizik = fizik;
    }

    public float getKimya() {
        return kimya;
    }

    public void setKimya(float kimya) {
        this.kimya = kimya;
    }

    public float getBiyoloji() {
        return biyoloji;
    }

    public void setBiyoloji(float biyoloji) {
        this.biyoloji = biyoloji;
    }

    public float getTarih() {
        return tarih;
    }

    public void setTarih(float tarih) {
        this.tarih = tarih;
    }

    public float getCografya() {
        return cografya;
    }

    public void setCografya(float cografya) {
        this.cografya = cografya;
    }

    public float getFelsefe() {
        return felsefe;
    }

    public void setFelsefe(float felsefe) {
        this.felsefe = felsefe;
    }

    public float getDin() {
        return din;
    }

    public void setDin(float din) {
        this.din = din;
    }

    float din;

    public TYTOgrenci(String bolum, String sinif, float OBP) {
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

    public float getOBP() {
        return OBP;
    }

    public void setOBP(float OBP) {
        this.OBP = OBP;
    }




    public double tytPuan(){
        double puan = 99.435 + (this.turkce * 3.165325) + ((this.cografya+ this.tarih + this.felsefe + this.din) * 3.60495) +
                ((this.biyoloji + this.fizik + this.kimya) *3.3464) + ((this.matematik + this.geometri) * 3.472025);

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
