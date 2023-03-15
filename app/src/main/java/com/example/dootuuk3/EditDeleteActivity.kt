package com.example.dootuuk3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.dootuuk3.databinding.ActivityEditDeleteBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class EditDeleteActivity : AppCompatActivity() {
    private lateinit var bindingEditDelete: ActivityEditDeleteBinding
    val createClient = AnimeAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingEditDelete = ActivityEditDeleteBinding.inflate(layoutInflater)
        setContentView(bindingEditDelete.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar

        val mID = intent.getStringExtra("mID")
        val mNameTH = intent.getStringExtra("mNameTH")
        val mNameJP = intent.getStringExtra("mNameJP")
        val mNameEN = intent.getStringExtra("mNameEN")
        val mSynopsis = intent.getStringExtra("mSynopsis")
        val mGenre = intent.getStringExtra("mGenre")
        val mEpisode = intent.getStringExtra("mEpisode")
        val mType = intent.getStringExtra("mType")
        val mSeason = intent.getStringExtra("mSeason")
        val mYear = intent.getStringExtra("mYear")
        val mAirDate = intent.getStringExtra("mAirDate")
        val mEndDate = intent.getStringExtra("mEndDate")
        val mStatus = intent.getStringExtra("mStatus")
        val mStudio = intent.getStringExtra("mStudio")

        bindingEditDelete.edtNameTHq.setText(mNameTH)
        bindingEditDelete.edtnameJPq.setText(mNameJP)
        bindingEditDelete.edtNameENq.setText(mNameEN)
        bindingEditDelete.edtSynopsisq.setText(mSynopsis)
        bindingEditDelete.edtGenreq.setText(mGenre)
        bindingEditDelete.edtEpisodeq.setText(mEpisode)
        bindingEditDelete.edtTypeq.setText(mType)
        bindingEditDelete.edtSeasonq.setText(mSeason)
        bindingEditDelete.edtYearq.setText(mYear)
        bindingEditDelete.edtairdateq.setText(mAirDate)
        bindingEditDelete.edtenddateq.setText(mEndDate)
        bindingEditDelete.edtstatusq.setText(mStatus)
        bindingEditDelete.edtstudioq.setText(mStudio)

    }
    fun saveAnime(v: View) {
        createClient.updateAnime(
            bindingEditDelete.edtNameTHq.text.toString(),
                    bindingEditDelete.edtnameJPq.text.toString(),
                    bindingEditDelete.edtNameENq.text.toString(),
                    bindingEditDelete.edtSynopsisq.text.toString(),
                    bindingEditDelete.edtGenreq.text.toString(),
                    bindingEditDelete.edtEpisodeq.text.toString().toInt(),
                    bindingEditDelete.edtTypeq.text.toString(),
                    bindingEditDelete.edtSeasonq.text.toString(),
                    bindingEditDelete.edtYearq.text.toString().toInt(),
                    bindingEditDelete.edtairdateq.text.toString(),
                    bindingEditDelete.edtenddateq.text.toString(),
                    bindingEditDelete.edtstatusq.text.toString(),
                    bindingEditDelete.edtstudioq.text.toString(),
                    bindingEditDelete.edtsourceq.text.toString(),
                    bindingEditDelete.edtpictureq.text.toString()
        ).enqueue(object : retrofit2.Callback<AnimeClass> {
            override fun onResponse(call: Call<AnimeClass>,response: Response<AnimeClass>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        applicationContext, " Seccessfully Updated",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext, " Updated Failure",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            override fun onFailure(call: Call<AnimeClass>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure" + t.message,
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}