package com.test.backend.Models.VideoFile;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "VideoFile")

public class VideoFile implements java.io.Serializable{

    @Id
    @Column(name = "TITLE",unique=true,nullable = false)
    private String title;
    @Column(name = "LINK",unique=true)
    private String link;
    @Column(name = "CREATION_DATE")
    private Date creationDate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

