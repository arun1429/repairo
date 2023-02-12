package com.app.repairo.app.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import com.app.repairo.app.R
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.app.repairo.app.adapter.BannerAdapter
import com.app.repairo.app.adapter.CategoryAdapter
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.homenewmodel.BannerList
import com.app.repairo.app.model.homenewmodel.CategoryList
import com.app.repairo.app.model.homenewmodel.HomeResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {
    var viewPager: ViewPager? = null
    var currentPage = 0
    private var imgProfile : RelativeLayout? = null
    private var viewHome : LinearLayout? = null
    private var viewBooking : LinearLayout? = null
    private var viewEmergency : LinearLayout? = null
    private var viewProfile : LinearLayout? = null
    private var imageBanner : ImageView? = null
    private var cardBanner : CardView? = null
    var timer: Timer? = null
    val delay_ms: Long = 2000
    val period_ms: Long = 6000
    var bannerList: ArrayList<BannerList>? = ArrayList()
    private var listCategory :RecyclerView? = null
    private var categoryAdapter : CategoryAdapter? = null
    private var dataList: java.util.ArrayList<CategoryList>? = null
    private var dataListAll: java.util.ArrayList<CategoryList>? = null
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
            viewPager =findViewById(R.id.view_pager)
            imgProfile =findViewById(R.id.imgProfile)
            listCategory =findViewById(R.id.listCategory)
            imageBanner =findViewById(R.id.imageBanner)
            cardBanner =findViewById(R.id.cardBanner)
            imgProfile!!.setOnClickListener {
                val intent = Intent(this@HomeActivity, MyProfileActivity::class.java)
                startActivity(intent)
            }
            viewHome =findViewById(R.id.viewHome)
            viewBooking =findViewById(R.id.viewBooking)
            viewEmergency =findViewById(R.id.viewEmergency)
            viewProfile =findViewById(R.id.viewProfile)
            viewBooking!!.setOnClickListener {
                val intent = Intent(this@HomeActivity, BookingListActivity::class.java)
                startActivity(intent)
            }
            viewEmergency!!.setOnClickListener {
                val intent = Intent(this@HomeActivity, EmergencyActivity::class.java)
                startActivity(intent)
            }
            viewProfile!!.setOnClickListener {
                val intent = Intent(this@HomeActivity, MyProfileActivity::class.java)
                startActivity(intent)
            }
            try {
                fetchHomeDataApisCall()
            }catch (e :Exception){
                e.printStackTrace()
            }
            editSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    var newString = s.toString()
                    if(dataListAll != null){
                    if (newString.length <= 1) {
                        dataList = ArrayList()
                        dataList!!.clear()
                        dataList = dataListAll
                        categoryAdapter!!.updateAdapter(dataList)
                    } else{
                        dataList = ArrayList()
                        dataList!!.clear()
                        for (i in dataListAll!!.indices) {
                          if(dataListAll!!.get(i).category_name.toString().toLowerCase().contains(newString.toString().toLowerCase())){
                              dataList!!.add(dataListAll!!.get(i))
                          }
                        }
                        categoryAdapter!!.updateAdapter(dataList)
                    }
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

    private fun fetchHomeDataApisCall() {
        val progressBarHandler = ProgressBarHandler(this@HomeActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@HomeActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.getHomeData()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<HomeResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(homeResponse: HomeResponse) {
                    Log.e("TAG", "homeResponse : $homeResponse")
                    progressBarHandler.hide()
                    if (homeResponse.success == 1) {
                        if(homeResponse.data.list_services!=null){
                            dataList = ArrayList()
                            dataListAll = ArrayList()
                            dataList = homeResponse.data.list_services
                            dataListAll = homeResponse.data.list_services
                            categoryAdapter = CategoryAdapter(this@HomeActivity, dataList)
                            listCategory!!.setLayoutManager(
                                GridLayoutManager(this@HomeActivity,4)
                            )
                            listCategory!!.adapter = categoryAdapter
                        }
                        if(homeResponse.data.list_banners!=null){
                            bannerList = ArrayList()
                            bannerList = homeResponse.data.list_banners
                            viewPager?.visibility = View.VISIBLE
                            val adapter: PagerAdapter = BannerAdapter(this@HomeActivity, bannerList)
                            viewPager!!.adapter = adapter
                            /*After setting the adapter use the timer */
                            val handler = Handler()
                            val Update = Runnable {
                                if (currentPage == bannerList!!.size) {
                                    currentPage = 0
                                }
                                viewPager!!.setCurrentItem(currentPage++, true)
                            }
                            timer = Timer() // This will create a new Thread
                            timer!!.schedule(object : TimerTask() {
                                // task to be scheduled
                                override fun run() {
                                    handler.post(Update)
                                }
                            }, delay_ms, period_ms)
                        }
                        if(homeResponse.data.banner_image!="") {
                            cardBanner?.visibility = View.VISIBLE
                            Glide.with(this@HomeActivity)
                                .load(homeResponse.data.banner_image).into(imageBanner!!)
                        }
                    } else {
                        Toast.makeText(
                            this@HomeActivity,
                            homeResponse.message,
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