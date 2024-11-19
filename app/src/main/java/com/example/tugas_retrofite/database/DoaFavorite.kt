package com.example.tugas_retrofite.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class DoaFavorite(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,
    @ColumnInfo(name = "doa")
    val doa: String,
    @ColumnInfo(name = "latin")
    val latin: String,
    @ColumnInfo(name = "artinya")
    val artinya: String,
    @ColumnInfo(name = "ayat")
    val ayat: String
)
