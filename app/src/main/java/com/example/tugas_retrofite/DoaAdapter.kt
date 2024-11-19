package com.example.tugas_retrofite

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas_retrofite.database.DoaFavorite
import com.example.tugas_retrofite.database.DoaRoomDatabase
import com.example.tugas_retrofite.database.FavoriteDao
import com.example.tugas_retrofite.databinding.ItemDoaBinding
import com.example.tugas_retrofite.model.Doa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DoaAdapter (private val listDoa: List<Doa>) :RecyclerView.Adapter<DoaAdapter.ItemDoaViewHolder>() {
    private lateinit var executorService: ExecutorService
    private lateinit var mFavoriteDao: FavoriteDao
    inner class ItemDoaViewHolder (private val binding: ItemDoaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Doa) {
            with(binding){
                txtName.text = data.doa
                txtAyat.text = data.ayat
                txtArtinya.text = data.artinya
                txtLatin.text = data.latin
                CoroutineScope(Dispatchers.IO).launch {
                    val isFavorite = isFavorite(data.id)
                    withContext(Dispatchers.Main) {
                        if (isFavorite) {
                            imgStar.setImageResource(R.drawable.baseline_star_24)
                        }
                    }
                }
                imgStar.setOnClickListener {
                    imgStar.setImageResource(R.drawable.baseline_star_24)
                    insertFavorite(
                        DoaFavorite(
                            id = data.id,
                            doa = data.doa,
                            latin = data.latin,
                            artinya = data.artinya,
                            ayat = data.ayat,
                        )
                    )
                    Toast.makeText(root.context, "Menambah ${data.doa} sebagai favorite", Toast.LENGTH_SHORT).show()
                }
                root.setOnLongClickListener {
                    imgStar.setImageResource(R.drawable.outline_star_outline_24)
                    deleteFavorite(data.id)
                    Toast.makeText(root.context, "Menghapus ${data.doa} sebagai favorite", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDoaViewHolder {
        val binding = ItemDoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        executorService = Executors.newSingleThreadExecutor()
        val db = DoaRoomDatabase.getDatabase(parent.context)
        mFavoriteDao = db!!.favoriteDao()!!
        return ItemDoaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listDoa.size
    }

    override fun onBindViewHolder(holder: ItemDoaViewHolder, position: Int) {
        holder.bind(listDoa[position])
    }

    fun insertFavorite(doa: DoaFavorite){
        executorService.execute {
            mFavoriteDao.insert(doa)
        }
    }
    fun deleteFavorite(id: Int){
        executorService.execute {
            mFavoriteDao.delete(id)
        }
    }
    fun isFavorite(id: Int): Boolean{
        if (mFavoriteDao.isFavorite(id) == 0){
            return false
        } else {
            return true
        }
    }
}