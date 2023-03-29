package com.larrex.myapplication.network

import com.larrex.myapplication.network.model.IgboApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap


interface IgboApiInterface {
    @Headers("X-API-Key: a116f7c5-782c-4566-9f8f-3f69efe16cd6")
    @GET("words")
    @JvmSuppressWildcards
    fun getMeanings(@QueryMap map: Map<String,Any>) : Call<List<IgboApiResponse>>

}