package com.android_poc.newyorktimesproject.pojos;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "NytCustomDetailObj")
public class NytCustomDetailObj {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String title;
    private String description;
    private String imgUrl;
    private String publishDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NytCustomDetailObj{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", imgUrl='").append(imgUrl).append('\'');
        sb.append(", publishDate='").append(publishDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
