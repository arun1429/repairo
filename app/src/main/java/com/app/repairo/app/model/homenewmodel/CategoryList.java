package com.app.repairo.app.model.homenewmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryList {
    @SerializedName("id")
    @Expose
    private String id="";
    @SerializedName("category_image")
    @Expose
    private String category_image="";
    @SerializedName("is_active")
    @Expose
    private String is_active="";
@SerializedName("category_name")
    @Expose
    private String category_name="";
@SerializedName("parent")
    @Expose
    private String parent="";

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    @SerializedName("category_description")
    @Expose
    private String category_description="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
