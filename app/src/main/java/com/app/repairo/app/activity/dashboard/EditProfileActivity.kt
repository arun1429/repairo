package com.app.repairo.app.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import com.app.repairo.app.R
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.repairo.app.adapter.CategoryAdapter
import com.app.repairo.app.adapter.ReviewsAdapter
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.OkDialog
import com.app.repairo.app.custom.OkDialog.show
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.login.LoginResponse
import com.app.repairo.app.model.products.ProductsList
import com.app.repairo.app.model.ratingsreview.ReviewData
import com.app.repairo.app.model.subcategory.SubCateResponse
import com.app.repairo.app.model.verifymobile.OtpVerifyRes
import com.app.repairo.app.utils.Preferences
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.ArrayList

class EditProfileActivity : AppCompatActivity() {
    private var imageBack :RelativeLayout? = null
    private var button :LinearLayout? = null
    private var editFullName :EditText? = null
    private var editEmail :EditText? = null
    private var editMobile :EditText? = null
         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
             imageBack = findViewById(R.id.imageBack)
             button = findViewById(R.id.button)
             editFullName = findViewById(R.id.editFullName)
             editEmail = findViewById(R.id.editEmail)
             editMobile = findViewById(R.id.editMobile)
             imageBack!!.setOnClickListener {
                 finish()
             }
             button!!.setOnClickListener {
                 if (editFullName!!.getText().toString() == "") {
                     show(this@EditProfileActivity, "Please enter full name")
                 }else if (editEmail!!.getText().toString() == "") {
                 show(this@EditProfileActivity, "Please enter email")
                 }else {
                     updateProfileApisCall()
                 }

             }
             fetchProfileApisCall()
    }
    private fun fetchProfileApisCall() {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),Preferences.getString(this@EditProfileActivity,"id"));
         val progressBarHandler = ProgressBarHandler(this@EditProfileActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@EditProfileActivity).create(
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
                            this@EditProfileActivity,
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
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),Preferences.getString(this@EditProfileActivity,"id"));
        var name = RequestBody.create("text/plain".toMediaTypeOrNull(),editFullName!!.text.toString());
        var email = RequestBody.create("text/plain".toMediaTypeOrNull(),editEmail!!.text.toString());
        val progressBarHandler = ProgressBarHandler(this@EditProfileActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@EditProfileActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.updateProfileData(userID,name,email)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(loginResponse: LoginResponse) {
                    Log.e("TAG", "loginResponse : $loginResponse")
                    progressBarHandler.hide()
                    if (loginResponse.success == 1) {
                        fetchProfileApisCall()
                    }
                    Toast.makeText(
                        this@EditProfileActivity,
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