package com.ui.g5.voyafun;

public class Information {


    private int Id;
    private int Image;
    private String Title;

    public Information(int id, int image, String title) {
        Id = id;
        Image = image;
        Title = title;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


}
