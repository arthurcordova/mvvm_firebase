package com.proway.mvvm_auth.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RefrofitService {

    /**
     * Cria uma instacia do retrofit passando a url base.
     * Deixamos como private pq não interessa para as outras classes, oq nos interessa
     */
    private val retrofitFake = Retrofit.Builder()
        .baseUrl("https://pokeapi.co")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getPokeService(): PokeService {
        return retrofitFake.create(PokeService::class.java)
    }
}