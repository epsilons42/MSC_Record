package com.example.mscrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mscrecord.adapters.MyCitiesAdapter
import com.example.mscrecord.adapters.MySeriesAdapter
import com.example.mscrecord.databinding.ActivityMySeriesBinding

class MySeries : AppCompatActivity() {
    private lateinit var vB : ActivityMySeriesBinding
    val mySeries = MySeriesAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        vB = ActivityMySeriesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vB.root)
            vB.mySeriesRecyclerView.adapter = MySeriesAdapter(this@MySeries)
            vB.mySeriesRecyclerView.layoutManager = LinearLayoutManager(this@MySeries)
            // silme işleminde sonra sayfa yenilenmesi ve silinen verinin görünümden gitmesi içim
            if(mySeries.itemCount == 0){
                vB.textViewEmty3.text = "İzlenen Dizi Yok"
            }
            // yenileme yapıldığında veri sayısı 0'sa yazıyı yazdırmak için



        if(mySeries.itemCount >= 1){
            vB.textViewEmty3.text = ""
        }
        // veri girilince izlenen dizi  yok yazısı boş değer alıyor

        // Intentions
        vB.buttonToAddSeries.setOnClickListener {
            val intent = Intent(this@MySeries,AddSeries :: class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
         super.onStart()
        vB.mySeriesRecyclerView.adapter = MySeriesAdapter(this@MySeries)
        vB.mySeriesRecyclerView.layoutManager = LinearLayoutManager(this@MySeries)
        if(mySeries.itemCount >= 1){
            vB.textViewEmty3.text = ""
        }
        // veri girilince izlenen dizi  yok yazısı boş değer alıyor
        // on start dizi eklendiğinde anlık eklensin tekrar en başa dönüp geri gelinmemesi için
    }

}