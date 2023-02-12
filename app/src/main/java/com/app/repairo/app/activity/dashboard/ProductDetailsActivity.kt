package com.app.repairo.app.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.app.repairo.app.R
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.app.repairo.app.adapter.ProductsAdapter
import com.app.repairo.app.adapter.ReviewsAdapter
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.products.ProductDetailsData
import com.app.repairo.app.model.products.ProductsData
import com.app.repairo.app.model.products.ProductsList
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product_details.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.ArrayList

class ProductDetailsActivity : AppCompatActivity() {
    private var txtHeader :TextView? = null
    private var imageBack :RelativeLayout? = null
    private var listReviews :RecyclerView? = null
    private var reviewsAdapter : ReviewsAdapter? = null
    private var dataList: ArrayList<ProductsList.ReviewList>? = null
    private var productsListData: ProductsList? = null
    var headerTxt :String? = ""
    var dealer_id :String? = ""
    var category_name :String? = ""
         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
             txtHeader = findViewById(R.id.txtHeader)
             imageBack = findViewById(R.id.imageBack)
             listReviews = findViewById(R.id.listReviews)
             imageBack!!.setOnClickListener {
                 finish()
             }
             button.setOnClickListener {
                 val intent = Intent(this@ProductDetailsActivity, BookNowActivity::class.java)
                 intent.putExtra("dealer_id",dealer_id)
                 startActivity(intent)
             }
             buttonAddReview.setOnClickListener {
                  startActivity(
                      Intent(this@ProductDetailsActivity, AddReviewActivity::class.java)
                          .putExtra("dealer_id", productsListData!!.id)
                          .putExtra("business_name", productsListData!!.business_name)
                          .putExtra("category_name", category_name)
                  )
             }
             headerTxt = intent.getStringExtra("header")
             dealer_id = intent.getStringExtra("dealer_id")
             category_name = intent.getStringExtra("headerTxt")
             txtHeader!!.setText(headerTxt)
             Glide.with(this@ProductDetailsActivity)
                 .load("https://www.casioindiashop.co.in/mailer/demo/public/home-banners/1.png").into(imageBanner)


         }

    override fun onResume() {
        super.onResume()
        fetchServiceDetailsApisCall()
    }

    private fun fetchServiceDetailsApisCall() {
        var dealer_id = RequestBody.create("text/plain".toMediaTypeOrNull(),dealer_id.toString());
        val progressBarHandler = ProgressBarHandler(this@ProductDetailsActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@ProductDetailsActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.getDealerDetails(dealer_id)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ProductDetailsData> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(productDetailsData: ProductDetailsData) {
                    Log.e("TAG", "productDetailsData : $productDetailsData")
                    progressBarHandler.hide()
                    if (productDetailsData.success == 1) {
                        productsListData = productDetailsData.data
                        txtName.setText(productDetailsData.data.business_name)
                        txtDescription.setText(productDetailsData.data.description)

                        if(productDetailsData.data.rating_total.equals("0")){
                            layoutRating.visibility = View.GONE
                            txtNoReview.visibility = View.VISIBLE
                        }else {
                            layoutRating.visibility = View.VISIBLE
                            txtNoReview.visibility = View.GONE
                            txtRating.setText(productDetailsData.data.rating_total)
                            txtRatingCount.setText(productDetailsData.data.reviews_list.size.toString()+" Ratings")
                        }
                        if(productDetailsData.data.reviews_list.size !=0){
                            dataList = ArrayList()
                            dataList = productDetailsData.data.reviews_list
                            reviewsAdapter = ReviewsAdapter(this@ProductDetailsActivity, dataList)
                            listReviews!!.setLayoutManager(
                                LinearLayoutManager(this@ProductDetailsActivity,
                                    LinearLayoutManager.VERTICAL, false)
                            )
                            listReviews!!.adapter = reviewsAdapter
                        }

                    } else {
                        Toast.makeText(
                            this@ProductDetailsActivity,
                            productDetailsData.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onError(e: Throwable) {
                    progressBarHandler.hide()
                    println(e.message)
                }

                override fun onComplete() {
                    progressBarHandler.hide()
                    println("")
                }
            })
    }
}