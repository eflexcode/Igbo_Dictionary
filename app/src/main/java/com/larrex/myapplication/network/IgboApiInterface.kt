package com.larrex.myapplication.network

import com.larrex.myapplication.network.model.IgboApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface IgboApiInterface {
    @Headers("X-API-Key: 658ba048-c7f2-4d48-94fb-f13d1055ecea")
    @GET("words")
    fun getMeanings() : Call<List<IgboApiResponse>>

}