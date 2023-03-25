package com.larrex.myapplication.network.model

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("isStandardIgbo") var isStandardIgbo: Boolean? = null,
    @SerializedName("isAccented") var isAccented: Boolean? = null,
    @SerializedName("isSlang") var isSlang: Boolean? = null,
    @SerializedName("isConstructedTerm") var isConstructedTerm: Boolean? = null,
    @SerializedName("isBorrowedTerm") var isBorrowedTerm: Boolean? = null,
    @SerializedName("isStem") var isStem: Boolean? = null,
    @SerializedName("isCommon") var isCommon: Boolean? = null
)
