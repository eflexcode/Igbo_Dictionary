package com.larrex.myapplication.network.model

import com.google.gson.annotations.SerializedName

data class Examples(
    @SerializedName("igbo") var igbo: String? = null,
    @SerializedName("english") var english: String? = null
)
