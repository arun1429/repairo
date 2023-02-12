package com.app.repairo.app.activity.dashboard

import android.app.Activity
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.repairo.app.adapter.*
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.address.AddressData
import com.app.repairo.app.model.address.AddressList
import com.app.repairo.app.model.login.LoginResponse
import com.app.repairo.app.utils.Preferences
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.ArrayList

class SelectAddressListActivity : AppCompatActivity(),SelectAddressAdapter.EventListener {
    private var imageBack :RelativeLayout? = null
    private var addNewAddress :LinearLayout? = null
    private var listAddress :RecyclerView? = null
    private var addressAdapter : SelectAddressAdapter? = null
    private var dataList: ArrayList<AddressList> = ArrayList()
         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_address_list)
             imageBack = findViewById(R.id.imageBack)
             addNewAddress = findViewById(R.id.addNewAddress)
             listAddress = findViewById(R.id.listAddress)
             imageBack!!.setOnClickListener {
                 finish()
             }
             addNewAddress!!.setOnClickListener {
                 val intent = Intent(this@SelectAddressListActivity, AddAddressActivity::class.java)
                 startActivity(intent)
             }


             
    }

    override fun onResume() {
        super.onResume()
        fetchAddressApisCall()
    }
    private fun fetchAddressApisCall() {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),
            Preferences.getString(this@SelectAddressListActivity,"id"));
        val progressBarHandler = ProgressBarHandler(this@SelectAddressListActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@SelectAddressListActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.getUserAddress(userID)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<AddressData> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(addressData: AddressData) {
                    Log.e("TAG", "addressData : $addressData")
                    progressBarHandler.hide()
                    if (addressData.success == 1) {
                        if(addressData.data.size !=0){
                            dataList = ArrayList()
                            dataList = addressData.data
                            addressAdapter = SelectAddressAdapter(this@SelectAddressListActivity, dataList,this@SelectAddressListActivity)
                            listAddress!!.setLayoutManager(LinearLayoutManager(this@SelectAddressListActivity,LinearLayoutManager.VERTICAL, false))
                            listAddress!!.adapter = addressAdapter
                        }

                    } else {
                        Toast.makeText(
                            this@SelectAddressListActivity,
                            addressData.message,
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
    override fun selectEventCalled(pos: Int) {
        val intent = Intent()
        intent.putExtra("SelectedAddressId", dataList.get(pos).id)
        intent.putExtra("SelectedAddress", dataList.get(pos).address+", "+dataList.get(pos).city+", "+dataList.get(pos).state+", "+dataList.get(pos).pincode)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}