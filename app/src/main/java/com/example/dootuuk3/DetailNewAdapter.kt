package com.example.dootuuk3

import AllDetailActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dootuuk3.databinding.AnimeItemLayoutBinding
import com.example.dootuuk3.databinding.DetailItemLayoutBinding

class DetailNewAdapter (val animeListDT: ArrayList<AnimeClass>?, val context: Context):
    RecyclerView.Adapter<DetailNewAdapter.ViewHolder>(){

    inner class ViewHolder(view: View, val bindingDT: DetailItemLayoutBinding) :
        RecyclerView.ViewHolder(view) {
        init {
            bindingDT.Picture.setOnClickListener{
                val contextView: Context = view.context
                val intent = Intent(contextView,AllDetailActivity::class.java)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingDT = DetailItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(bindingDT.root,bindingDT)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bindingDT = holder.bindingDT

        bindingDT.NameTH?.text = animeListDT!![position].NameTH
        bindingDT.NameJP?.text = animeListDT!![position].NameJP
        bindingDT.NameEN?.text = animeListDT!![position].NameEN
        bindingDT.Episode?.text = animeListDT!![position].Episode.toString()
        bindingDT.Type?.text = animeListDT!![position].Type
        bindingDT.Genre?.text = animeListDT!![position].Genre
        bindingDT.Season?.text = animeListDT!![position].Season
        bindingDT.Year?.text = animeListDT!![position].Year.toString()
        bindingDT.AirDate?.text = animeListDT!![position].Air_Date
        bindingDT.EndDate?.text = animeListDT!![position].End_Date
        bindingDT.Studio?.text = animeListDT!![position].Studio
        bindingDT.Status?.text = animeListDT!![position].Status
        bindingDT.Synopsis?.text = animeListDT!![position].Synopsis

        Glide.with(context).load(animeListDT!![position].Picture).into(bindingDT.Picture)
    }


    override fun getItemCount(): Int {
        return animeListDT!!.size
    }

}