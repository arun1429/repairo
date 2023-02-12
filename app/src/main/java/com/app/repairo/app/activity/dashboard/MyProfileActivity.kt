package com.app.repairo.app.activity.dashboard

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.app.repairo.app.R
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.repairo.app.adapter.ProductsAdapter
import com.app.repairo.app.model.products.ProductsList
import java.util.ArrayList
import android.content.DialogInterface
import com.app.repairo.app.activity.logreg.LoginActivity
import com.app.repairo.app.utils.ConstantValues.KEY_IS_TYPE

import com.app.repairo.app.utils.ConstantValues.KEY_IS_LOGIN

import com.app.repairo.app.activity.logreg.SplashActivity
import com.app.repairo.app.utils.ConstantValues
import com.app.repairo.app.utils.Preferences
import kotlinx.android.synthetic.main.activity_my_profile.*


class MyProfileActivity : AppCompatActivity() {
    private var imageBack :RelativeLayout? = null
    private var viewHome : LinearLayout? = null
    private var editProfile : LinearLayout? = null
    private var profileLogout : LinearLayout? = null
    private var editAddress : LinearLayout? = null
    private var viewBooking : LinearLayout? = null
    private var viewEmergency : LinearLayout? = null
    private var viewProfile : LinearLayout? = null
         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
             imageBack = findViewById(R.id.imageBack)
             imageBack!!.setOnClickListener {
                 finish()
             }
             editProfile =findViewById(R.id.editProfile)
             profileLogout =findViewById(R.id.profileLogout)
             editAddress =findViewById(R.id.editAddress)
             viewHome =findViewById(R.id.viewHome)
             viewBooking =findViewById(R.id.viewBooking)
             viewEmergency =findViewById(R.id.viewEmergency)
             viewProfile =findViewById(R.id.viewProfile)


             editProfile!!.setOnClickListener {
                 val intent = Intent(this@MyProfileActivity, EditProfileActivity::class.java)
                 startActivity(intent)
             }
             editAddress!!.setOnClickListener {
                 val intent = Intent(this@MyProfileActivity, AddressListActivity::class.java)
                 startActivity(intent)
             }
             editRegister!!.setOnClickListener {
                 val intent = Intent(this@MyProfileActivity, MechanicRegisterActivity::class.java)
                 startActivity(intent)
             }
             profileLogout!!.setOnClickListener {
                 showAlertPopup();
             }

             viewBooking!!.setOnClickListener {
                 val intent = Intent(this@MyProfileActivity, BookingListActivity::class.java)
                 startActivity(intent)
             }

             viewHome!!.setOnClickListener {
                 val intent = Intent(this@MyProfileActivity, HomeActivity::class.java)
                 startActivity(intent)
             }
             viewEmergency!!.setOnClickListener {
                 val intent = Intent(this@MyProfileActivity, EmergencyActivity::class.java)
                 startActivity(intent)
             }
             
    }
    private fun showAlertPopup() {
        val builder: AlertDialog.Builder =
            AlertDialog.Builder(this@MyProfileActivity, R.style.AlertDialogCustom)
        builder.setMessage(resources.getString(R.string.logout_msg))
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                val intent = Intent(this@MyProfileActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                Preferences.clerPref(this@MyProfileActivity)
                Preferences.saveBoolean(this@MyProfileActivity, "isLogin", false)
                Preferences.saveString(this@MyProfileActivity, "id", "")
                Preferences.saveString(this@MyProfileActivity, "mobile", "")
                Preferences.saveString(this@MyProfileActivity, "email", "")
                Preferences.saveString(this@MyProfileActivity, "name", "")
                startActivity(intent);
                finishAffinity();
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        val alert: AlertDialog = builder.create()
        alert.getWindow()!!.setBackgroundDrawableResource(android.R.color.white)
        alert.show()
    }
}