package com.example.mscrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mscrecord.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var vB : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        vB  = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vB.root)



        // Intentions

        vB.moviesCardView.setOnClickListener {
            val intent = Intent(this@MainActivity, MyMovies :: class.java)
            startActivity(intent)
        }

        vB.seriesCardView.setOnClickListener {
            val intent = Intent(this@MainActivity, MySeries :: class.java)
            startActivity(intent)
        }

        vB.citiesCardView.setOnClickListener {
            val intent = Intent(this@MainActivity, MyCities :: class.java)
            startActivity(intent)
        }


    }
}