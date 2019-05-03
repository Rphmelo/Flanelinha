package br.com.flanelinha.app.cars.list.model.model

import com.google.gson.annotations.SerializedName

data class CarsResponse(@SerializedName("content")val pokemons: List<Carros>)

data class Carros(
        @SerializedName("plate") val placa: String,
        @SerializedName("model") val modelo: String,
        @SerializedName("imageURL") val imagem: String
)