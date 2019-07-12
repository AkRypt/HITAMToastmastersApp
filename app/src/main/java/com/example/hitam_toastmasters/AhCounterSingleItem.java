package com.example.hitam_toastmasters;

public class AhCounterSingleItem {

    private String spkrname, ah, um, shrt, lng, rem;

    AhCounterSingleItem(String spkr, String ahx, String umx, String shrtx, String lngx, String remx) {
        spkrname = spkr;
        ah = ahx;
        um = umx;
        shrt = shrtx;
        lng = lngx;
        rem = remx;
    }

    public String getSpkrname() {
        return spkrname;
    }

    public void setSpkrname(String spkrname) {
        this.spkrname = spkrname;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getShrt() {
        return shrt;
    }

    public void setShrt(String shrt) {
        this.shrt = shrt;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }
}
