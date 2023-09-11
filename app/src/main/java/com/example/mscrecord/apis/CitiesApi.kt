package com.example.mscrecord.apis

import com.example.mscrecord.models.CitiesModel
import com.example.mscrecord.contants.Urls
import retrofit2.Call
import retrofit2.http.GET

interface CitiesApi {
    @GET(Urls.CİTİES_SECOND_URL)
    fun loadData() : Call<List<CitiesModel>>
}