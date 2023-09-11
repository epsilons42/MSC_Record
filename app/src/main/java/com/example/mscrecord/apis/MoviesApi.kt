package com.example.mscrecord.apis

import com.example.mscrecord.models.MoviesModel
import com.example.mscrecord.contants.Urls
import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {
    @GET(Urls.MOVİES_SECOND_URL)
    fun loadData() : Call<List<MoviesModel>>
}