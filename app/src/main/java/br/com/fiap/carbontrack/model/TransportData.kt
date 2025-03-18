package br.com.fiap.carbontrack.model

import com.google.gson.annotations.SerializedName

data class TransportData(
    @SerializedName("car_distance") val car_distance: Double,
    @SerializedName("bus_distance") val bus_distance: Double,
    @SerializedName("plane_distance") val plane_distance: Double
)