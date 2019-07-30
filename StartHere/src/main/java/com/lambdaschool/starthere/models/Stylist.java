package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stylists")
public class Stylist extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stylistid;

    @Column(nullable = false, unique = true)
    private String stylistUserName;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "stylist", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("stylist")
    private List<Images> images = new ArrayList<>();

    public Stylist() {
    }

    public Stylist(String stylistUserName, String password, List<Images> images) {
        this.stylistUserName = stylistUserName;
        this.password = password;
        this.images = images;
    }

    public long getStylistid() {
        return stylistid;
    }

    public void setStylistid(long stylistid) {
        this.stylistid = stylistid;
    }

    public String getStylistUserName() {
        return stylistUserName;
    }

    public void setStylistUserName(String stylistUserName) {
        this.stylistUserName = stylistUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }
}
