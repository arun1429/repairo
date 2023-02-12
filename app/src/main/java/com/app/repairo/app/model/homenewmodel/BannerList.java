package com.app.repairo.app.model.homenewmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerList {
    @SerializedName("id")
    @Expose
    private String id="";
    @SerializedName("banner_image")
    @Expose
    private String banner_image="";
    @SerializedName("is_active")
    @Expose
    private String is_active="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @SerializedName("created_at")
    @Expose
    private String created_at="";
}
