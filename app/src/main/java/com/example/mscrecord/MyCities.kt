package com.example.mscrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.core.view.size
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mscrecord.adapters.MyCitiesAdapter
import com.example.mscrecord.databinding.ActivityMyCitiesBinding


class MyCities : AppCompatActivity() {
    private lateinit var vB : ActivityMyCitiesBinding
    val myCities = MyCitiesAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        vB = ActivityMyCitiesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vB.root)



            vB.myCitiesRecyclerView.adapter = MyCitiesAdapter(this@MyCities)
            vB.myCitiesRecyclerView.layoutManager = LinearLayoutManager(this@MyCities)
            if(myCities.itemCount == 0){
                vB.textViewEmty.text = "Gezilen Şehir Yok"
            }
            // yenileme yapıldığında veri sayısı 0'sa yazıyı yazdırmak için

        // Intentions
        vB.buttonToAddCities.setOnClickListener {
            val intent = Intent(this@MyCities,AddCities :: class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
         super.onStart()
        vB.myCitiesRecyclerView.adapter = MyCitiesAdapter(this@MyCities)
        vB.myCitiesRecyclerView.layoutManager = LinearLayoutManager(this@MyCities)
        if(myCities.itemCount >= 1){
            vB.textViewEmty.text = ""
        }
        // veri girilince gezilen şehir yok yazısı boş değer alıyor

        // on start şehir eklendiğinde anlık eklensin tekrar en başa dönüp geri gelinmemesi için
    }
}