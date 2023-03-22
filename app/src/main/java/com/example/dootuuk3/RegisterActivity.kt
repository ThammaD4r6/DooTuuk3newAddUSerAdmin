package com.example.dootuuk3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.dootuuk3.databinding.ActivityInsertBinding
import com.example.dootuuk3.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var bindingRG : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRG = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bindingRG.root)

        setContentView(R.layout.activity_register)
    }
    fun resetRG(v: View){
        bindingRG.rgUsername.text?.clear()
        bindingRG.rgEmail.text?.clear()
        bindingRG.rgPassword.text?.clear()

    }
    fun addUser(v:View){
        val serv : UserAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI ::class.java)
        serv.InsertUser(
            bindingRG.rgUsername.text.toString(),
            bindingRG.rgEmail.text.toString(),
            bindingRG.rgPassword.text.toString()).enqueue(object: Callback<UserClass> {
            override fun onResponse(call: Call<UserClass>, response: retrofit2.Response<UserClass>){
                if (response.isSuccessful){
                    Toast.makeText(applicationContext,"Successfully Register", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext,"Error", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<UserClass>, t: Throwable) {
                Toast.makeText(applicationContext,"Error on Failure" + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
