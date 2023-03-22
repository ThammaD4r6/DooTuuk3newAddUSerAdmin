package com.example.dootuuk3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.dootuuk3.databinding.DetailItemLayoutBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class TheDetailActivity : AppCompatActivity() {
    private lateinit var bindingTDT: DetailItemLayoutBinding
    val createClient = AnimeAPI.create()
    var animeListTDT = arrayListOf<AnimeClass>()

    var mID:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTDT = DetailItemLayoutBinding.inflate(layoutInflater)
        setContentView(bindingTDT.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar
    }

        val dNameTH = intent.getStringExtra("mNameTH")
        val dNameJP = intent.getStringExtra("mNameJP")
        val dNameEN = intent.getStringExtra("mNameEN")
        val dSynopsis = intent.getStringExtra("mSynopsis")
        val dGenre = intent.getStringExtra("mGenre")
        val dEpisode = intent.getStringExtra("mEpisode")
        val dType = intent.getStringExtra("mType")
        val dSeason = intent.getStringExtra("mSeason")
        val dYear = intent.getStringExtra("mYear")
        val dAirDate = intent.getStringExtra("mAirDate")
        val dEndDate = intent.getStringExtra("mEndDate")
        val dStatus = intent.getStringExtra("mStatus")
        val dStudio = intent.getStringExtra("mStudio")
        val dSource = intent.getStringExtra("mSource")
        val dPicture = intent.getStringExtra("mPicture")
}
