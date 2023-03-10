package com.example.dootuuk3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dootuuk3.databinding.ActivityAllAnimeBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllAnimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllAnimeBinding
    var animeList = arrayListOf<AnimeClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityAllAnimeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar
//
//        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)
//        binding.recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                binding.recyclerView.getContext(), DividerItemDecoration.VERTICAL
//            )
//        )
        binding = ActivityAllAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.getContext(),
                DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume() {
        super.onResume()
        callAnimeData()
    }

    fun callAnimeData() {
        animeList.clear();

        val serv: AnimeAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeAPI::class.java)

        serv.retrieveAnime()
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

                    binding.recyclerView.adapter = AnimeAdapter(animeList, applicationContext)
                    binding.anime.text = "All Anime : "+animeList.size.toString()
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

    fun clickInsert(v: View) {
        val intent = Intent(this, InsertActivity::class.java)
        startActivity(intent)
    }

}