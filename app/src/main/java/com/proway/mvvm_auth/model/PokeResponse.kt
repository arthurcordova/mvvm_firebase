package com.proway.mvvm_auth.model

data class PokeResponse(
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String
)
