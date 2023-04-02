package com.larrex.myapplication.network.model

import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("type") var type: String? = null,
    @SerializedName("data") var data: ArrayList<Int> = arrayListOf()
)
