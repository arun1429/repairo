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
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_mechanic_register.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import android.widget.DatePicker

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import java.util.*


class MechanicRegisterActivity : AppCompatActivity() {
    private var imageBack :RelativeLayout? = null
    private var button : LinearLayout? = null
    private var editFullName : EditText? = null
    private var editDob : EditText? = null
    private var mYear = 0
    private var mMonth = 0
    private  var mDay = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mechanic_register)
        imageBack = findViewById(R.id.imageBack)
        button = findViewById(R.id.button)
        editFullName = findViewById(R.id.editFullName)
        editDob = findViewById(R.id.editDob)
        imageBack!!.setOnClickListener {
            finish()
        }
        editDob!!.setOnClickListener {
            // Get Current Date
            // Get Current Date
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth -> editDob!!.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.datePicker.maxDate = Date().time
            datePickerDialog.show()
        }
        button!!.setOnClickListener {
            if (editFullName!!.getText().toString() == "") {
                OkDialog.show(this@MechanicRegisterActivity, "Please enter full name")
            }else if (editEmail!!.getText().toString() == "") {
                OkDialog.show(this@MechanicRegisterActivity, "Please enter email")
            }else if (editMobile!!.getText().toString() == "") {
                OkDialog.show(this@MechanicRegisterActivity, "Please enter phone number")
            }else if (editDob!!.getText().toString() == "") {
                OkDialog.show(this@MechanicRegisterActivity, "Please select date of birth")
            }else if (editProfession!!.getText().toString() == "") {
                OkDialog.show(this@MechanicRegisterActivity, "Please enter profession")
            }else if (editAadharCard!!.getText().toString() == "") {
                OkDialog.show(this@MechanicRegisterActivity, "Please enter aadhar card")
            }else if (editAadharCard!!.getText().toString().length !=12) {
                OkDialog.show(this@MechanicRegisterActivity, "Please enter valid aadhar card")
            }else {
                addDetailsApisCall()
            }

        }
    }

    private fun addDetailsApisCall() {
        var name = RequestBody.create("text/plain".toMediaTypeOrNull(),editFullName!!.text.toString());
        var email = RequestBody.create("text/plain".toMediaTypeOrNull(),editEmail!!.text.toString());
        var mobile = RequestBody.create("text/plain".toMediaTypeOrNull(),editMobile!!.text.toString());
        var dob = RequestBody.create("text/plain".toMediaTypeOrNull(),editDob!!.text.toString());
        var profession = RequestBody.create("text/plain".toMediaTypeOrNull(),editProfession!!.text.toString());
        var aadharCard = RequestBody.create("text/plain".toMediaTypeOrNull(),editAadharCard!!.text.toString());
        val progressBarHandler = ProgressBarHandler(this@MechanicRegisterActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@MechanicRegisterActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.mechanicRegister(name,email,mobile,dob,profession,aadharCard)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(loginResponse: LoginResponse) {
                    Log.e("TAG", "loginResponse : $loginResponse")
                    progressBarHandler.hide()
                    Toast.makeText(
                        this@MechanicRegisterActivity,
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