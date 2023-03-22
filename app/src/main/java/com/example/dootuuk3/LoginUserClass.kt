package com.example.dootuuk3

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginUserClass (
    @Expose
    @SerializedName("success") val success: Int,

    @Expose
    @SerializedName("id") val userid: Int,

    @Expose
    @SerializedName("Username") val Username: String,

    @Expose
    @SerializedName("Email") val Email: String,

    @Expose
    @SerializedName("Password") val Password: String){}
