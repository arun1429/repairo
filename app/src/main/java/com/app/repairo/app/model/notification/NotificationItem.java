
package com.app.repairo.app.model.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationItem {

    @SerializedName("notificationId")
    @Expose
    private String notificationId ;
    @SerializedName("_id")
    @Expose
    private String _id ;
    @SerializedName("userId")
    @Expose
    private String userId ;
    @SerializedName("message")
    @Expose
    private String message ;
    @SerializedName("heading")
    @Expose
    private String heading ;
   @SerializedName("createdAt")
    @Expose
    private long createdAt ;
    @SerializedName("updatedAt")
    @Expose
    private long updatedAt ;

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
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

    public double get__v() {
        return __v;
    }

    public void set__v(double __v) {
        this.__v = __v;
    }

    @SerializedName("__v")
    @Expose
    private double __v ;
}
