package com.example.pruebainterrapidisimo.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl("https://apitesting.interrapidisimo.co/FtAppAgencias012/apiControllerPruebas/api/SincronizadorDatos/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
}
