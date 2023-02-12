package com.app.repairo.app.activity.dashboard

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.app.repairo.app.R;
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.app.repairo.app.activity.logreg.LoginActivity
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.login.LoginResponse
import com.app.repairo.app.model.products.ProductsList
import com.app.repairo.app.utils.Preferences
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_booking_details.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

import android.widget.EditText







class BookingDetailsActivity : AppCompatActivity() {
    private var imageBack :RelativeLayout? = null
    var dealer_id :String = ""
    var bookingStatus :String = "Pending"
    var bookingId :String = ""

         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_details)
             imageBack = findViewById(R.id.imageBack)
             imageBack!!.setOnClickListener {
                 finish()
             }
             button.setOnClickListener {
                 showAlertPopup();
             }
             dealer_id = intent.getStringExtra("dealer_id").toString()
             bookingId = intent.getStringExtra("bookingId").toString()
             bookingStatus = intent.getStringExtra("status").toString()
             txtName.setText(intent.getStringExtra("business_name"))
             if(intent.getStringExtra("rating_count").equals("0")){
                 layoutRating.visibility = View.GONE
                 txtNoReview.visibility = View.VISIBLE
             }else {
                 layoutRating.visibility = View.VISIBLE
                 txtNoReview.visibility = View.GONE
                 txtRating.setText(intent.getStringExtra("rating_count"))
                 txtRatingCount.setText(intent.getStringExtra("total_rating_count"))
             }


             txtType.setText(intent.getStringExtra("category_name"))
             txtStatus.setText(intent.getStringExtra("status"))
             txtDate.setText(intent.getStringExtra("date"))
             Glide.with(this@BookingDetailsActivity)
                 .load("https://www.casioindiashop.co.in/mailer/demo/public/home-banners/1.png").into(imageBanner)
             if(bookingStatus.equals("Pending") || bookingStatus.equals("Confirmed")){
                 button.visibility = View.VISIBLE
             }else {
                 button.visibility = View.GONE
             }
         }
    private fun showAlertPopup() {
        val alertDialog = AlertDialog.Builder(this@BookingDetailsActivity)
        val customLayout: View = layoutInflater.inflate(R.layout.custom, null)
        alertDialog.setView(customLayout)
        alertDialog.setCancelable(false)
        alertDialog.setMessage(resources.getString(R.string.cancel_msg))
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                val editText: EditText = customLayout.findViewById(R.id.editTitle)
                if(editText.text.toString().length != 0){
                    cancelBookingApisCall(editText.text.toString())
                }else {
                    Toast.makeText(
                        this@BookingDetailsActivity,
                        "Please enter reason",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val alert: AlertDialog = alertDialog.create()
        alert.getWindow()!!.setBackgroundDrawableResource(android.R.color.white)
        alert.show()
    }
    private fun cancelBookingApisCall(cancelReason : String) {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),
            Preferences.getString(this@BookingDetailsActivity,"id"));
        var bookingId = RequestBody.create("text/plain".toMediaTypeOrNull(),bookingId);
        var cancelReason = RequestBody.create("text/plain".toMediaTypeOrNull(),cancelReason);
        val progressBarHandler = ProgressBarHandler(this@BookingDetailsActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@BookingDetailsActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.cancelBooking(userID,bookingId,cancelReason)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(loginResponse: LoginResponse) {
                    Log.e("TAG", "loginResponse : $loginResponse")
                    progressBarHandler.hide()
                    if (loginResponse.success == 1) {
                       finish()
                    }
                    Toast.makeText(
                        this@BookingDetailsActivity,
                        loginResponse.message,
                        Toast.LENGTH_SHORT
                    ).show()
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