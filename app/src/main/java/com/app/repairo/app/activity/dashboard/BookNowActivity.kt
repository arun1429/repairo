package com.app.repairo.app.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import com.app.repairo.app.R
import androidx.appcompat.app.AppCompatActivity
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.OkDialog
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.login.LoginResponse
import com.app.repairo.app.model.verifymobile.OtpVerifyRes
import com.app.repairo.app.utils.Preferences
import com.razorpay.Checkout
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_book_now.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.ArrayList
import org.json.JSONObject

import android.app.Activity
import com.razorpay.PaymentResultListener
import java.lang.Exception


class BookNowActivity : AppCompatActivity() , PaymentResultListener {
    private var imageBack :RelativeLayout? = null
    private var button : LinearLayout? = null
    private var editFullName : EditText? = null
    private var addressId : String = ""
    private var dealer_id :String = ""
    val checkout = Checkout()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_now)
        imageBack = findViewById(R.id.imageBack)
        button = findViewById(R.id.button)
        editFullName = findViewById(R.id.editFullName)
        imageBack!!.setOnClickListener {
            finish()
        }
        Checkout.preload(getApplicationContext());
        checkout.setImage(R.drawable.logo)
        checkout.setKeyID("rzp_test_VTrvsZuGJVovlZ");
        editAddressLayout.setOnClickListener {
            val intent = Intent(this@BookNowActivity, SelectAddressListActivity::class.java)
            startActivityForResult(intent,200)
        }
        dealer_id = intent.getStringExtra("dealer_id").toString()
        button!!.setOnClickListener {
            if (editFullName!!.getText().toString() == "") {
                OkDialog.show(this@BookNowActivity, "Please enter full name")
            }else if (editEmail!!.getText().toString() == "") {
                OkDialog.show(this@BookNowActivity, "Please enter email")
            }else if (editMobile!!.getText().toString() == "") {
                OkDialog.show(this@BookNowActivity, "Please enter phone number")
            }else if (addressId == "") {
                OkDialog.show(this@BookNowActivity, "Please select address")
            }else if (editMessage!!.getText().toString() == "") {
                OkDialog.show(this@BookNowActivity, "Please enter message")
            }else {
                //comment this line for make payment active and uncomment all commnted lines

                updateProfileApisCall("")


//                val activity: Activity = this
//                try {
//                    val options = JSONObject()
//                    options.put("name", editFullName!!.getText().toString())
//                    options.put("description", "Book Service")
//                    options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
//                 //   options.put("order_id", "order_DBJOWzybf0sJbb") //from response of step 3.
//                    options.put("theme.color", "#FC983F")
//                    options.put("currency", "INR")
//                    options.put("amount", "50000") //pass amount in currency subunits
//                    options.put("prefill.email", editEmail!!.getText().toString())
//                    options.put("prefill.contact", editMobile!!.getText().toString())
//                    val retryObj = JSONObject()
//                    retryObj.put("enabled", true)
//                    retryObj.put("max_count", 4)
//                    options.put("retry", retryObj)
//                    checkout.open(activity, options)
//                } catch (e: Exception) {
//                    Log.e("TAG", "Error in starting Razorpay Checkout", e)
//                }

            }

        }
        fetchProfileApisCall()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 200) {
            addressId = data!!.getStringExtra("SelectedAddressId").toString()
           var  address = data!!.getStringExtra("SelectedAddress").toString()
            editAddress.setText(address)

        }
    }
    private fun fetchProfileApisCall() {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),Preferences.getString(this@BookNowActivity,"id"));
        val progressBarHandler = ProgressBarHandler(this@BookNowActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@BookNowActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.getProfileData(userID)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<OtpVerifyRes> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(otpVerifyRes: OtpVerifyRes) {
                    Log.e("TAG", "otpVerifyRes : $otpVerifyRes")
                    progressBarHandler.hide()
                    if (otpVerifyRes.success == 1) {
                        if(otpVerifyRes.data  != null){
                            editFullName!!.setText(otpVerifyRes.data.name)
                            editEmail!!.setText(otpVerifyRes.data.email)
                            editMobile!!.setText(otpVerifyRes.data.mobile)
                        }

                    } else {
                        Toast.makeText(
                            this@BookNowActivity,
                            otpVerifyRes.message,
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
    private fun updateProfileApisCall(paymentId : String) {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),
            Preferences.getString(this@BookNowActivity,"id"));
        var name = RequestBody.create("text/plain".toMediaTypeOrNull(),editFullName!!.text.toString());
        var email = RequestBody.create("text/plain".toMediaTypeOrNull(),editEmail!!.text.toString());
        var mobile = RequestBody.create("text/plain".toMediaTypeOrNull(),editMobile!!.text.toString());
        var message = RequestBody.create("text/plain".toMediaTypeOrNull(),editMessage!!.text.toString());
        var addressId = RequestBody.create("text/plain".toMediaTypeOrNull(),addressId);
        var dealer_id = RequestBody.create("text/plain".toMediaTypeOrNull(),dealer_id);
        var paymentId = RequestBody.create("text/plain".toMediaTypeOrNull(),paymentId);
        val progressBarHandler = ProgressBarHandler(this@BookNowActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@BookNowActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.bookService(userID,name,addressId,email,mobile,message,dealer_id,paymentId)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(loginResponse: LoginResponse) {
                    Log.e("TAG", "loginResponse : $loginResponse")
                    progressBarHandler.hide()
                    Toast.makeText(
                        this@BookNowActivity,
                        loginResponse.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    if (loginResponse.success == 1) {
                        finish()
                    } else {

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

    override fun onPaymentSuccess(p0: String?) {
        updateProfileApisCall(p0.toString())
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Log.e("TAG", "error "+p1)
        Toast.makeText(
            this@BookNowActivity,
            p1,
            Toast.LENGTH_SHORT
        ).show()
    }
}