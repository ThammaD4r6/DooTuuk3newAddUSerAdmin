package com.example.dootuuk3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dootuuk3.databinding.AnimeItemLayoutBinding
import com.example.dootuuk3.databinding.DetailItemLayoutBinding

class AnimeDetailAdapter (val items: List<AnimeClass>,val context: Context):
        RecyclerView.Adapter<AnimeDetailAdapter.ViewHolder>(){
    inner class ViewHolder (view: View, val binding: AnimeItemLayoutBinding):
        RecyclerView.ViewHolder(view){

        init {
            binding.Picture.setOnClickListener{
                val item = items[adapterPosition]
                val contextView: Context = view.context
                val intent = Intent(contextView, DetailItemLayoutBinding::class.java)

                intent.putExtra("mID", item.ID.toString())
                intent.putExtra("mNameTH", item.NameTH)
                intent.putExtra("mNameJP", item.NameJP)
                intent.putExtra("mNameEN", item.NameEN)
                intent.putExtra("mSynopsis", item.Synopsis)
                intent.putExtra("mGenre", item.Genre)
                intent.putExtra("mEpisode", item.Episode.toString())
                intent.putExtra("mType", item.Type)
                intent.putExtra("mSeason", item.Season)
                intent.putExtra("mYear", item.Year.toString())
                intent.putExtra("mAirDate", item.Air_Date)
                intent.putExtra("mEndDate", item.End_Date)
                intent.putExtra("mStatus", item.Status)
                intent.putExtra("mStudio", item.Studio)
                intent.putExtra("mSource", item.Source)
                intent.putExtra("mPicture", item.Picture)

                contextView.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnimeItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding.NameTH?.text = items[position].NameTH
        binding.Synopsis?.text = items[position].Synopsis
    }

    override fun getItemCount(): Int {
        return items.size
    }

}