package com.example.mscrecord.apis

import com.example.mscrecord.models.SeriesModel
import com.example.mscrecord.contants.Urls
import retrofit2.Call
import retrofit2.http.GET

interface SeriesApi {
    @GET(Urls.SERİES_SECOND_URL)
    fun loadData() : Call<List<SeriesModel>>
}