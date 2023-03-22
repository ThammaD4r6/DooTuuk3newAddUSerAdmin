package com.example.dootuuk3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dootuuk3.databinding.ActivityAllAnimeBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllAnimeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    private var animeList = arrayListOf<AnimeClass>()
    private var filteredAnimeList = arrayListOf<AnimeClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        searchView = binding.searchView2

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filterAnimeList(newText)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                return true
            }

        })
    }

    override fun onResume() {
        super.onResume()
        callAnimeData()
    }

    private fun callAnimeData() {
        animeList.clear();

        val serv: AnimeAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeAPI::class.java)

        serv.allanime()
            .enqueue(object : Callback<List<AnimeClass>> {

                override fun onResponse(
                    call: Call<List<AnimeClass>>,
                    response: Response<List<AnimeClass>>
                ) {
                    response.body()?.forEach {
                        animeList.add(
                            AnimeClass(
                                it.ID,
                                it.NameTH,
                                it.NameJP,
                                it.NameEN,
                                it.Synopsis,
                                it.Genre,
                                it.Episode,
                                it.Type,
                                it.Season,
                                it.Year,
                                it.Air_Date,
                                it.End_Date,
                                it.Status,
                                it.Studio,
                                it.Source,
                                it.Picture
                            )
                        )
                    }

                    filteredAnimeList.addAll(animeList)
                    recyclerView.adapter = AnimeAdapter(filteredAnimeList, applicationContext)
                    binding.anime.text =
                        "อนิเมะทั้งหมด : " + filteredAnimeList.size.toString() + " เรื่อง"
                }

                override fun onFailure(call: Call<List<AnimeClass>>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Error onFailure" + t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    fun filterAnimeList(query: String) {
        val regexQuery = Regex(".*${query.toLowerCase()}.*")
        val filteredList = animeList.filter { anime ->
            anime.NameEN.toLowerCase().matches(regexQuery) || anime.NameTH.toLowerCase().matches(regexQuery)
        }

        filteredAnimeList.clear()
        filteredAnimeList.addAll(filteredList)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}