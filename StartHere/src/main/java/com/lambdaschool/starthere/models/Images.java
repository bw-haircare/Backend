package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Images extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long imageId;

    @Column(nullable = false)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stylistid", nullable = false)
    @JsonIgnoreProperties({"images", "hibernateLazyInitializer"})
    private Stylist stylist;

    public Images() {
    }

    public Images(String image, Stylist stylist) {
        this.image = image;
        this.stylist = stylist;
    }

    public long getimagesid() {
        return imageId;
    }

    public void setimagesid(long imagesid) {
        this.imageId = imagesid;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Stylist getStylist() {
        return stylist;
    }

    public void setStylist(Stylist stylist) {
        this.stylist = stylist;
    }
}

