package com.larrex.myapplication.network.model

import com.google.gson.annotations.SerializedName

data class Tenses(
    @SerializedName("infinitive") var infinitive: String? = null,
    @SerializedName("imperative") var imperative: String? = null,
    @SerializedName("simplePast") var simplePast: String? = null,
    @SerializedName("simplePresent") var simplePresent: String? = null,
    @SerializedName("presentContinuous") var presentContinuous: String? = null,
    @SerializedName("future") var future: String? = null
)
