package com.app.repairo.app.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductsList {
    @SerializedName("id")
    @Expose
    private String id="";
    @SerializedName("business_name")
    @Expose
    private String business_name="";
    @SerializedName("description")
    @Expose
    private String description="";
    @SerializedName("category_ids")
    @Expose
    private String category_ids="";

    @SerializedName("dealer_address")
    @Expose
    private String dealer_address="";

    @SerializedName("dealer_city")
    @Expose
    private String dealer_city="";
  @SerializedName("dealer_state")
    @Expose
    private String dealer_state="";
  @SerializedName("dealer_pincode")
    @Expose
    private String dealer_pincode="";
  @SerializedName("service_charge")
    @Expose
    private String service_charge="";
  @SerializedName("rating_total")
    @Expose
    private String rating_total="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(String category_ids) {
        this.category_ids = category_ids;
    }

    public String getDealer_address() {
        return dealer_address;
    }

    public void setDealer_address(String dealer_address) {
        this.dealer_address = dealer_address;
    }

    public String getDealer_city() {
        return dealer_city;
    }

    public void setDealer_city(String dealer_city) {
        this.dealer_city = dealer_city;
    }

    public String getDealer_state() {
        return dealer_state;
    }

    public void setDealer_state(String dealer_state) {
        this.dealer_state = dealer_state;
    }

    public String getDealer_pincode() {
        return dealer_pincode;
    }

    public void setDealer_pincode(String dealer_pincode) {
        this.dealer_pincode = dealer_pincode;
    }

    public String getService_charge() {
        return service_charge;
    }

    public void setService_charge(String service_charge) {
        this.service_charge = service_charge;
    }

    public String getRating_total() {
        return rating_total;
    }

    public void setRating_total(String rating_total) {
        this.rating_total = rating_total;
    }

    public ArrayList<ReviewList> getReviews_list() {
        return reviews_list;
    }

    public void setReviews_list(ArrayList<ReviewList> reviews_list) {
        this.reviews_list = reviews_list;
    }

    @SerializedName("reviews_list")
    @Expose
    private ArrayList<ReviewList> reviews_list= new ArrayList<>();
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
}
