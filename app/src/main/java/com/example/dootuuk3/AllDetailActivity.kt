import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dootuuk3.AnimeAPI
import com.example.dootuuk3.AnimeAdapter
import com.example.dootuuk3.AnimeClass
import com.example.dootuuk3.InsertActivity
import com.example.dootuuk3.databinding.ActivityAllAnimeBinding
import com.example.dootuuk3.databinding.ActivityAllDetailBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllDetailActivity : AppCompatActivity() {

    private lateinit var bindingDTA: ActivityAllDetailBinding
    var animeListDTA = arrayListOf<AnimeClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingDTA = ActivityAllDetailBinding.inflate(layoutInflater)
        setContentView(bindingDTA.root)

        bindingDTA.recyclerView4.layoutManager = LinearLayoutManager(applicationContext)
        bindingDTA.recyclerView4.addItemDecoration(
            DividerItemDecoration(
                bindingDTA.recyclerView4.getContext(),
                DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume() {
        super.onResume()
        allanime()
    }

    fun allanime() {
        animeListDTA.clear();

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
                        animeListDTA.add(
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

                    bindingDTA.recyclerView4.adapter = AnimeAdapter(animeListDTA, applicationContext)
                    bindingDTA.anime.text = "อนิเมะทั้งหมด : "+animeListDTA.size.toString()+" เรื่อง"
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