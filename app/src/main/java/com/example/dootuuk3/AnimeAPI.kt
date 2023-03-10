package com.example.dootuuk3

import com.example.dootuuk3.AnimeClass

import retrofit2.Call
import retrofit2.http.*

interface AnimeAPI {
    @GET("allanime")
    fun retrieveAnime():Call<List<AnimeClass>>

    @GET("random")
    fun randomAnime():Call<List<AnimeClass>>

    @GET("anime/{nameTH}")
    fun retrievenimeNameTH(
        @Path("nameTH") NameTH: String
    ):  Call<AnimeClass>

    @FormUrlEncoded
    @POST("anime")
    fun insertAnime(
        @Field("NameTH") NameTH: String,
        @Field("NameJP") NameJP: String,
        @Field("NameEN") NameEN: String,
        @Field("Synopsis") Synopsis: String,
        @Field("Genre") Genre: String,
        @Field("Episode") Episode: Int,
        @Field("Type") Type: String,
        @Field("Season") Season: String,
        @Field("Year") Year: Int,
        @Field("Air_Date") Air_Date: String,
        @Field("End_Date") End_Date: String,
        @Field("Status") Status: String,
        @Field("Studio") Studio: String,
        @Field("Source") Source: String,
        @Field("Picture") Picture: String):Call<AnimeClass>

}