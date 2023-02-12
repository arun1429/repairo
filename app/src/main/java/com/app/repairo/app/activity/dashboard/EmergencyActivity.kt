package com.app.repairo.app.activity.dashboard

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import com.app.repairo.app.R
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

import android.widget.ArrayAdapter

import android.widget.Spinner
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.OkDialog
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.homenewmodel.CategoryList
import com.app.repairo.app.model.homenewmodel.HomeResponse
import com.app.repairo.app.model.login.LoginResponse
import com.app.repairo.app.model.verifymobile.OtpVerifyRes
import com.app.repairo.app.utils.Preferences
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_emergency.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.*
import kotlin.collections.ArrayList


class EmergencyActivity : AppCompatActivity() ,
AdapterView.OnItemSelectedListener {
    private var imageBack :RelativeLayout? = null
   private var viewHome : LinearLayout? = null
    private var viewBooking : LinearLayout? = null
    private var viewEmergency : LinearLayout? = null
    private var viewProfile : LinearLayout? = null
    var serviceArrayList : ArrayList<String> = ArrayList()
    private var dataList: java.util.ArrayList<CategoryList>? = null
    private var button : LinearLayout? = null
    private var editFullName : EditText? = null
    private var addressId : String = ""
    private var category_id : String = ""
         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)
             imageBack = findViewById(R.id.imageBack)
             button = findViewById(R.id.button)
             editFullName = findViewById(R.id.editFullName)
             imageBack!!.setOnClickListener {
                 finish()
             }
             viewHome =findViewById(R.id.viewHome)
             viewBooking =findViewById(R.id.viewBooking)
             viewEmergency =findViewById(R.id.viewEmergency)
             viewProfile =findViewById(R.id.viewProfile)
             viewBooking!!.setOnClickListener {
                 val intent = Intent(this@EmergencyActivity, BookingListActivity::class.java)
                 startActivity(intent)
             }
             viewHome!!.setOnClickListener {
                 val intent = Intent(this@EmergencyActivity, HomeActivity::class.java)
                 startActivity(intent)
             }
             viewProfile!!.setOnClickListener {
                 val intent = Intent(this@EmergencyActivity, MyProfileActivity::class.java)
                 startActivity(intent)
             }



    editAddressLayout.setOnClickListener {
        val intent = Intent(this@EmergencyActivity, SelectAddressListActivity::class.java)
        startActivityForResult(intent,200)
    }
    button!!.setOnClickListener {
        if (editFullName!!.getText().toString() == "") {
            OkDialog.show(this@EmergencyActivity, "Please enter full name")
        }else if (editEmail!!.getText().toString() == "") {
            OkDialog.show(this@EmergencyActivity, "Please enter email")
        }else if (editMobile!!.getText().toString() == "") {
            OkDialog.show(this@EmergencyActivity, "Please enter phone number")
        }else if (addressId == "") {
            OkDialog.show(this@EmergencyActivity, "Please select address")
        }else if (editMessage!!.getText().toString() == "") {
            OkDialog.show(this@EmergencyActivity, "Please enter message")
        }else {
            updateProfileApisCall()
        }

    }
    fetchProfileApisCall()
             fetchHomeDataApisCall()
}
override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    category_id = dataList!!.get(p2).id
}

override fun onNothingSelected(p0: AdapterView<*>?) {
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

    private fun fetchHomeDataApisCall() {
        val progressBarHandler = ProgressBarHandler(this@EmergencyActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@EmergencyActivity).create(
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
                            dataList!!.clear()
                            serviceArrayList = ArrayList()
                            serviceArrayList!!.clear()

                            dataList = homeResponse.data.list_services
                            for (i in dataList!!.indices) {
                                serviceArrayList.add(dataList!!.get(i).category_name)
                            }
                            val spin = findViewById<View>(R.id.spinner) as Spinner
                            spin.onItemSelectedListener = this@EmergencyActivity
                            val aa: ArrayAdapter<String> =
                                ArrayAdapter<String>(this@EmergencyActivity, android.R.layout.simple_spinner_item, serviceArrayList)
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            spin.adapter = aa
                        }

                    } else {
                        Toast.makeText(
                            this@EmergencyActivity,
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

    private fun fetchProfileApisCall() {

    var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),Preferences.getString(this@EmergencyActivity,"id"));
    val progressBarHandler = ProgressBarHandler(this@EmergencyActivity)
    progressBarHandler.show()
    val apiInterface = ApiClient.getRetrofitClientForAuth(this@EmergencyActivity).create(
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
                        this@EmergencyActivity,
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

private fun updateProfileApisCall() {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),
            Preferences.getString(this@EmergencyActivity,"id"));
        var name = RequestBody.create("text/plain".toMediaTypeOrNull(),editFullName!!.text.toString());
        var email = RequestBody.create("text/plain".toMediaTypeOrNull(),editEmail!!.text.toString());
        var mobile = RequestBody.create("text/plain".toMediaTypeOrNull(),editMobile!!.text.toString());
        var message = RequestBody.create("text/plain".toMediaTypeOrNull(),editMessage!!.text.toString());
        var addressId = RequestBody.create("text/plain".toMediaTypeOrNull(),addressId);
        var category_id = RequestBody.create("text/plain".toMediaTypeOrNull(),category_id);
        val progressBarHandler = ProgressBarHandler(this@EmergencyActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@EmergencyActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.saveEmergencyBooking(userID,name,addressId,email,mobile,message,category_id)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(loginResponse: LoginResponse) {
                    Log.e("TAG", "loginResponse : $loginResponse")
                    progressBarHandler.hide()
                    Toast.makeText(
                        this@EmergencyActivity,
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
}