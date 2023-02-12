package com.app.repairo.app.apis;

import com.app.repairo.app.model.address.AddressList;
import com.app.repairo.app.model.bookings.BookingsData;
import com.app.repairo.app.model.homenewmodel.HomeAllProduct;
import com.google.gson.JsonObject;
import com.app.repairo.app.model.address.AddressData;
import com.app.repairo.app.model.homenewmodel.HomeResponse;
import com.app.repairo.app.model.logout.Logout;
import com.app.repairo.app.model.login.LoginResponse;
import com.app.repairo.app.model.products.ProductDetailsData;
import com.app.repairo.app.model.products.ProductsData;
import com.app.repairo.app.model.ratingsreview.ReviewResponse;
import com.app.repairo.app.model.subcategory.SubCateResponse;
import com.app.repairo.app.model.userprofile.UserProfile;
import com.app.repairo.app.model.verifymobile.OtpVerifyPost;
import com.app.repairo.app.model.verifymobile.OtpVerifyRes;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    //login apis call
    @Multipart
    @POST("login")
    Observable<LoginResponse> loginApisCall(@Part("mobile") RequestBody mobile);

      //verify otp in mobile
    @POST("verify-otp")
    Observable<OtpVerifyRes> verifyOtpApisCall(@Body OtpVerifyPost otpVerifyPost);

    @GET("fetch-homepage")
    Observable<HomeResponse> getHomeData();

    @Multipart
    @POST("fetch-subcategories")
    Observable<SubCateResponse> getSubCateData(@Part("category_id") RequestBody category_id);

    @Multipart
    @POST("my-booking-list")
    Observable<BookingsData> getBookingsData(@Part("userId") RequestBody userId);

    @Multipart
    @POST("fetch-dealers-list")
    Observable<ProductsData> getDealersList(@Part("category_id") RequestBody category_id);

    @Multipart
    @POST("fetch-dealer-page")
    Observable<ProductDetailsData> getDealerDetails(@Part("dealer_id") RequestBody dealer_id);

    @Multipart
    @POST("fetch-address-list")
    Observable<AddressData> getUserAddress(@Part("userId") RequestBody userId);

    @Multipart
    @POST("delete-address")
    Observable<LoginResponse> deleteAddress(@Part("userId") RequestBody userId,@Part("addressId") RequestBody addressId);

    @Multipart
    @POST("update-address")
    Observable<LoginResponse> updateAddress(@Part("userId") RequestBody userId,@Part("addressId") RequestBody addressId,@Part("address") RequestBody address,@Part("city") RequestBody city,@Part("state") RequestBody state,@Part("pincode") RequestBody pincode,@Part("is_default") RequestBody isdefault,@Part("landmark") RequestBody landmark);

    @Multipart
    @POST("my-profile")
    Observable<OtpVerifyRes> getProfileData(@Part("userId") RequestBody userId);

    @Multipart
    @POST("update-profile")
    Observable<LoginResponse> updateProfileData(@Part("userId") RequestBody userId,@Part("name") RequestBody name,@Part("email") RequestBody email);

    @Multipart
    @POST("cancel-booking")
    Observable<LoginResponse> cancelBooking(@Part("userId") RequestBody userId,@Part("bookingId") RequestBody bookingId,@Part("cancelReason") RequestBody cancelReason);

    @Multipart
    @POST("add-address")
    Observable<LoginResponse> addAddress(@Part("userId") RequestBody userId,@Part("address") RequestBody address,@Part("city") RequestBody city,@Part("state") RequestBody state,@Part("pincode") RequestBody pincode,@Part("is_default") RequestBody is_default,@Part("landmark") RequestBody landmark);

    @Multipart
    @POST("book-service")
    Observable<LoginResponse> bookService(@Part("userId") RequestBody userId,@Part("name") RequestBody name,@Part("addressId") RequestBody addressId,@Part("email") RequestBody email,@Part("mobile") RequestBody mobile,@Part("message") RequestBody message,@Part("dealer_id") RequestBody dealer_id,@Part("paymentId") RequestBody paymentId);

    @Multipart
    @POST("save-emergency-booking")
    Observable<LoginResponse> saveEmergencyBooking(@Part("userId") RequestBody userId,@Part("name") RequestBody name,@Part("addressId") RequestBody addressId,@Part("email") RequestBody email,@Part("mobile") RequestBody mobile,@Part("message") RequestBody message,@Part("category_id") RequestBody category_id);

    @Multipart
    @POST("mechanic-register")
    Observable<LoginResponse> mechanicRegister(@Part("name") RequestBody name,@Part("email") RequestBody email,@Part("mobile") RequestBody mobile,@Part("dob") RequestBody dob,@Part("profession") RequestBody profession,@Part("aadhar_card") RequestBody aadhar_card);

    @Multipart
    @POST("save-review")
    Observable<LoginResponse> saveReview(@Part("userId") RequestBody userId,@Part("dealer_id") RequestBody dealer_id,@Part("review") RequestBody review,@Part("rating") RequestBody rating,@Part("review_title") RequestBody review_title);


}
