package com.example.tugas_retrofite.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Doa(
    @SerializedName("id")
    val id: Int,
    @SerializedName("doa")
    val doa: String,
    @SerializedName("latin")
    val latin: String,
    @SerializedName("artinya")
    val artinya: String,
    @SerializedName("ayat")
    val ayat: String,
)
