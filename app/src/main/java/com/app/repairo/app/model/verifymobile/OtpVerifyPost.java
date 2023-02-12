package com.app.repairo.app.model.verifymobile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpVerifyPost {

    @SerializedName("mobile")
    @Expose
    private String mobile="";
    @SerializedName("otp")
    @Expose
    private String otp="";

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @SerializedName("deviceToken")
    @Expose
    private String deviceToken="";



    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}