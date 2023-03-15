package com.example.dootuuk3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dootuuk3.databinding.ActivityAllDetailBinding
import com.example.dootuuk3.databinding.ActivityRandomBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllDetailActivity : AppCompatActivity() {
    private lateinit var bindingDTT: ActivityAllDetailBinding
    var animeListDTT = arrayListOf<AnimeClass>()
    val client = AnimeAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDTT = ActivityAllDetailBinding.inflate(layoutInflater)
        setContentView(bindingDTT.root)

        bindingDTT.recyclerView4.adapter = DetailNewAdapter(this.animeListDTT, applicationContext)
        bindingDTT.recyclerView4.layoutManager = LinearLayoutManager(applicationContext)
        bindingDTT.recyclerView4.addItemDecoration(
            DividerItemDecoration(bindingDTT.recyclerView4.getContext(),
                DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume() {
        super.onResume()
        allanime()
    }fun allanime() {
        animeListDTT.clear();
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
                        animeListDTT.add(
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

                    bindingDTT.recyclerView4.adapter = DetailNewAdapter(animeListDTT, applicationContext)
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