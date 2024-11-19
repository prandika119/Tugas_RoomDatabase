package com.example.tugas_retrofite

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas_retrofite.database.DoaFavorite
import com.example.tugas_retrofite.databinding.ItemDoaBinding

class FavoriteAdapter(private val listFavorite: List<DoaFavorite>) : RecyclerView.Adapter<FavoriteAdapter.ItemFavoriteViewHolder>() {

    inner class ItemFavoriteViewHolder(private val binding: ItemDoaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DoaFavorite) {
            with(binding) {
                txtName.text = data.doa
                txtAyat.text = data.ayat
                txtArtinya.text = data.artinya
                txtLatin.text = data.latin

                imgStar.setImageResource(R.drawable.baseline_star_24)

                root.setOnClickListener {
                    Toast.makeText(root.context, "Clicked: ${data.doa}", Toast.LENGTH_SHORT).show()
                }
                root.setOnLongClickListener {
                    Toast.makeText(root.context, "Long Clicked: ${data.doa}", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFavoriteViewHolder {
        val binding = ItemDoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemFavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listFavorite.size
    }

    override fun onBindViewHolder(holder: ItemFavoriteViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }
}