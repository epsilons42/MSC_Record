package com.example.mscrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mscrecord.databinding.ActivitySplashScreenBinding

class splashScreen : AppCompatActivity() {
    private lateinit var vB : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        vB = ActivitySplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vB.root)
        Handler().postDelayed({
            val intent = Intent(this@splashScreen,MainActivity :: class.java)
            startActivity(intent)
            finish()
        },900)
        //0,9 saniye sonra diğer aktiviteye geç dedik
    }
}