package com.app.repairo.app.model.bookings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.app.repairo.app.model.products.ProductsList;

import java.util.ArrayList;

public class BookingList {
    @SerializedName("id")
    @Expose
    private String id="";
    @SerializedName("userid")
    @Expose
    private String userid="";

    @SerializedName("dealer_id")
    @Expose
    private String dealer_id="";

    @SerializedName("address_id")
    @Expose
    private String address_id="";

    @SerializedName("name")
    @Expose
    private String name="";

    @SerializedName("email")
    @Expose
    private String email="";
    @SerializedName("mobile")
    @Expose
    private String mobile="";
    @SerializedName("message")
    @Expose
    private String message="";
    @SerializedName("address")
    @Expose
    private String address="";
    @SerializedName("city")
    @Expose
    private String city="";
    @SerializedName("state")
    @Expose
    private String state="";
    @SerializedName("pincode")
    @Expose
    private String pincode="";
    @SerializedName("address_name")
    @Expose
    private String address_name="";

   @SerializedName("booking_status")
    @Expose
    private String booking_status="";

   @SerializedName("cancel_datetime")
    @Expose
    private String cancel_datetime="";

   @SerializedName("created_at")
    @Expose
    private String created_at="";
    @SerializedName("business_name")
    @Expose
    private String business_name="";
    @SerializedName("category_name")
    @Expose
    private String category_name="";
    @SerializedName("rating_total")
    @Expose
    private String rating_total="";

    public String getId() {
        return id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getRating_total() {
        return rating_total;
    }

    public void setRating_total(String rating_total) {
        this.rating_total = rating_total;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public ArrayList<ProductsList.ReviewList> getReviews_list() {
        return reviews_list;
    }

    public void setReviews_list(ArrayList<ProductsList.ReviewList> reviews_list) {
        this.reviews_list = reviews_list;
    }

    @SerializedName("category_image")
    @Expose
    private String category_image="";
    @SerializedName("reviews_list")
    @Expose
    private ArrayList<ProductsList.ReviewList> reviews_list= new ArrayList<>();
    public class ReviewList {
        @SerializedName("id")
        @Expose
        private String id = "";
        @SerializedName("userid")
        @Expose
        private String userid = "";
        @SerializedName("dealer_id")
        @Expose
        private String dealer_id = "";

        @SerializedName("title")
        @Expose
        private String title = "";

        @SerializedName("rating")
        @Expose
        private String rating = "";
        @SerializedName("review")
        @Expose
        private String review = "";
        @SerializedName("updated_at")
        @Expose
        private String updated_at = "";
        @SerializedName("created_at")
        @Expose
        private String created_at = "";

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

        public String getDealer_id() {
            return dealer_id;
        }

        public void setDealer_id(String dealer_id) {
            this.dealer_id = dealer_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @SerializedName("name")
        @Expose
        private String name = "";
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

    public String getDealer_id() {
        return dealer_id;
    }

    public void setDealer_id(String dealer_id) {
        this.dealer_id = dealer_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public String getCancel_datetime() {
        return cancel_datetime;
    }

    public void setCancel_datetime(String cancel_datetime) {
        this.cancel_datetime = cancel_datetime;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @SerializedName("updated_at")
    @Expose
    private String updated_at="";




}
