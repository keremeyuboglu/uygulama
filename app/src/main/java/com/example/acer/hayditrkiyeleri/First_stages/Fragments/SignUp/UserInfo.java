package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

public class UserInfo {
    private String username="";
    private String password="";

    private String email="";

    private int bolum;
    private int hedef;
    private int saat;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getSaat() {
        return saat;
    }

    public void setSaat(int saat) {
        this.saat = saat;
    }
}
