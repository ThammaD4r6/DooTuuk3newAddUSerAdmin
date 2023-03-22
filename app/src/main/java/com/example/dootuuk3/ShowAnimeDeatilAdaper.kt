package com.example.dootuuk3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dootuuk3.databinding.AnimeItemLayoutBinding

class ShowAnimeDeatilAdaper (val itemab :List<AnimeClass>, val context: Context):
    RecyclerView.Adapter<ShowAnimeDeatilAdaper.ViewHolder>(){
    inner class ViewHolder (view: View, val binding: AnimeItemLayoutBinding) :
        RecyclerView.ViewHolder(view){
        init {
            binding.Picture.setOnClickListener{
                val item = itemab[adapterPosition]
                val contextView: Context = view.context
                val intent = Intent(contextView, EditDeleteActivity::class.java)

                intent.putExtra("dNameTH", item.NameTH)
                intent.putExtra("dNameJP", item.NameJP)
                intent.putExtra("dNameEN", item.NameEN)
                intent.putExtra("dSynopsis", item.Synopsis)
                intent.putExtra("dGenre", item.Genre)
                intent.putExtra("dEpisode", item.Episode.toString())
                intent.putExtra("dType", item.Type)
                intent.putExtra("dSeason", item.Season)
                intent.putExtra("dYear", item.Year.toString())
                intent.putExtra("dAirDate", item.Air_Date)
                intent.putExtra("dEndDate", item.End_Date)
                intent.putExtra("dStatus", item.Status)
                intent.putExtra("dStudio", item.Studio)
                intent.putExtra("dSource", item.Source)
                intent.putExtra("dPicture", item.Picture)

                contextView.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnimeItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        binding.NameTH?.text = itemab[position].ID.toString()
        binding.Synopsis?.text = itemab[position].NameTH

        Glide.with(context).load(itemab[position].Picture).into(binding.Picture)

    }

    override fun getItemCount(): Int {
        return itemab.size
    }

}