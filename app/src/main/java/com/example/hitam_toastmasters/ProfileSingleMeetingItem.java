package com.example.hitam_toastmasters;

public class ProfileSingleMeetingItem {

    ProfileSingleMeetingItem() {}

    private String meetingName, rolePlayed, date, ribbonEarned;

    ProfileSingleMeetingItem(String namex, String rolex, String datex, String ribbonx) {
        meetingName = namex;
        rolePlayed = rolex;
        date = datex;
        ribbonEarned = ribbonx;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getRolePlayed() {
        return rolePlayed;
    }

    public void setRolePlayed(String rolePlayed) {
        this.rolePlayed = rolePlayed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRibbonEarned() {
        return ribbonEarned;
    }

    public void setRibbonEarned(String ribbonEarned) {
        this.ribbonEarned = ribbonEarned;
    }
}
