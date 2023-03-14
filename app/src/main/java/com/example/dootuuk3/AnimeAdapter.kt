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


class AnimeAdapter (val items :List<AnimeClass>, val context: Context):
    RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    inner class ViewHolder(view:View, val binding: AnimeItemLayoutBinding): RecyclerView.ViewHolder(view){
        init {
            binding.Picture.setOnClickListener{
                val intent = Intent(view.context, AllDetailActivity::class.java)
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnimeItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        binding.NameTH?.text = items[position].NameTH
        binding.Synopsis?.text = items[position].Synopsis

        Glide.with(context).load(items[position].Picture).into(binding.Picture)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}