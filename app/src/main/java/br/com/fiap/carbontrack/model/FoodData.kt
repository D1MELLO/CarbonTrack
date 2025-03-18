package br.com.fiap.carbontrack.model

import com.google.gson.annotations.SerializedName

data class FoodData(
    @SerializedName("meat_consumption") val meat_consumption: Double,
    @SerializedName("dairy_consumption") val dairy_consumption: Double
)