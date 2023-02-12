//package com.app.repairo.app.activity.rating_and_reviews;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.app.repairo.app.utils.ConstantValues;
//import com.google.gson.JsonObject;
//import com.app.repairo.app.R;
//
//import com.app.repairo.app.adapter.ReviewAdapter;
//import com.app.repairo.app.apis.ApiClient;
//import com.app.repairo.app.apis.ApiInterface;
//import com.app.repairo.app.custom.OkDialog;
//import com.app.repairo.app.custom.ProgressBarHandler;
//import com.app.repairo.app.model.ratingsreview.ReviewData;
//import com.app.repairo.app.model.ratingsreview.ReviewResponse;
//import com.app.repairo.app.utils.Preferences;
//
//import java.util.ArrayList;
//
//import io.reactivex.Observable;
//import io.reactivex.Observer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//
//import static com.app.repairo.app.utils.ConstantValues.KEY_IS_LOGIN;
//import static com.app.repairo.app.utils.ConstantValues.STOREUID;
//
//public class AddReview extends BaseActivity {
//    ImageView img_product_image;
//    String image1, name,product_id,st_RatingValue,storeID = "";
//    int productDisplayID = 0;
//    TextView you_are_reviewing, reviewCountTextView,tvRatingValue;
//    EditText etext_your_thoughts;
//    String msg,customer_id;
//    SharedPreferences sharedPreferences;
//    ProgressBarHandler progressBarHandler;
//    RatingBar rating_bar;
//    ArrayList<ReviewData> reviewList;
//    RecyclerView reviewRecyclerView;
//    ReviewAdapter reviewAdapter;
//    Button btn_add_review;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getLayoutInflater().inflate(R.layout.activity_add_review, frameLayout);
//        image1 = getIntent().getStringExtra("imgurl");
//        name = getIntent().getStringExtra("name");
//        product_id = getIntent().getStringExtra("product_id");
//        productDisplayID = getIntent().getIntExtra("productDisplayID",0);
//        you_are_reviewing = (TextView) findViewById(R.id.you_are_reviewing);
//        reviewCountTextView = (TextView)findViewById(R.id.reviewCountTextView);
//        img_product_image = (ImageView) findViewById(R.id.img_product_image);
//        etext_your_thoughts=(EditText)findViewById(R.id.etext_your_thoughts);
//        btn_add_review=(Button) findViewById(R.id.btn_add_review);
//        rating_bar=(RatingBar)findViewById(R.id.simpleRatingBar);
//        reviewRecyclerView = (RecyclerView)findViewById(R.id.reviewRecyclerView);
//        sharedPreferences = getSharedPreferences("budzer", MODE_PRIVATE);
//        cart_count = (TextView) findViewById(R.id.cart_count);
//        customer_id =   Preferences.getString(AddReview.this, ConstantValues.CUSTOMER_ID);
//        reviewList = new ArrayList<>();
//        progressBarHandler=new ProgressBarHandler(AddReview.this);
//        Glide.with(AddReview.this)
//                .load(image1)
//                .into(img_product_image);
//        you_are_reviewing.setText(getResources().getString(R.string.you_are_reviewing_product_name) + " " + name);
//        progressBarHandler = new ProgressBarHandler(AddReview.this);
//        st_RatingValue = String.valueOf(rating_bar.getRating());
//        rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
//            {
//                ratingBar.setRating(rating);
//                st_RatingValue =(String.valueOf(rating));
//                System.out.println("Print rating value::"+st_RatingValue);
//            }
//        });
//        storeID = Preferences.getString(AddReview.this, STOREUID);
//        btn_add_review.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               productAddReview();
//            }
//        });
//        getProductReviewDetails();
//    }
//    public void getProductReviewDetails() {
//        final ProgressBarHandler progressBarHandler = new ProgressBarHandler(AddReview.this);
//        progressBarHandler.show();
//        ApiInterface apiInterface = ApiClient.getRetrofitClientForAuth(AddReview.this).create(ApiInterface.class);
//        Observable<ReviewResponse> observable = apiInterface.getproductReviewDetails(String.valueOf(productDisplayID),storeID);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ReviewResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ReviewResponse reviewResponse) {
//                        progressBarHandler.hide();
//                        if(reviewResponse.getMeta().getStatus()){
//                            reviewList =reviewResponse.getData();
//                            int size = reviewList.size();
//                            reviewCountTextView.setText("Customer Reviews:"+ size + " item(s)");
//                            reviewAdapter = new ReviewAdapter(getApplicationContext(),reviewList);
//                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                            reviewRecyclerView.setLayoutManager(mLayoutManager);
//                            reviewRecyclerView.setAdapter(reviewAdapter);
//
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        progressBarHandler.hide();
//                        System.out.println(e.getMessage());
//                        Log.d("TAG@123", e.getMessage());
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        progressBarHandler.hide();
//                        System.out.println("");
//                    }
//                });
//    }
//    private void productAddReview() {
//        if(Preferences.getBoolean(AddReview.this, KEY_IS_LOGIN)){
//            String ratingPoint = st_RatingValue.split("\\.")[0];
//            if (st_RatingValue.equalsIgnoreCase("0.0")){
//
//                OkDialog.INSTANCE.show(AddReview.this, "please rate our product");
//                return;
//            }
//            String st_etext_your_thoughts = etext_your_thoughts.getText().toString();
//            if (st_etext_your_thoughts.equals("")) {
//                OkDialog.INSTANCE.show(AddReview.this, getString(R.string.please_enter_your_review));
//            }else {
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("productDisplayId", productDisplayID);
//            jsonObject.addProperty("productName", name);
//            jsonObject.addProperty("storeId", storeID);
//            jsonObject.addProperty("review", st_etext_your_thoughts);
//            jsonObject.addProperty("reviewRating", st_RatingValue);
//            final ProgressBarHandler progressBarHandler = new ProgressBarHandler(AddReview.this);
//            progressBarHandler.show();
//            ApiInterface apiInterface = ApiClient.getRetrofitClientForHeader(AddReview.this).create(ApiInterface.class);
//            Observable<JsonObject> observable = apiInterface.addreview(jsonObject);
//            observable.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<JsonObject>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                        }
//                        @Override
//                        public void onNext(JsonObject response) {
//                            Log.e("TAG","response : "+response);
//                            progressBarHandler.hide();
//                            try {
//                                JsonObject jsonObject2 = response.getAsJsonObject("meta");
//                                String status = jsonObject2.get("status").toString();
//                                String msg = jsonObject2.get("msg").toString();
//                                if(status.equalsIgnoreCase("true")){
//                                    etext_your_thoughts.setText("");
//                                    rating_bar.setRating(0);
//                                    Toast.makeText(AddReview.this, msg.substring(1,msg.length()-1), Toast.LENGTH_SHORT).show();
//                                    onBackPressed();
//                                }
//
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            e.printStackTrace();
//                            progressBarHandler.hide();
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            progressBarHandler.hide();
//                        }
//                    });
//            }
//        }else {
//            OkDialog.INSTANCE.show(AddReview.this, "Please login first");
//        }
//
//    }
//
//
//}
