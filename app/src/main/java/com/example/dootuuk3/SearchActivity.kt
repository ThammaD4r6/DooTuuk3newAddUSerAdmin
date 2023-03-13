package com.example.dootuuk3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dootuuk3.databinding.ActivitySearchBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    private lateinit var bindingSR: ActivitySearchBinding
    var animeListSR = arrayListOf<AnimeClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar

        bindingSR = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(bindingSR.root)

        bindingSR.recyclerView3.layoutManager = LinearLayoutManager(applicationContext)
        bindingSR.recyclerView3.addItemDecoration(
            DividerItemDecoration(
                bindingSR.recyclerView3.getContext(),
                DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume() {
        super.onResume()
        callAnimeData()
    }

    fun callAnimeData() {
        animeListSR.clear();

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
                        animeListSR.add(
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

                    bindingSR.recyclerView3.adapter = AnimeAdapter(animeListSR, applicationContext)
                    bindingSR.animesearch.text = "อนิเมะทั้งหมด : "+animeListSR.size.toString()+" เรื่อง"
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