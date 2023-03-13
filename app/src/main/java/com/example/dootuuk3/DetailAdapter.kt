package com.example.dootuuk3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dootuuk3.databinding.DetailItemLayoutBinding

class DetailAdapter (val animeListRD: ArrayList<AnimeClass>?, val context: Context):
RecyclerView.Adapter<DetailAdapter.ViewHolder>(){

    class ViewHolder(view: View, val bindingRD: DetailItemLayoutBinding) :
    RecyclerView.ViewHolder(view){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingRD = DetailItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(bindingRD.root,bindingRD)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bindingRD = holder.bindingRD

        bindingRD.NameTH?.text = animeListRD!![position].NameTH
        bindingRD.NameJP?.text = animeListRD!![position].NameJP
        bindingRD.NameEN?.text = animeListRD!![position].NameEN
        bindingRD.Episode?.text = animeListRD!![position].Episode.toString()
        bindingRD.Type?.text = animeListRD!![position].Type
        bindingRD.Genre?.text = animeListRD!![position].Genre
        bindingRD.Season?.text = animeListRD!![position].Season
        bindingRD.Year?.text = animeListRD!![position].Year.toString()
        bindingRD.AirDate?.text = animeListRD!![position].Air_Date
        bindingRD.EndDate?.text = animeListRD!![position].End_Date
        bindingRD.Studio?.text = animeListRD!![position].Studio
        bindingRD.Status?.text = animeListRD!![position].Status
        bindingRD.Synopsis?.text = animeListRD!![position].Synopsis

        Glide.with(context).load(animeListRD!![position].Picture).into(bindingRD.Picture)
    }

    override fun getItemCount(): Int {
        return animeListRD!!.size
    }

}