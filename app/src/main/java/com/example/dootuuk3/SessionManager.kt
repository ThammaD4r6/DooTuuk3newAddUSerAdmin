package com.example.dootuuk3

import android.content.Context
import android.content.SharedPreferences

class SessionManager {
    var pref: SharedPreferences
    var edior: SharedPreferences.Editor
    var context: Context
    var PRIVATE_MODE: Int = 0

    constructor(context: Context) {
        this.context = context
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        edior = pref.edit()
    }
    companion object {
        val PREF_NAME: String = "SessionDemo"
        val IS_LOGIN: String = "isLogin"
        val KEY_NAME: String = "username"
        val KEY_EMAIL: String = "email"
    }
    fun createLoginSession(username: String, id: String, email: String) {
        edior.putBoolean(IS_LOGIN, true)
        edior.putString(KEY_NAME, username)
        edior.putString(KEY_EMAIL, email)
        edior.commit()
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }
}