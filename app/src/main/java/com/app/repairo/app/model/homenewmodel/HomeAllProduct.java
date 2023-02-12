package com.app.repairo.app.model.homenewmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomeAllProduct {
    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    @SerializedName("banner_image")
    @Expose
    private String banner_image = "";


    @SerializedName("list_services")
    @Expose
    private ArrayList<CategoryList> list_services = new ArrayList<CategoryList>();

    public ArrayList<CategoryList> getList_services() {
        return list_services;
    }

    public void setList_services(ArrayList<CategoryList> list_services) {
        this.list_services = list_services;
    }

    public ArrayList<BannerList> getList_banners() {
        return list_banners;
    }

    public void setList_banners(ArrayList<BannerList> list_banners) {
        this.list_banners = list_banners;
    }

    @SerializedName("list_banners")
    @Expose
    private ArrayList<BannerList> list_banners = new ArrayList<BannerList>();

}
