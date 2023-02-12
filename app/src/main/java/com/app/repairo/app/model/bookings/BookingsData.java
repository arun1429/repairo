
package com.app.repairo.app.model.bookings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookingsData {

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

    public ArrayList<BookingList> getData() {
        return data;
    }

    public void setData(ArrayList<BookingList> data) {
        this.data = data;
    }

    @SerializedName("message")
    @Expose
    private String message="";
    @SerializedName("data")
    @Expose
    private ArrayList<BookingList> data = new ArrayList<>();


}
