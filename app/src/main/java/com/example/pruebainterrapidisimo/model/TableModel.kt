package com.example.pruebainterrapidisimo.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class TableModel(
    @SerializedName("NombreTabla")
    val nombreTabla: String,

    @SerializedName("Pk")
    val pk: String,

    @SerializedName("QueryCreacion")
    val queryCreacion: String,

    @SerializedName("BatchSize")
    val batchSize: Long,

    @SerializedName("Filtro")
    val filtro: String,

    @SerializedName("Error")
    val error: String?,

    @SerializedName("NumeroCampos")
    val numeroCampos: Int,

    @SerializedName("MetodoApp")
    val metodoApp: String?,

    @SerializedName("FechaActualizacionSincro")
    val fechaActualizacionSincro: String
)
