package org.techtown.festivity.ui;

public class Posting {
    String username;
    int profileImgRes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProfileImgRes() {
        return profileImgRes;
    }

    public void setProfileImgRes(int profileImgRes) {
        this.profileImgRes = profileImgRes;
    }

    String title;
    String content;
    int imageRes;
    String date;
    String location;

    public Posting(String title, String content, int imageRes, String date, String location) {
        this.title = title;
        this.content = content;
        this.imageRes = imageRes;
        this.date = date;
        this.location = location;
    }

    public Posting(String username, int profileImgRes, String title, String content, int imageRes, String date, String location) {
        this.username = username;
        this.profileImgRes = profileImgRes;
        this.title = title;
        this.content = content;
        this.imageRes = imageRes;
        this.date = date;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
