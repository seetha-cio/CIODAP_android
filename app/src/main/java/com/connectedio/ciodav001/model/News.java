package com.connectedio.ciodav001.model;

/**
 * Created by seetharaman on 9/8/16.
 */
public class News {
    String title,description,auther,date,image,video;
    int id;

    public News(String title, String description, String auther, String date, String image, String video, int id) {
        this.title = title;
        this.description = description;
        this.auther = auther;
        this.date = date;
        this.image = image;
        this.video = video;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
