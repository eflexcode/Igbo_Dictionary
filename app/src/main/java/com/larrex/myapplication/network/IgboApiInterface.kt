package com.larrex.myapplication.network

import com.larrex.myapplication.network.model.IgboApiResponse
import com.larrex.myapplication.network.model.IgboSingleWordApiResponse
import retrofit2.Call
import retrofit2.http.*


interface IgboApiInterface {

    @Headers("X-API-Key: a116f7c5-782c-4566-9f8f-3f69efe16cd6")
    @GET("words")
    @JvmSuppressWildcards
    fun getMeanings(@QueryMap map: Map<String, Any>): Call<List<IgboApiResponse>>

    @Headers("X-API-Key: a116f7c5-782c-4566-9f8f-3f69efe16cd6")
    @GET("words/{wordId}")
    @JvmSuppressWildcards
    fun getSingleWordMeaning(@Path("wordId") worldId: String): Call<IgboSingleWordApiResponse>

}