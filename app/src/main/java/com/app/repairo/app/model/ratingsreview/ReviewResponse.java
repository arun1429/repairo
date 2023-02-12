package com.app.repairo.app.model.ratingsreview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReviewResponse {
    @SerializedName("data")
    @Expose
    private ArrayList<ReviewData> data;


    public ArrayList<ReviewData> getData() {
        return data;
    }

    public void setData(ArrayList<ReviewData> data) {
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
