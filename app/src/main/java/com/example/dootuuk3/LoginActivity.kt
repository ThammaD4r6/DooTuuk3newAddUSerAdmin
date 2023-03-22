package com.example.dootuuk3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dootuuk3.databinding.ActivityLoginBinding
import com.example.dootuuk3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var bindingLG : ActivityLoginBinding
    lateinit var session: SessionManager
    val createClient = AnimeAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLG = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingLG.root )
        bindingLG.btnRegister.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        session = SessionManager(applicationContext)
        if (session.isLoggedIn()) {
            var i: Intent = Intent(applicationContext, ShowUserActivity::class.java)
            startActivity(i)
            finish()
        }
        bindingLG.submitlg.setOnClickListener(){
            var user = bindingLG.edtUser.text.toString()
            var password = bindingLG.edtPassword.text.toString()
            if(user.isEmpty() || password.isEmpty()){
                Toast.makeText(applicationContext,"Enter username and password.", Toast.LENGTH_LONG).show()
            }else{
                createClient.userLogin(user,password)
                    .enqueue(object : Callback<LoginUserClass> {
                        override fun onResponse(call: Call<LoginUserClass>, response: Response<LoginUserClass>) {
                            val success =response.body()?.success.toString().toInt()
                            if(success == 1){
                                val id_back = response.body()?.userid.toString()
                                val username_back = response.body()?.Username.toString()
                                session.createLoginSession(username_back, id_back, user)
                                val intent = Intent(applicationContext, AdminActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            if(success == 2){
                                val id_back = response.body()?.userid.toString()
                                val username_back = response.body()?.Username.toString()
                                session.createLoginSession(username_back, id_back, user)
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(applicationContext,"Enter username and password.", Toast.LENGTH_LONG).show() }
                        }
                        override fun onFailure(call: Call<LoginUserClass>, t: Throwable) {
                            Toast.makeText(applicationContext,"Error onFailure " + t.message,Toast.LENGTH_LONG).show()
                        }
                    })

            }
        }
    }
}