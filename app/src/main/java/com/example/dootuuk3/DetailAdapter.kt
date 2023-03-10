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

        bindingRD.NameTH?.text = "ชื่อเรื่อง (ไทย) : "+ animeListRD!![position].NameTH
        bindingRD.NameJP?.text = "ชื่อเรื่อง (ญี่ปุ่น) : "+ animeListRD!![position].NameJP
        bindingRD.NameJP?.text = "เรื่องย่อ : "+ animeListRD!![position].Synopsis

        Glide.with(context).load(animeListRD!![position].Picture).into(bindingRD.Picture)
    }

    override fun getItemCount(): Int {
        return animeListRD!!.size
    }

}