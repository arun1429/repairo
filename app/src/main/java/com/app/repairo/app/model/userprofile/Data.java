
package com.app.repairo.app.model.userprofile;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    String document;
    @SerializedName("dob")
    @Expose
    private String dob = "";
    @SerializedName("userId")
    @Expose
    private String userId;
     @SerializedName("profileImage")
    @Expose
    private String profileImage="";
    @SerializedName("mobileVerified")
    @Expose
    private Boolean mobileVerified;
    @SerializedName("documentVerify")
    @Expose
    private Integer documentVerify;
    @SerializedName("storeId")
    @Expose
    private Integer storeId;
    @SerializedName("storeName")
    @Expose
    private String storeName;
    @SerializedName("isLogin")
    @Expose
    private Boolean isLogin;
    @SerializedName("originalCustomer")
    @Expose
    private Boolean originalCustomer;
    @SerializedName("orderStatus")
    @Expose
    private Boolean orderStatus;
    @SerializedName("addUserFromCSV")
    @Expose
    private Boolean addUserFromCSV;
    @SerializedName("emailVerified")
    @Expose
    private Boolean emailVerified;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("signuptype")
    @Expose
    private String signuptype;
    @SerializedName("referralCode")
    @Expose
    private String referralCode;
    @SerializedName("affilateCode")
    @Expose
    private String affilateCode;
    @SerializedName("usedAffilateCode")
    @Expose
    private String usedAffilateCode;
    @SerializedName("usedReferralCode")
    @Expose
    private String usedReferralCode;
    @SerializedName("hearFrom")
    @Expose
    private String hearFrom;
    @SerializedName("affilateLevel")
    @Expose
    private Integer affilateLevel;
    @SerializedName("affilateUserId")
    @Expose
    private List<Object> affilateUserId = null;
    @SerializedName("address")
    @Expose
    private List<Address> address = null;
    @SerializedName("createdAt")
    @Expose
    private long createdAt;
    @SerializedName("updatedAt")
    @Expose
    private long updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("mobileOTP")
    @Expose
    private Integer mobileOTP;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("deviceToken")
    @Expose
    private Object deviceToken;

    @SerializedName("dobStatus")
    @Expose
    private boolean dobStatus = false;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public Integer getDocumentVerify() {
        return documentVerify;
    }

    public void setDocumentVerify(Integer documentVerify) {
        this.documentVerify = documentVerify;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Boolean isLogin) {
        this.isLogin = isLogin;
    }

    public Boolean getOriginalCustomer() {
        return originalCustomer;
    }

    public void setOriginalCustomer(Boolean originalCustomer) {
        this.originalCustomer = originalCustomer;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getAddUserFromCSV() {
        return addUserFromCSV;
    }

    public void setAddUserFromCSV(Boolean addUserFromCSV) {
        this.addUserFromCSV = addUserFromCSV;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSignuptype() {
        return signuptype;
    }

    public void setSignuptype(String signuptype) {
        this.signuptype = signuptype;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getAffilateCode() {
        return affilateCode;
    }

    public void setAffilateCode(String affilateCode) {
        this.affilateCode = affilateCode;
    }

    public String getUsedAffilateCode() {
        return usedAffilateCode;
    }

    public void setUsedAffilateCode(String usedAffilateCode) {
        this.usedAffilateCode = usedAffilateCode;
    }

    public String getUsedReferralCode() {
        return usedReferralCode;
    }

    public void setUsedReferralCode(String usedReferralCode) {
        this.usedReferralCode = usedReferralCode;
    }

    public String getHearFrom() {
        return hearFrom;
    }

    public void setHearFrom(String hearFrom) {
        this.hearFrom = hearFrom;
    }

    public Integer getAffilateLevel() {
        return affilateLevel;
    }

    public void setAffilateLevel(Integer affilateLevel) {
        this.affilateLevel = affilateLevel;
    }

    public List<Object> getAffilateUserId() {
        return affilateUserId;
    }

    public void setAffilateUserId(List<Object> affilateUserId) {
        this.affilateUserId = affilateUserId;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Integer getMobileOTP() {
        return mobileOTP;
    }

    public void setMobileOTP(Integer mobileOTP) {
        this.mobileOTP = mobileOTP;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(Object deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isDobStatus() {
        return dobStatus;
    }

    public void setDobStatus(boolean dobStatus) {
        this.dobStatus = dobStatus;
    }
}
