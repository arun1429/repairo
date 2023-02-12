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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.repairo.app.adapter.CategoryAdapter
import com.app.repairo.app.adapter.ServiceAdapter
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.homenewmodel.CategoryList
import com.app.repairo.app.model.subcategory.SubCateResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.*
import kotlin.collections.ArrayList

class ServiceListActivity : AppCompatActivity() {
    private var txtHeader :TextView? = null
    private var imageBack :RelativeLayout? = null
    private var searchOption :RelativeLayout? = null
    private var imageNext :RelativeLayout? = null
    private var searchCard :CardView? = null
    private var viewHome : LinearLayout? = null
    private var viewBooking : LinearLayout? = null
    private var viewEmergency : LinearLayout? = null
    private var viewProfile : LinearLayout? = null
    private var listCategory : RecyclerView? = null
    private var serviceAdapter : ServiceAdapter? = null
    private var dataList: java.util.ArrayList<CategoryList>? = null
    private var dataListAll: java.util.ArrayList<CategoryList>? = null
    var headerTxt :String? = ""
    var categoryId :String? = ""
         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
             txtHeader = findViewById(R.id.txtHeader)
             imageBack = findViewById(R.id.imageBack)
             searchOption = findViewById(R.id.searchOption)
             imageNext = findViewById(R.id.imageNext)
             searchCard = findViewById(R.id.searchCard)
             listCategory =findViewById(R.id.listCategory)
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
             headerTxt = intent.getStringExtra("header")
             categoryId = intent.getStringExtra("categoryId")
             txtHeader!!.setText(headerTxt)
             viewHome =findViewById(R.id.viewHome)
             viewBooking =findViewById(R.id.viewBooking)
             viewEmergency =findViewById(R.id.viewEmergency)
             viewProfile =findViewById(R.id.viewProfile)
             viewBooking!!.setOnClickListener {
                 val intent = Intent(this@ServiceListActivity, BookingListActivity::class.java)
                 startActivity(intent)
             }
             viewEmergency!!.setOnClickListener {
                 val intent = Intent(this@ServiceListActivity, EmergencyActivity::class.java)
                 startActivity(intent)
             }
             viewProfile!!.setOnClickListener {
                 val intent = Intent(this@ServiceListActivity, MyProfileActivity::class.java)
                 startActivity(intent)
             }
             fetchSubcategoryApisCall()
             editSearch.addTextChangedListener(object : TextWatcher {
                 override fun afterTextChanged(s: Editable?) {
                     var newString = s.toString()
                     if (newString.length <= 1) {
                         dataList = java.util.ArrayList()
                         dataList!!.clear()
                         dataList = dataListAll
                         serviceAdapter!!.updateAdapter(dataList)
                     } else{
                         dataList = java.util.ArrayList()
                         dataList!!.clear()
                         for (i in dataListAll!!.indices) {
                             if(dataListAll!!.get(i).category_name.toString().toLowerCase().contains(newString.toString().toLowerCase())){
                                 dataList!!.add(dataListAll!!.get(i))
                             }
                         }
                         serviceAdapter!!.updateAdapter(dataList)
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

    private fun fetchSubcategoryApisCall() {
         var categoryID = RequestBody.create("text/plain".toMediaTypeOrNull(),categoryId.toString());
        val progressBarHandler = ProgressBarHandler(this@ServiceListActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@ServiceListActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.getSubCateData(categoryID)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<SubCateResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(subCateResponse: SubCateResponse) {
                    Log.e("TAG", "subCateResponse : $subCateResponse")
                    progressBarHandler.hide()
                    if (subCateResponse.success == 1) {
                        if(subCateResponse.data.size !=0){
                            dataList = ArrayList()
                            dataListAll = ArrayList()
                            dataList = subCateResponse.data
                            dataListAll = subCateResponse.data
                            serviceAdapter = ServiceAdapter(this@ServiceListActivity, dataList)
                            listCategory!!.setLayoutManager(
                                LinearLayoutManager(this@ServiceListActivity,
                                    LinearLayoutManager.VERTICAL, false)
                            )
                            listCategory!!.adapter = serviceAdapter
                        }

                    } else {
                        Toast.makeText(
                            this@ServiceListActivity,
                            subCateResponse.message,
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