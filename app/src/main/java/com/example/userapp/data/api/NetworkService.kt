package com.example.userapp.data.api

import com.example.userapp.data.model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET("api")
    suspend fun getUserDetails(@Query("results") results: Int): ResponseData
}