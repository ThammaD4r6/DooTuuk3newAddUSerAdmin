package com.example.dootuuk3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dootuuk3.databinding.ActivityShowEditDeleteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowEditDeleteActivity : AppCompatActivity() {
    private lateinit var bindingShow: ActivityShowEditDeleteBinding
    var animeListED = arrayListOf<AnimeClass>()
    val createClient = AnimeAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingShow = ActivityShowEditDeleteBinding.inflate(layoutInflater)
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
        bindingShow.edtSearch.text?.clear()
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
                    bindingShow.recyclerView.adapter =
                        EditAnimesAdapter(animeListED, applicationContext)
                }

                override fun onFailure(call: Call<List<AnimeClass>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(applicationContext, "Error2", Toast.LENGTH_LONG).show()
                }
            })
    }

    fun clickSearch(v: View) {
        animeListED.clear()
        if (bindingShow.edtSearch.text!!.isEmpty()) {
            callAnime()
        } else {
            createClient.retrievenimeID(bindingShow.edtSearch.text.toString())
                .enqueue(object : Callback<AnimeClass> {
                    override fun onResponse(
                        call: Call<AnimeClass>,
                        response: Response<AnimeClass>
                    ) {
                        if (response.isSuccessful) {
                            animeListED.add(
                                AnimeClass(
                                    response.body()?.ID.toString().toInt(),
                                    response.body()?.NameTH.toString(),
                                    response.body()?.NameJP.toString(),
                                    response.body()?.NameEN.toString(),
                                    response.body()?.Synopsis.toString(),
                                    response.body()?.Genre.toString(),
                                    response.body()?.Episode.toString().toInt(),
                                    response.body()?.Type.toString(),
                                    response.body()?.Season.toString(),
                                    response.body()?.Year.toString().toInt(),
                                    response.body()?.Air_Date.toString(),
                                    response.body()?.End_Date.toString(),
                                    response.body()?.Status.toString(),
                                    response.body()?.Studio.toString(),
                                    response.body()?.Source.toString(),
                                    response.body()?.Picture.toString()
                                )
                            )
                            bindingShow.recyclerView.adapter =
                                EditAnimesAdapter(animeListED, applicationContext)
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "ID not found",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<AnimeClass>, t: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            "Error" + t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }
}