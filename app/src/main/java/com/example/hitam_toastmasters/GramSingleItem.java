package com.example.hitam_toastmasters;

public class GramSingleItem {

    private String speakerName, wod, iod, remarks;

    GramSingleItem(String spkr, String wodx, String iodx, String remarksx) {
        speakerName = spkr;
        wod = wodx;
        iod = iodx;
        remarks = remarksx;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getWod() {
        return wod;
    }

    public void setWod(String wod) {
        this.wod = wod;
    }

    public String getIod() {
        return iod;
    }

    public void setIod(String iod) {
        this.iod = iod;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
