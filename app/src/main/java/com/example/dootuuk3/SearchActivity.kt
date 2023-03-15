package com.example.dootuuk3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import com.example.dootuuk3.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchActivity : AppCompatActivity() {
    private lateinit var bindingMovie: ActivitySearchBinding
    var animeList = arrayListOf<AnimeClass>()
    val createClient = AnimeAPI.create()

    private fun retrieveAnime() {
        createClient.allanime().enqueue(object : Callback<List<AnimeClass>> {
            override fun onResponse(
                call: Call<List<AnimeClass>>,
                response: Response<List<AnimeClass>>
            ) {
                if (response.isSuccessful) {
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
                    bindingMovie.recyclerView3.adapter =
                        SearchAdapter(animeList, applicationContext)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Error: " + response.message(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<AnimeClass>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error onFailure", Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMovie = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(bindingMovie.root)

        val searchView: android.widget.SearchView = findViewById(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                animeList.clear()
                if (searchView.query.toString().isEmpty()) {
                    retrieveAnime()
                } else {
                    createClient.retrieveAnimeByName(searchView.query.toString())
                        .enqueue(object : Callback<List<AnimeClass>> {
                            override fun onResponse(
                                call: Call<List<AnimeClass>>,
                                response: Response<List<AnimeClass>>
                            ) {
                                if (response.isSuccessful) {
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
                                    bindingMovie.recyclerView3.adapter =
                                        SearchAdapter(animeList, applicationContext)
                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        "NOT Found: " + searchView.query,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<List<AnimeClass>>, t: Throwable) {
                                Toast.makeText(
                                    applicationContext,
                                    "Error onFailure",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                        })
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                animeList.clear()
                if (searchView.toString().isEmpty()) {
                    retrieveAnime()
                } else {
                    createClient.retrieveAnimeByName(searchView.query.toString())
                        .enqueue(object : Callback<List<AnimeClass>> {
                            override fun onResponse(
                                call: Call<List<AnimeClass>>,
                                response: Response<List<AnimeClass>>
                            ) {
                                if (response.isSuccessful) {
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
                                    bindingMovie.recyclerView3.adapter =
                                        SearchAdapter(animeList, applicationContext)
                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        "NOT Found: " + searchView.query,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<List<AnimeClass>>, t: Throwable) {
                                Toast.makeText(
                                    applicationContext,
                                    "Error onFailure",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                        })
                }

                return true
            }
        })
    }
}