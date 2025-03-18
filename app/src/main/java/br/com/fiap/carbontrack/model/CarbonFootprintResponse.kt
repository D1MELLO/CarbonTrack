package br.com.fiap.carbontrack.model

data class CarbonFootprintResponse(
    val total_footprint: Double,
    val unit: String
)