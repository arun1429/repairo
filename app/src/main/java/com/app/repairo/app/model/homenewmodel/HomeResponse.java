package com.app.repairo.app.model.homenewmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomeResponse {
    public HomeAllProduct getData() {
        return data;
    }

    public void setData(HomeAllProduct data) {
        this.data = data;
    }

    @SerializedName("data")
    @Expose
    private HomeAllProduct data = null;

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
