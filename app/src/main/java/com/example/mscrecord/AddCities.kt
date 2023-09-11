package com.example.mscrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mscrecord.adapters.AddCitiesAdapter
import com.example.mscrecord.apis.CitiesApi
import com.example.mscrecord.contants.Urls
import com.example.mscrecord.databinding.ActivityAddCitiesBinding
import com.example.mscrecord.models.CitiesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddCities : AppCompatActivity() {
    val addCitiesNameList = ArrayList<String>()
    val addCitiesImageList = ArrayList<String>()
    val addCitiesRegionList = ArrayList<String>()
    private lateinit var vB: ActivityAddCitiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        vB = ActivityAddCitiesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vB.root)
        service()
    }
    fun service(){
        val retrofit = Retrofit.Builder()
            .baseUrl(Urls.CİTİES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CitiesApi :: class.java)
        val call = service.loadData()
        call.enqueue(object : Callback<List<CitiesModel>> {
            override fun onResponse(
                call: Call<List<CitiesModel>>,
                response: Response<List<CitiesModel>>,
            ) {
                if(response.isSuccessful){
                    vB.addCitiesProgressBar.visibility = View.INVISIBLE
                    vB.addCitiesRecyclerView.adapter = AddCitiesAdapter(this@AddCities,addCitiesNameList,addCitiesImageList,addCitiesRegionList)
                    vB.addCitiesRecyclerView.layoutManager = LinearLayoutManager(this@AddCities)
                    var indeks = 0
                    while(indeks<response.body()!!.size){
                        addCitiesNameList.add(response.body()!!.get(indeks).name)
                        addCitiesImageList.add(response.body()!!.get(indeks).image)
                        addCitiesRegionList.add(response.body()!!.get(indeks).region)
                        indeks ++


                    }
                }
            }

            override fun onFailure(call: Call<List<CitiesModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}