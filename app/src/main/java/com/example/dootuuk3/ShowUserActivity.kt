package com.example.dootuuk3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dootuuk3.databinding.ActivityShowUserBinding

class ShowUserActivity : AppCompatActivity() {
    private lateinit var bindingSU: ActivityShowUserBinding
    lateinit var session: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSU = ActivityShowUserBinding.inflate(layoutInflater)
        setContentView(bindingSU.root)

        session = SessionManager(applicationContext)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar

        val username: String? = session.pref.getString(SessionManager.KEY_NAME, null)
        bindingSU.txtUsername.text = "Hello \n วันนี้จะดูอนิเมะเรื่องอะไรดี?"
        bindingSU.btnLogout.setOnClickListener() {
            val edit = session.edior
            edit.clear()
            edit.commit()

            var i: Intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}