package br.com.fiap.carbontrack.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://carbon-track-api.onrender.com"

    // Configura o Interceptor para logar as requisições e respostas
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY  // Loga o corpo da requisição e resposta
    }

    // Configura o cliente HTTP com o Interceptor
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Configura o Retrofit
    val instance: CarbonFootprintApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)  // Adiciona o cliente HTTP configurado
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarbonFootprintApi::class.java)
    }
}