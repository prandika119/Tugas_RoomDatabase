package com.example.tugas_retrofite.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tugas_retrofite.model.Doa

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: DoaFavorite)

    @Query("DELETE FROM favorites WHERE id = :id")
    fun delete(id: Int)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): LiveData<List<DoaFavorite>>

    @Query("SELECT COUNT(*) FROM favorites WHERE id = :id")
    fun isFavorite(id: Int): Int
}