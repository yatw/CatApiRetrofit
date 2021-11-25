package com.example.catapi

import com.google.gson.annotations.SerializedName

data class CatApiResponse(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)