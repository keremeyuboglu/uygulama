package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

public class UserInfo {
    private String Username="";
    private String Password="";
    private String Email="";

    private int bolum=-1;
    private int hedef=-1;
    private double obp=-1;

    private int sinif=-1;
    private int istercih;

    //Getters and Setters
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getBolum() {
        return bolum;
    }

    public void setBolum(int bolum) {
        this.bolum = bolum;
    }

    public int getHedef() {
        return hedef;
    }

    public void setHedef(int hedef) {
        this.hedef = hedef;
    }

    public double getObp() {
        return obp;
    }

    public void setObp(double obp) {
        this.obp = obp;
    }

    public int getSinif() {
        return sinif;
    }

    public void setSinif(int sinif) {
        this.sinif = sinif;
    }

    public int getIstercih() {
        return istercih;
    }

    public void setIstercih(int istercih) {
        this.istercih = istercih;
    }
}
