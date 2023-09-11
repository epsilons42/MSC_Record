package com.example.mscrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mscrecord.adapters.AddSeriesAdapter
import com.example.mscrecord.apis.SeriesApi
import com.example.mscrecord.contants.Urls
import com.example.mscrecord.databinding.ActivityAddSeriesBinding
import com.example.mscrecord.models.SeriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddSeries : AppCompatActivity() {
    val addSeriesNameList = ArrayList<String>()
    val addSeriesYearList = ArrayList<String>()
    val addSeriesPosterList = ArrayList<String>()
    private lateinit var vB : ActivityAddSeriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        vB = ActivityAddSeriesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vB.root)
        service()
    }
    fun service(){
        val retrofit = Retrofit.Builder()
            .baseUrl(Urls.SERİES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SeriesApi :: class.java)
        val call = service.loadData()
        call.enqueue(object : Callback<List<SeriesModel>>{
            override fun onResponse(
                call: Call<List<SeriesModel>>,
                response: Response<List<SeriesModel>>,
            ) {
                if(response.isSuccessful){
                    vB.addSeriesProgressBar.visibility = View.INVISIBLE
                    vB.addSeriesRecyclerView.adapter = AddSeriesAdapter(this@AddSeries,addSeriesNameList,addSeriesYearList,addSeriesPosterList)
                    vB.addSeriesRecyclerView.layoutManager = LinearLayoutManager(this@AddSeries)
                    var indeks = 0
                    while(indeks<response.body()!!.size){
                        addSeriesNameList .add(response.body()!!.get(indeks).Title)
                        addSeriesYearList .add(response.body()!!.get(indeks).Year)
                        addSeriesPosterList .add(response.body()!!.get(indeks).Poster)
                        indeks ++
                        // retrofitten gelen verileri listemize verdik ve recyclerview Adaptere gönderdik

                    }
                }
            }

            override fun onFailure(call: Call<List<SeriesModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }
}