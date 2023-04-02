package com.larrex.myapplication.network.model

import com.google.gson.annotations.SerializedName

data class IgboSingleWordApiResponse(
    @SerializedName("definitions") var definitions: ArrayList<String> = arrayListOf(),
    @SerializedName("stems") var stems: ArrayList<Stems> = arrayListOf(),
    @SerializedName("word") var word: String? = null,
    @SerializedName("pronunciation") var pronunciation: String? = null,
    @SerializedName("attributes") var attributes: Attributes? = Attributes(),
    @SerializedName("relatedTerms") var relatedTerms: ArrayList<RelatedTerms> = arrayListOf(),
    @SerializedName("id") var id: String? = null,
    @SerializedName("wordClass") var wordClass: String? = null,
    @SerializedName("nsibidi") var nsibidi: String? = null
)
