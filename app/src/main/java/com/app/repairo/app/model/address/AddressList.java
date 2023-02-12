package com.app.repairo.app.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressList {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    @SerializedName("id")
    @Expose
    private String id="";
    @SerializedName("userid")
    @Expose
    private String userid="";
    @SerializedName("name")
    @Expose
    private String name="";
    @SerializedName("address")
    @Expose
    private String address="";

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    @SerializedName("landmark")
    @Expose
    private String landmark="";
    @SerializedName("city")
    @Expose
    private String city="";
    @SerializedName("state")
    @Expose
    private String state="";
    @SerializedName("pincode")
    @Expose
    private String pincode="";
    @SerializedName("country")
    @Expose
    private String country="";
    @SerializedName("is_default")
    @Expose
    private String is_default="";

}
