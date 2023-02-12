package com.app.repairo.app.activity.logreg

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.repairo.app.R
import com.google.firebase.FirebaseApp
import com.app.repairo.app.activity.dashboard.HomeActivity
import com.app.repairo.app.utils.Preferences
import java.util.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        FirebaseApp.initializeApp(this@SplashActivity)
            val intervalTime = 3000 // 10 sec
            val handler = Handler()
            handler.postDelayed(Runnable {
               if(Preferences.getBoolean(this@SplashActivity,"isLogin")){
                   var intent = Intent(this@SplashActivity, HomeActivity::class.java)
                   startActivity(intent)
               }else {
                   var intent = Intent(this@SplashActivity, LoginActivity::class.java)
                   startActivity(intent)
               }
            }, intervalTime.toLong())
        }
    }
