package com.example.dootuuk3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dootuuk3.databinding.ActivityShowDetailBinding
import com.example.dootuuk3.databinding.ActivityShowEditDeleteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowDetailActivity : AppCompatActivity() {
    private lateinit var bindingShow: ActivityShowDetailBinding
    var animeListED = arrayListOf<AnimeClass>()
    val createClient = AnimeAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingShow = ActivityShowDetailBinding.inflate(layoutInflater)
        setContentView(bindingShow.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar

        bindingShow.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        bindingShow.recyclerView.addItemDecoration(
            DividerItemDecoration(
                bindingShow.recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onResume() {
        super.onResume()
        callAnime()
    }

    fun callAnime() {
        animeListED.clear()
        createClient.allanime()
            .enqueue(object : Callback<List<AnimeClass>> {
                override fun onResponse(
                    call: Call<List<AnimeClass>>,
                    response: Response<List<AnimeClass>>
                ) {
                    response.body()?.forEach {
                        animeListED.add(
                            (AnimeClass(
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
                            ))
                        )
                    }
                    bindingShow.recyclerView.adapter = EditAnimesAdapter(animeListED, applicationContext)
                }

                override fun onFailure(call: Call<List<AnimeClass>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(applicationContext, "Error2", Toast.LENGTH_LONG).show()
                }
            })
    }
}