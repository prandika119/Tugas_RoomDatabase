package com.example.tugas_retrofite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas_retrofite.databinding.ActivityMainBinding
import com.example.tugas_retrofite.model.Doa
import com.example.tugas_retrofite.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val responseDoa = client.getAllDoa()
        val doaList = ArrayList<Doa>()
        with(binding){
            btnFavorite.setOnClickListener{
                val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
        responseDoa.enqueue(object : Callback<List<Doa>> {
            override fun onResponse(p0: Call<List<Doa>>, p1: Response<List<Doa>>) {
                for (i in p1.body()!!) {
                    doaList.add(i)
                }
                val adapterDoa = DoaAdapter(doaList)
                binding.rvDoa.adapter = adapterDoa
                binding.rvDoa.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(p0: Call<List<Doa>>, p1: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi Error", Toast.LENGTH_SHORT).show()
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}