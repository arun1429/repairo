
package com.app.repairo.app.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductsData {

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

    public ArrayList<ProductsList> getData() {
        return data;
    }

    public void setData(ArrayList<ProductsList> data) {
        this.data = data;
    }

    @SerializedName("message")
    @Expose
    private String message="";
    @SerializedName("data")
    @Expose
    private ArrayList<ProductsList> data = new ArrayList<>();


}
