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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.repairo.app.adapter.AddressAdapter
import com.app.repairo.app.adapter.BookingsAdapter
import com.app.repairo.app.adapter.CategoryAdapter
import com.app.repairo.app.adapter.ProductsAdapter
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.address.AddressData
import com.app.repairo.app.model.address.AddressList
import com.app.repairo.app.model.bookings.BookingList
import com.app.repairo.app.model.login.LoginResponse
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

class AddressListActivity : AppCompatActivity(),AddressAdapter.EventListener {
    private var imageBack :RelativeLayout? = null
    private var addNewAddress :LinearLayout? = null
    private var listAddress :RecyclerView? = null
    private var addressAdapter : AddressAdapter? = null
    private var dataList: ArrayList<AddressList> = ArrayList()
         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)
             imageBack = findViewById(R.id.imageBack)
             addNewAddress = findViewById(R.id.addNewAddress)
             listAddress = findViewById(R.id.listAddress)
             imageBack!!.setOnClickListener {
                 finish()
             }
             addNewAddress!!.setOnClickListener {
                 val intent = Intent(this@AddressListActivity, AddAddressActivity::class.java)
                 startActivity(intent)
             }


             
    }

    override fun onResume() {
        super.onResume()
        fetchAddressApisCall()
    }
    private fun fetchAddressApisCall() {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),
            Preferences.getString(this@AddressListActivity,"id"));
        val progressBarHandler = ProgressBarHandler(this@AddressListActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@AddressListActivity).create(
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
                            addressAdapter = AddressAdapter(this@AddressListActivity, dataList,this@AddressListActivity)
                            listAddress!!.setLayoutManager(LinearLayoutManager(this@AddressListActivity,LinearLayoutManager.VERTICAL, false))
                            listAddress!!.adapter = addressAdapter
                        }

                    } else {
                        Toast.makeText(
                            this@AddressListActivity,
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
    private fun deleteAddressApisCall(addressId:String) {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),
            Preferences.getString(this@AddressListActivity,"id"));
        var addressId = RequestBody.create("text/plain".toMediaTypeOrNull(),addressId);
        val progressBarHandler = ProgressBarHandler(this@AddressListActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@AddressListActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.deleteAddress(userID,addressId)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(loginResponse: LoginResponse) {
                    Log.e("TAG", "loginResponse : $loginResponse")
                    progressBarHandler.hide()
                    if (loginResponse.success == 1) {
                        fetchAddressApisCall()
                    }
                        Toast.makeText(
                            this@AddressListActivity,
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
    private fun selectAddressApisCall(index:Int) {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),
            Preferences.getString(this@AddressListActivity,"id"));
        var addressId = RequestBody.create("text/plain".toMediaTypeOrNull(),dataList.get(index).id);
        var address = RequestBody.create("text/plain".toMediaTypeOrNull(),dataList.get(index).address);
        var city = RequestBody.create("text/plain".toMediaTypeOrNull(),dataList.get(index).city);
        var state = RequestBody.create("text/plain".toMediaTypeOrNull(),dataList.get(index).state);
        var pincode = RequestBody.create("text/plain".toMediaTypeOrNull(),dataList.get(index).pincode);
        var landmark = RequestBody.create("text/plain".toMediaTypeOrNull(),dataList.get(index).landmark);
        var isdefault = RequestBody.create("text/plain".toMediaTypeOrNull(),"1");
        val progressBarHandler = ProgressBarHandler(this@AddressListActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@AddressListActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.updateAddress(userID,addressId,address,city,state,pincode,isdefault,landmark)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(loginResponse: LoginResponse) {
                    Log.e("TAG", "loginResponse : $loginResponse")
                    progressBarHandler.hide()
                    if (loginResponse.success == 1) {
                        fetchAddressApisCall()
                    }
                    Toast.makeText(
                        this@AddressListActivity,
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
    override fun deleteEventCalled(pos: Int) {
        deleteAddressApisCall(dataList.get(pos).id)
    }

    override fun editEventCalled(pos: Int) {
        val intent = Intent(this@AddressListActivity, EditAddressActivity::class.java)
        intent.putExtra("addressId",dataList.get(pos).id)
        intent.putExtra("address",dataList.get(pos).address)
        intent.putExtra("landmark",dataList.get(pos).landmark)
        intent.putExtra("city",dataList.get(pos).city)
        intent.putExtra("state",dataList.get(pos).state)
        intent.putExtra("pincode",dataList.get(pos).pincode)
        intent.putExtra("is_default",dataList.get(pos).is_default)
        startActivity(intent)
    }

    override fun selectEventCalled(pos: Int) {
        selectAddressApisCall(pos)
    }

}