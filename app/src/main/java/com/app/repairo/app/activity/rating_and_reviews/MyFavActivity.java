//package com.app.repairo.app.activity.rating_and_reviews;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.Toast;
//
//import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
//import com.ethanhua.skeleton.Skeleton;
//import com.google.gson.JsonObject;
//import com.app.repairo.app.R;
//import com.app.repairo.app.activity.dashboard.HomeActivity;
//import com.app.repairo.app.activity.product.ProductDetailActivity;
//import com.app.repairo.app.adapter.FavAdapter;
//import com.app.repairo.app.apis.ApiClient;
//import com.app.repairo.app.apis.ApiInterface;
//import com.app.repairo.app.custom.ProgressBarHandler;
//import com.app.repairo.app.model.FavListResponse;
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
//import static com.app.repairo.app.utils.ConstantValues.STOREUID;
//
//public class MyFavActivity extends BaseActivity implements FavAdapter.ListItemClickListener {
//    Button share_slected_favs;
//    RecyclerView recyclerView;
//    ArrayList<FavListResponse.Data.WishList> wishLists=new ArrayList<>();
//    FavAdapter adapter;
//    RecyclerViewSkeletonScreen skeleton;
//    FrameLayout nodata;
//    String storeID = "";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getLayoutInflater().inflate(R.layout.activity_my_fav,frameLayout);
//        share_slected_favs=findViewById(R.id.share_slected_favs);
//        recyclerView=findViewById(R.id.recyclerView);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        adapter = new FavAdapter( wishLists,MyFavActivity.this,MyFavActivity.this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(mLayoutManager);
//        storeID = Preferences.getString(MyFavActivity.this, STOREUID);
//        nodata=findViewById(R.id.nodatas);
//        findViewById(R.id.btn_back_menu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(MyFavActivity.this, HomeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                finish();
//            }
//        });
//        getFavListApi();
//    }
//    public void getFavListApi(){
//        nodata.setVisibility(View.GONE);
//        skeleton = Skeleton.bind(recyclerView)
//                .adapter(adapter)
//                .load(R.layout.skeleton_item_view)
//                .shimmer(true)
//                .angle(30)
//                .count(6)
//                .show();
//        ApiInterface apiInterface= ApiClient.getRetrofitClientForHeader(MyFavActivity.this).create(ApiInterface.class);
//        Observable<FavListResponse> observable=apiInterface.getFavList(storeID);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<FavListResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(FavListResponse favListResponse) {
//
//                        try {
//                            if(favListResponse.getMeta().getStatus()){
//                                wishLists = new ArrayList<>();
//                                wishLists = favListResponse.getData().getWishList();
//                                Log.e("TAG", "wishLists : "+wishLists.size());
//                                if (wishLists.size()>0){
//
//                                    skeleton.hide();
//                                    nodata.setVisibility(View.GONE);
//                                    recyclerView.setVisibility(View.VISIBLE);
//                                    adapter.update(wishLists);
//                                    skeleton.hide();
//                                    Log.e("TAG", "wishLists 22: "+wishLists.size());
//                                }
//                                else {
//                                    skeleton.hide();
//                                    nodata.setVisibility(View.VISIBLE);
//                                    recyclerView.setVisibility(View.GONE);
//                                }
//                            }
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            skeleton.hide();
//                            nodata.setVisibility(View.VISIBLE);
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println(e.getMessage());
//                        skeleton.hide();
//                        nodata.setVisibility(View.VISIBLE);
//                        recyclerView.setVisibility(View.GONE);
//
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("");
//                    }
//                });
//    }
//    public void deleteWishListApi(String idd, final int index){
//         final ProgressBarHandler progressBarHandler = new ProgressBarHandler(MyFavActivity.this);
//        progressBarHandler.show();
//        ApiInterface apiInterface= ApiClient.getRetrofitClientForHeader(MyFavActivity.this).create(ApiInterface.class);
//        Observable<JsonObject> observable=apiInterface.deleteWishList(storeID,idd);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<JsonObject>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(JsonObject jsonObject) {
//                        try {
//                            String status = jsonObject.getAsJsonObject("meta").get("status").getAsString();
//                            String msg = jsonObject.getAsJsonObject("meta").get("msg").getAsString();
//                            if (status.equalsIgnoreCase("true")) {
//                                progressBarHandler.hide();
//
//                                if(wishLists.size()!=0){
//                                    wishLists.remove(index);
//                                    adapter.notifyItemRemoved(index);
//                                    adapter.notifyItemRangeChanged(index, wishLists.size());
//                                    adapter.notifyDataSetChanged();
//                                    if (wishLists.size()==0){
//                                        nodata.setVisibility(View.VISIBLE);
//                                    }
//                                }
//
//                                Toast.makeText(MyFavActivity.this, msg,Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//    @Override
//    public void onItemSelected(int index) {
//        if(wishLists.size()!=0){
//            deleteWishListApi(wishLists.get(index).getId(),index);
//        }
//
//    }
//    @Override
//    public void onItemView(int index) {
//        if(wishLists.size()!=0){
//            Intent intent = new Intent(MyFavActivity.this, ProductDetailActivity.class);
//            intent.putExtra("id", wishLists.get(index).getProductId());
//            intent.putExtra("productName",wishLists.get(index).getProductName());
//            intent.putExtra("productImage", wishLists.get(index).getProductImg());
//            intent.putExtra("productType",wishLists.get(index).getSku());
//            intent.putExtra("productID",wishLists.get(index).getProductId());
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//
//    }
//
//    @Override
//    public void onItemShare(int index) {
//        if(wishLists.size()!=0){
//            ShareToFriends(wishLists.get(index).getProductName(),wishLists.get(index).getProductImg());
//        }
//    }
//    private void ShareToFriends(String name, String img) {
//        Intent share = new Intent(android.content.Intent.ACTION_SEND);
//        share.setType("text/plain");
//        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//        share.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name + "");
//        share.putExtra(Intent.EXTRA_TEXT, "Product Name: " + name+"\n"+ img);
//        startActivity(Intent.createChooser(share, "Share link!"));
//    }
//}