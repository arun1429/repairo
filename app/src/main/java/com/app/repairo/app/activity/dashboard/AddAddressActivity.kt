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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.app.repairo.app.adapter.ReviewsAdapter
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.OkDialog
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.login.LoginResponse
import com.app.repairo.app.model.products.ProductsList
import com.app.repairo.app.model.ratingsreview.ReviewData
import com.app.repairo.app.model.verifymobile.OtpVerifyRes
import com.app.repairo.app.utils.Preferences
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_address.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.ArrayList

class AddAddressActivity : AppCompatActivity() {
    private var imageBack :RelativeLayout? = null
    private var button : LinearLayout? = null
    private var editAddress : EditText? = null
    private var selectedCity = ""
    private var selectedPinCode = ""
    private var selectedDefault = "1"
    var cityArrayList = arrayOf("Ambehta", "Behat", "Chhutmalpur", "Chilkana Sultanpur", "Deoband","Gangoh", "Kailashpur","Nakur", "Nanauta","Rampur Maniharan","Saharanpur",  )
    var pincodeArrayList = arrayOf("247001", "247121", "247342", "247554", "247451","247341")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        imageBack = findViewById(R.id.imageBack)
        button = findViewById(R.id.button)
        editAddress = findViewById(R.id.editAddress)
        imageBack!!.setOnClickListener {
            finish()
        }
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
           if(isChecked){
               selectedDefault = "1"
           }else {
               selectedDefault = "0"
           }
        }
        button!!.setOnClickListener {
            if (editAddress!!.getText().toString() == "") {
                OkDialog.show(this@AddAddressActivity, "Please enter address")
            }else {
                updateProfileApisCall()
            }

        }
        val spin = findViewById<View>(R.id.spinnerCity) as Spinner
        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, cityArrayList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = aa
        spin.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                selectedCity = cityArrayList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        val spinPinCode = findViewById<View>(R.id.spinnerPinCode) as Spinner
        val aaPinCode: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, pincodeArrayList)
        aaPinCode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinPinCode.adapter = aaPinCode
        spinPinCode.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                selectedPinCode = pincodeArrayList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun updateProfileApisCall() {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),
            Preferences.getString(this@AddAddressActivity,"id"));
        var address = RequestBody.create("text/plain".toMediaTypeOrNull(),editAddress!!.text.toString());
        var selectedCity = RequestBody.create("text/plain".toMediaTypeOrNull(),selectedCity);
        var selectedState = RequestBody.create("text/plain".toMediaTypeOrNull(),"Uttar Pradesh");
        var selectedPinCode = RequestBody.create("text/plain".toMediaTypeOrNull(),selectedPinCode);
        var selectedDefault = RequestBody.create("text/plain".toMediaTypeOrNull(),selectedDefault);
        var landmark = RequestBody.create("text/plain".toMediaTypeOrNull(),editlandmark.text.toString());
        val progressBarHandler = ProgressBarHandler(this@AddAddressActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@AddAddressActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.addAddress(userID,address,selectedCity,selectedState,selectedPinCode,selectedDefault,landmark)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(loginResponse: LoginResponse) {
                    Log.e("TAG", "loginResponse : $loginResponse")
                    progressBarHandler.hide()
                    Toast.makeText(
                        this@AddAddressActivity,
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