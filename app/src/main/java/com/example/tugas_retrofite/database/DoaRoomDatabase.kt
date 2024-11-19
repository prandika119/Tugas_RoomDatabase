package com.example.tugas_retrofite.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DoaFavorite::class], version = 1, exportSchema = false)
abstract class DoaRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: DoaRoomDatabase? = null

        fun getDatabase(context: Context): DoaRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DoaRoomDatabase::class.java,
                    "doa_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}