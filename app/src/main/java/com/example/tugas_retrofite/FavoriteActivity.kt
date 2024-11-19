package com.example.tugas_retrofite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas_retrofite.databinding.ActivityFavoriteBinding
import com.example.tugas_retrofite.database.DoaRoomDatabase
import com.example.tugas_retrofite.database.FavoriteDao
import com.example.tugas_retrofite.database.DoaFavorite

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var mFavoriteDao: FavoriteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DoaRoomDatabase.getDatabase(this)
        mFavoriteDao = db!!.favoriteDao()

        val favoriteList = mFavoriteDao.getAllFavorites()
        favoriteList.observe(this) { favorites ->
            val adapterFavorite = FavoriteAdapter(favorites)
            binding.rvFavorite.adapter = adapterFavorite
            binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        }
    }
//    fun getAllFavorite(){
//        mFavoriteDao.getAllFavorites().observe(this){
//
//        }
//    }
}