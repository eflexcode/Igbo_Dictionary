package com.larrex.myapplication.network.model

import com.google.gson.annotations.SerializedName

data class Stems( @SerializedName("id"  ) var id : Id?     = Id(),
                  @SerializedName("_id" ) var Id : String? = null)
