package com.example.dootuuk3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dootuuk3.databinding.ActivityRandomBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomActivity : AppCompatActivity() {
    private lateinit var bindingRD: ActivityRandomBinding
    var animeListRD = arrayListOf<AnimeClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRD = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(bindingRD.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar

        bindingRD.recyclerView2.adapter = DetailAdapter(this.animeListRD, applicationContext)
        bindingRD.recyclerView2.layoutManager = LinearLayoutManager(applicationContext)
        bindingRD.recyclerView2.addItemDecoration(
            DividerItemDecoration(bindingRD.recyclerView2.getContext(),
                DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume() {
        super.onResume()
        randomAnime()
    }fun randomAnime() {
        animeListRD.clear();
        val serv: AnimeAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeAPI::class.java)

        serv.randomAnime()
            .enqueue(object : Callback<List<AnimeClass>> {

                override fun onResponse(
                    call: Call<List<AnimeClass>>,
                    response: Response<List<AnimeClass>>
                ) {
                    response.body()?.forEach {
                        animeListRD.add(
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

                    bindingRD.recyclerView2.adapter = AnimeAdapter(animeListRD, applicationContext)
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

}