package br.com.fiap.carbontrack.model

import com.google.gson.annotations.SerializedName

data class EnergyData(
    @SerializedName("electricity_usage") val electricity_usage: Double
)