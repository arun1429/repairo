package com.app.repairo.app.model.subcategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.app.repairo.app.model.homenewmodel.CategoryList;

import java.util.ArrayList;

public class SubCateResponse {
    @SerializedName("data")
    @Expose
    private ArrayList<CategoryList> data = new ArrayList<CategoryList>();

    public ArrayList<CategoryList> getData() {
        return data;
    }
    public void setData(ArrayList<CategoryList> data) {
        this.data = data;
    }

    @SerializedName("success")
    @Expose
    private Integer success=0;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    @Expose
    private String message="";

}
