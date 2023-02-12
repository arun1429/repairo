package com.app.repairo.app.model.userprofile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("shipingAddress")
    @Expose
    private ShipingAddress shipingAddress;
    @SerializedName("billingAddress")
    @Expose
    private BillingAddress billingAddress;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("orderDisplayId")
    @Expose
    private String orderDisplayId;
    @SerializedName("driverOrderStatus")
    @Expose
    private Integer driverOrderStatus;
    @SerializedName("driverId")
    @Expose
    private Integer driverId;


    public ShipingAddress getShipingAddress() {
        return shipingAddress;
    }

    public void setShipingAddress(ShipingAddress shipingAddress) {
        this.shipingAddress = shipingAddress;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderDisplayId() {
        return orderDisplayId;
    }

    public void setOrderDisplayId(String orderDisplayId) {
        this.orderDisplayId = orderDisplayId;
    }

    public Integer getDriverOrderStatus() {
        return driverOrderStatus;
    }

    public void setDriverOrderStatus(Integer driverOrderStatus) {
        this.driverOrderStatus = driverOrderStatus;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
}
