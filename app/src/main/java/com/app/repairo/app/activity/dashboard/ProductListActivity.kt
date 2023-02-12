package com.app.repairo.app.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.LinearLayout
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
import com.app.repairo.app.adapter.ServiceAdapter
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.products.ProductsData
import com.app.repairo.app.model.products.ProductsList
import com.app.repairo.app.model.subcategory.SubCateResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_product.editSearch
import kotlinx.android.synthetic.main.activity_product.imageBanner
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.ArrayList

class ProductListActivity : AppCompatActivity() {
    private var txtHeader :TextView? = null
    private var imageBack :RelativeLayout? = null
    private var searchOption :RelativeLayout? = null
    private var searchCard :CardView? = null
    private var listProducts :RecyclerView? = null
    private var productsAdapter : ProductsAdapter? = null
    private var dataList: ArrayList<ProductsList>? = null
    private var dataListAll: ArrayList<ProductsList>? = null
    private var viewHome : LinearLayout? = null
    private var viewBooking : LinearLayout? = null
    private var viewEmergency : LinearLayout? = null
    private var viewProfile : LinearLayout? = null
    var headerTxt :String? = ""
    var categoryId :String? = ""
         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
             txtHeader = findViewById(R.id.txtHeader)
             imageBack = findViewById(R.id.imageBack)
             searchOption = findViewById(R.id.searchOption)
             searchCard = findViewById(R.id.searchCard)
             listProducts = findViewById(R.id.listProducts)
             imageBack!!.setOnClickListener {
                 finish()
             }
             searchOption!!.setOnClickListener {
                 if(searchCard!!.visibility == View.VISIBLE){
                     searchCard!!.visibility = View.GONE
                 }else {
                     searchCard!!.visibility = View.VISIBLE
                 }
             }
             categoryId = intent.getStringExtra("categoryId")
             headerTxt = intent.getStringExtra("header")
             txtHeader!!.setText(headerTxt)
             viewHome =findViewById(R.id.viewHome)
             viewBooking =findViewById(R.id.viewBooking)
             viewEmergency =findViewById(R.id.viewEmergency)
             viewProfile =findViewById(R.id.viewProfile)
             viewBooking!!.setOnClickListener {
                 val intent = Intent(this@ProductListActivity, BookingListActivity::class.java)
                 startActivity(intent)
             }
             viewEmergency!!.setOnClickListener {
                 val intent = Intent(this@ProductListActivity, EmergencyActivity::class.java)
                 startActivity(intent)
             }
             viewProfile!!.setOnClickListener {
                 val intent = Intent(this@ProductListActivity, MyProfileActivity::class.java)
                 startActivity(intent)
             }
             fetchDealersApisCall()
             Glide.with(this@ProductListActivity)
                 .load("https://www.casioindiashop.co.in/mailer/demo/public/home-banners/1.png").into(imageBanner)
             editSearch.addTextChangedListener(object : TextWatcher {
                 override fun afterTextChanged(s: Editable?) {
                     var newString = s.toString()
                     if (newString.length <= 1) {
                         dataList = ArrayList()
                         dataList!!.clear()
                         dataList = dataListAll
                         productsAdapter!!.updateAdapter(dataList)
                     } else{
                         dataList = ArrayList()
                         dataList!!.clear()
                         for (i in dataListAll!!.indices) {
                             if(dataListAll!!.get(i).business_name.toString().toLowerCase().contains(newString.toString().toLowerCase())){
                                 dataList!!.add(dataListAll!!.get(i))
                             }
                         }
                         productsAdapter!!.updateAdapter(dataList)
                     }

                 }

                 override fun beforeTextChanged(
                     s: CharSequence?,
                     start: Int,
                     count: Int,
                     after: Int
                 ) {
                 }

                 override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                 }
             })
             
    }


    private fun fetchDealersApisCall() {
        var categoryID = RequestBody.create("text/plain".toMediaTypeOrNull(),categoryId.toString());
        val progressBarHandler = ProgressBarHandler(this@ProductListActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@ProductListActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.getDealersList(categoryID)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ProductsData> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(productsData: ProductsData) {
                    Log.e("TAG", "productsData : $productsData")
                    progressBarHandler.hide()
                    if (productsData.success == 1) {
                        if(productsData.data.size !=0){
                            dataList = ArrayList()
                            dataListAll = ArrayList()
                            dataList = productsData.data
                            dataListAll = productsData.data
                            productsAdapter = ProductsAdapter(this@ProductListActivity, dataList,headerTxt)
                            listProducts!!.setLayoutManager(
                                LinearLayoutManager(this@ProductListActivity,
                                    LinearLayoutManager.VERTICAL, false)
                            )
                            listProducts!!.adapter = productsAdapter
                        }

                    } else {
                        Toast.makeText(
                            this@ProductListActivity,
                            productsData.message,
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