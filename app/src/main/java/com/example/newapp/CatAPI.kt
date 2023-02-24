package com.example.newapp

import retrofit2.http.GET
import retrofit2.http.Headers

interface CatAPI {
    @Headers("Content-Type: application/json")
    @GET("/")
    suspend fun fact(): CatFact
}