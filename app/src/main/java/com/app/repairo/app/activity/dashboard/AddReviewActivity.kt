package com.app.repairo.app.activity.dashboard

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import com.app.repairo.app.R
import androidx.appcompat.app.AppCompatActivity
import com.app.repairo.app.activity.logreg.LoginActivity
import com.app.repairo.app.apis.ApiClient
import com.app.repairo.app.apis.ApiInterface
import com.app.repairo.app.custom.OkDialog
import com.app.repairo.app.custom.ProgressBarHandler
import com.app.repairo.app.model.login.LoginResponse
import com.app.repairo.app.utils.Preferences
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_review.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.util.ArrayList

class AddReviewActivity : AppCompatActivity() {
    private var imageBack :RelativeLayout? = null
    private var button : LinearLayout? = null
    private var editTitle : EditText? = null
    private var editMessage : EditText? = null
    private var dealer_id :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)
        imageBack = findViewById(R.id.imageBack)
        button = findViewById(R.id.button)
        editTitle = findViewById(R.id.editTitle)
        editMessage = findViewById(R.id.editMessage)
        imageBack!!.setOnClickListener {
            finish()
        }
        dealer_id = intent.getStringExtra("dealer_id").toString()
        txtServiceName.setText(intent.getStringExtra("category_name").toString())
        txtDealerName.setText(intent.getStringExtra("business_name").toString())
        button!!.setOnClickListener {
            if (editTitle!!.getText().toString() == "") {
                OkDialog.show(this@AddReviewActivity, "Please enter title")
            }else if (editMessage!!.getText().toString() == "") {
                OkDialog.show(this@AddReviewActivity, "Please enter email")
            }else if (txtRating!!.getRating() == 0f) {
                OkDialog.show(this@AddReviewActivity, "Please set rating")
            }else {
                addReviewApisCall()
            }

        }
    }
    private fun addReviewApisCall() {
        var userID = RequestBody.create("text/plain".toMediaTypeOrNull(),
            Preferences.getString(this@AddReviewActivity,"id"));
        var review = RequestBody.create("text/plain".toMediaTypeOrNull(),editMessage!!.text.toString());
        var rating = RequestBody.create("text/plain".toMediaTypeOrNull(),txtRating!!.rating.toString());
        var review_title = RequestBody.create("text/plain".toMediaTypeOrNull(),editTitle!!.text.toString());
        var dealer_id = RequestBody.create("text/plain".toMediaTypeOrNull(),dealer_id);
        val progressBarHandler = ProgressBarHandler(this@AddReviewActivity)
        progressBarHandler.show()
        val apiInterface = ApiClient.getRetrofitClientForAuth(this@AddReviewActivity).create(
            ApiInterface::class.java
        )
        val observable = apiInterface.saveReview(userID,dealer_id,review,rating,review_title)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponse> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(loginResponse: LoginResponse) {
                    Log.e("TAG", "loginResponse : $loginResponse")
                    progressBarHandler.hide()



                    if (loginResponse.success == 1) {
                        var ratingCount = txtRating!!.rating.toString().toFloat()
                        if(ratingCount>=4){
                            showAlertPopup("Satisfied")
                        }else {
                            showAlertPopup("Not Satisfied")
                        }
                    } else {
                        Toast.makeText(
                            this@AddReviewActivity,
                            loginResponse.message,
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
    private fun showAlertPopup(message : String) {
        val builder: AlertDialog.Builder =
            AlertDialog.Builder(this@AddReviewActivity, R.style.AlertDialogCustom)
        builder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
                finish()
            })
        val alert: AlertDialog = builder.create()
        alert.getWindow()!!.setBackgroundDrawableResource(android.R.color.white)
        alert.show()
    }
}