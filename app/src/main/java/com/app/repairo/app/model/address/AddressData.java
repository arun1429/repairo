
package com.app.repairo.app.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddressData {

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

    public ArrayList<AddressList> getData() {
        return data;
    }

    public void setData(ArrayList<AddressList> data) {
        this.data = data;
    }

    @SerializedName("message")
    @Expose
    private String message="";
    @SerializedName("data")
    @Expose
    private ArrayList<AddressList> data = new ArrayList<>();


}
