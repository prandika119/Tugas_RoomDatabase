package com.example.tugas_retrofite.network

import com.example.tugas_retrofite.model.Doa
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api")
    fun getAllDoa(): Call<List<Doa>>
}