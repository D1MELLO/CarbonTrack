package br.com.fiap.carbontrack.api

import retrofit2.http.Body
import retrofit2.http.POST
import br.com.fiap.carbontrack.model.CarbonFootprintRequest
import br.com.fiap.carbontrack.model.CarbonFootprintResponse

interface CarbonFootprintApi {
    @POST("/calculate")
    suspend fun calculateFootprint(@Body request: CarbonFootprintRequest): CarbonFootprintResponse
}