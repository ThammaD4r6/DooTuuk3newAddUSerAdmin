package com.example.dootuuk3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.dootuuk3.databinding.ActivityInsertBinding
import com.example.dootuuk3.AnimeClass

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InsertActivity : AppCompatActivity() {
    private lateinit var bindingInsert : ActivityInsertBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInsert = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(bindingInsert.root)
    }
    fun reset(v: View){
        bindingInsert.edtnameTH.text?.clear()
        bindingInsert.edtnameJP.text?.clear()
        bindingInsert.edtnameEN.text?.clear()
        bindingInsert.edtsynopsis.text?.clear()
        bindingInsert.edtgenre.text?.clear()
        bindingInsert.edtepisode.text?.clear()
        bindingInsert.edttype.text?.clear()
        bindingInsert.edtseason.text?.clear()
        bindingInsert.edtyear.text?.clear()
        bindingInsert.edtairdate.text?.clear()
        bindingInsert.edtenddate.text?.clear()
        bindingInsert.edtstatus.text?.clear()
        bindingInsert.edtstudio.text?.clear()
        bindingInsert.edtsource.text?.clear()
        bindingInsert.edtpic.text?.clear()
    }
    fun addAnime(v:View){
        val serv : AnimeAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeAPI ::class.java)
        serv.insertAnime(
            bindingInsert.edtnameTH.text.toString(),
            bindingInsert.edtnameJP.text.toString(),
            bindingInsert.edtnameEN.text.toString(),
            bindingInsert.edtsynopsis.text.toString(),
            bindingInsert.edtgenre.text.toString(),
            bindingInsert.edtepisode.text.toString().toInt(),
            bindingInsert.edttype.text.toString(),
            bindingInsert.edtseason.text.toString(),
            bindingInsert.edtyear.text.toString().toInt(),
            bindingInsert.edtairdate.text.toString(),
            bindingInsert.edtenddate.text.toString(),
            bindingInsert.edtstatus.text.toString(),
            bindingInsert.edtstudio.text.toString(),
            bindingInsert.edtsource.text.toString(),
            bindingInsert.edtpic.text.toString()).enqueue(object: Callback<AnimeClass> {
            override fun onResponse(call: Call<AnimeClass>, response: retrofit2.Response<AnimeClass>){
                if (response.isSuccessful){
                    Toast.makeText(applicationContext,"Successfully Inserted",Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AnimeClass>, t: Throwable) {
                Toast.makeText(applicationContext,"Error on Failure" + t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}
