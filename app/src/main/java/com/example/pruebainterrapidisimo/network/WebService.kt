package com.example.pruebainterrapidisimo.network

import com.example.pruebainterrapidisimo.model.TableModel
import com.example.pruebainterrapidisimo.network.response.TablesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface WebService {
    @GET("ObtenerEsquema/true")
    suspend fun consumeApi(@Header("usuario") type: String): Response<TablesResponse>
}