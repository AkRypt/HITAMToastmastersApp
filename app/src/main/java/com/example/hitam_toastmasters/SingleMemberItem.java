package com.example.hitam_toastmasters;

public class SingleMemberItem {

    private String name, role, level, photoName;

    public SingleMemberItem(String namex, String rolex, String levelx, String photoNamex) {
        name = namex;
        role = rolex;
        level = levelx;
        photoName = photoNamex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
