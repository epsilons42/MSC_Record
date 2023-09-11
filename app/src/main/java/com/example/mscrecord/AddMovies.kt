package com.example.mscrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mscrecord.adapters.AddMoviesAdapter
import com.example.mscrecord.apis.MoviesApi
import com.example.mscrecord.contants.Urls
import com.example.mscrecord.databinding.ActivityAddMoviesBinding
import com.example.mscrecord.models.MoviesModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class AddMovies : AppCompatActivity() {
    val addMoviesNameList = ArrayList<String>()
    val addMoviesYearList = ArrayList<String>()
    val addMoviesPosterList = ArrayList<String>()
    private lateinit var vB : ActivityAddMoviesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        vB = ActivityAddMoviesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vB.root)
        service()
    }
    fun service(){
        val retrofit = Retrofit.Builder()
            .baseUrl(Urls.MOVİES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MoviesApi :: class.java)
        val call = service.loadData()
        call.enqueue(object : Callback<List<MoviesModel>>{
            override fun onResponse(
                call: Call<List<MoviesModel>>,
                response: Response<List<MoviesModel>>,
            ) {
                if(response.isSuccessful){
                    vB.addMoviesProgressBar.visibility = View.INVISIBLE
                    vB.addMoviesRecyclerView.adapter = AddMoviesAdapter(this@AddMovies,addMoviesNameList,addMoviesYearList,addMoviesPosterList)
                    vB.addMoviesRecyclerView.layoutManager = LinearLayoutManager(this@AddMovies)
                    var indeks = 0
                    while(indeks<response.body()!!.size - 1){
                        addMoviesNameList .add(response.body()!!.get(indeks).Title)
                        addMoviesYearList .add(response.body()!!.get(indeks).Year)
                        addMoviesPosterList .add(response.body()!!.get(indeks).Poster)
                        indeks ++
                        // retrofitten gelen verileri listemize verdik ve recyclerview Adaptere gönderdik
                    }

                }
            }

            override fun onFailure(call: Call<List<MoviesModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }
}