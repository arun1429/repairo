package com.app.repairo.app.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
import com.app.repairo.app.adapter.BookingsAdapter
import com.app.repairo.app.adapter.ProductsAdapter
import com.app.repairo.app.adapter.ServiceAdapter
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.bookings.BookingList
import com.app.repairo.app.model.bookings.BookingsData
import com.app.repairo.app.model.products.ProductsList
import com.app.repairo.app.model.subcategory.SubCateResponse
import com.app.repairo.app.utils.Preferences
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.ArrayList

class BookingListActivity : AppCompatActivity() {
    private var imageBack :RelativeLayout? = null
    private var listBookings :RecyclerView? = null
    private var bookingsAdapter : BookingsAdapter? = null
    private var dataList: ArrayList<BookingList>? = null
    private var viewHome : LinearLayout? = null
    private var viewBooking : LinearLayout? = null
    private var viewEmergency : LinearLayout? = null
    private var viewProfile : LinearLayout? = null
         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookings)
             imageBack = findViewById(R.id.imageBack)
             listBookings = findViewById(R.id.listBookings)
             imageBack!!.setOnClickListener {
                 finish()
             }
             viewHome =findViewById(R.id.viewHome)
             viewBooking =findViewById(R.id.viewBooking)
             viewEmergency =findViewById(R.id.viewEmergency)
             viewProfile =findViewById(R.id.viewProfile)
             viewHome!!.setOnClickListener {
                 val intent = Intent(this@BookingListActivity, HomeActivity::class.java)
                 startActivity(intent)
             }
             viewEmergency!!.setOnClickListener {
                 val intent = Intent(this@BookingListActivity, EmergencyActivity::class.java)
                 startActivity(intent)
             }
             viewProfile!!.setOnClickListener {
                 val intent = Intent(this@BookingListActivity, MyProfileActivity::class.java)
                 startActivity(intent)
             }

    }

    override fun onResume() {
        super.onResume()
        fetchBookingApisCall()
    }
    private fun fetchBookingApisCall() {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),
            Preferences.getString(this@BookingListActivity,"id"));
        val progressBarHandler = ProgressBarHandler(this@BookingListActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@BookingListActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.getBookingsData(userID)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BookingsData> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(bookingsData: BookingsData) {
                    Log.e("TAG", "bookingsData : $bookingsData")
                    progressBarHandler.hide()
                    if (bookingsData.success == 1) {
                        if(bookingsData.data.size !=0){
                            dataList = ArrayList()
                            dataList = bookingsData.data
                            bookingsAdapter = BookingsAdapter(this@BookingListActivity, dataList)
                            listBookings!!.setLayoutManager(
                                LinearLayoutManager(this@BookingListActivity,
                                    LinearLayoutManager.VERTICAL, false)
                            )
                            listBookings!!.adapter = bookingsAdapter
                        }

                    } else {
                        Toast.makeText(
                            this@BookingListActivity,
                            bookingsData.message,
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