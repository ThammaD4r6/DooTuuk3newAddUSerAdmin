package com.example.dootuuk3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dootuuk3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.user.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
        binding.current.setOnClickListener() {
            val intent = Intent(this, AllAnimeActivity::class.java)
            startActivity(intent)
        }
        binding.random.setOnClickListener() {
            val intent = Intent(this, RandomActivity::class.java)
            startActivity(intent)
        }
        binding.search.setOnClickListener() {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        binding.add.setOnClickListener() {
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }
    }
}