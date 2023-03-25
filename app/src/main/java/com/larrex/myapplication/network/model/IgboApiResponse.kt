package com.larrex.myapplication.network.model

import com.google.gson.annotations.SerializedName

data class IgboApiResponse(
    @SerializedName("definitions") var definitions: ArrayList<String> = arrayListOf(),
    @SerializedName("stems") var stems: ArrayList<String> = arrayListOf(),
    @SerializedName("word") var word: String? = null,
    @SerializedName("pronunciation") var pronunciation: String? = null,
    @SerializedName("tenses") var tenses: Tenses? = Tenses(),
    @SerializedName("variations") var variations: ArrayList<String> = arrayListOf(),
    @SerializedName("attributes") var attributes: Attributes? = Attributes(),
    @SerializedName("relatedTerms") var relatedTerms: ArrayList<String> = arrayListOf(),
    @SerializedName("examples") var examples: ArrayList<Examples> = arrayListOf(),
    @SerializedName("id") var id: String? = null,
    @SerializedName("wordClass") var wordClass: String? = null,
    @SerializedName("nsibidi") var nsibidi: String? = null
)
