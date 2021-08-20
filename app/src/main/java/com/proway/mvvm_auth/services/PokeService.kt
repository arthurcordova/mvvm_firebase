package com.proway.mvvm_auth.services

import com.proway.mvvm_auth.model.PokeResponse
import retrofit2.Call
import retrofit2.http.GET

interface PokeService {

    @GET("/api/v2/pokemon/")
    fun getAll(): Call<PokeResponse>

}