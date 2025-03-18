package br.com.fiap.carbontrack.model

import com.google.gson.annotations.SerializedName

data class CarbonFootprintRequest(
    @SerializedName("transport") val transport: TransportData,
    @SerializedName("energy") val energy: EnergyData,
    @SerializedName("food") val food: FoodData
)