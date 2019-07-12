package com.example.hitam_toastmasters;

public class MeetingDetails {

    public MeetingDetails() {}

    private String meetingName, rolePlayed, ribbonEarned, date;

    public MeetingDetails(String namex, String rolex, String ribbonx, String datex) {
        meetingName = namex;
        rolePlayed = rolex;
        ribbonEarned = ribbonx;
        date = datex;
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

    public String getRibbonEarned() {
        return ribbonEarned;
    }

    public void setRibbonEarned(String ribbonEarned) {
        this.ribbonEarned = ribbonEarned;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
