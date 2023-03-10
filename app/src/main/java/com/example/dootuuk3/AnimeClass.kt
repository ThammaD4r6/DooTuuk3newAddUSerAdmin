package com.example.dootuuk3

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

data class AnimeClass(
    @Expose
    @SerializedName("ID") val ID: Int,

    @Expose
    @SerializedName("NameTH") val NameTH: String,

    @Expose
    @SerializedName("NameJP") val NameJP: String,

    @Expose
    @SerializedName("NameEN") val NameEN: String,

    @Expose
    @SerializedName("Synopsis") val Synopsis: String,

    @Expose
    @SerializedName("Genre") val Genre: String,

    @Expose
    @SerializedName("Episode") val Episode: Int,

    @Expose
    @SerializedName("Type") val Type: String,

    @Expose
    @SerializedName("Season") val Season: String,

    @Expose
    @SerializedName("Year") val Year: Int,

    @Expose
    @SerializedName("Air_Date") val Air_Date: String,

    @Expose
    @SerializedName("End_Date") val End_Date: String,

    @Expose
    @SerializedName("Status") val Status: String,

    @Expose
    @SerializedName("Studio") val Studio: String,

    @Expose
    @SerializedName("Source") val Source: String,

    @Expose
    @SerializedName("Picture") val Picture: String){}
