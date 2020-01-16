package com.erporate.visitjogja.Model;

@SuppressWarnings("unused")
public class ScreenItem {

    private String Title;
    private String Description;
    private int ScreenImg;
    private int BackgroundImg;

    public ScreenItem(String title, String description, int screenImg, int backgroundImg) {
        Title = title;
        Description = description;
        ScreenImg = screenImg;
        BackgroundImg = backgroundImg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public int getBackgroundImg() {
        return BackgroundImg;
    }

    public void setBackgroundImg(int backgroundImg) {
        BackgroundImg = backgroundImg;
    }
}

