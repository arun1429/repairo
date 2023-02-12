package com.app.repairo.app.model.userprofile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillingAddress {

    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("streetNumber")
    @Expose
    private String streetNumber;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("administrativeAreaLevel")
    @Expose
    private String administrativeAreaLevel;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("neighborhood")
    @Expose
    private String neighborhood;
    @SerializedName("currentLat")
    @Expose
    private Double currentLat;
    @SerializedName("currentLong")
    @Expose
    private Double currentLong;
    @SerializedName("formattedAddress")
    @Expose
    private String formattedAddress;
    @SerializedName("subLocality")
    @Expose
    private String subLocality;
    @SerializedName("route")
    @Expose
    private String route;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAdministrativeAreaLevel() {
        return administrativeAreaLevel;
    }

    public void setAdministrativeAreaLevel(String administrativeAreaLevel) {
        this.administrativeAreaLevel = administrativeAreaLevel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(Double currentLat) {
        this.currentLat = currentLat;
    }

    public Double getCurrentLong() {
        return currentLong;
    }

    public void setCurrentLong(Double currentLong) {
        this.currentLong = currentLong;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getSubLocality() {
        return subLocality;
    }

    public void setSubLocality(String subLocality) {
        this.subLocality = subLocality;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}