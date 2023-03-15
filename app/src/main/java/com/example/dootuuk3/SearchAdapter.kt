package com.example.dootuuk3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dootuuk3.databinding.SearchItemLayoutBinding

class SearchAdapter (val items :List<AnimeClass>, val context: Context):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(view:View, val binding: SearchItemLayoutBinding):
        RecyclerView.ViewHolder(view){
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return ViewHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        binding.NameTH?.text = items[position].NameTH
        binding.NameEN?.text = items[position].NameEN
        binding.Synopsis?.text = items[position].Synopsis

        if (items[position].Picture != null) {
            Glide.with(context).load(items[position].Picture).into(binding.Picture)
        } else {
            Toast.makeText(context, "Picture is null", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}