package com.example.dootuuk3

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AnimeAPI {
    @GET("allanime")
    fun allanime():Call<List<AnimeClass>>

    @GET("anime/search/{name}")
    fun retrieveAnimeByName(@Path("name") name: String): Call<List<AnimeClass>>


    @GET("animenamepic/{Picture}")
    fun animedetailpic():Call<List<AnimeClass>>

    @GET("animewinter2023")
    fun retrieveAnimewinter2023():Call<List<AnimeClass>>

    @GET("random")
    fun randomAnime():Call<List<AnimeClass>>

    @GET("anime/{nameTH}")
    fun retrievenimeNameTH(
        @Path("nameTH") NameTH: String
    ):  Call<AnimeClass>

    @GET("anime/{id}")
    fun retrievenimeID(
        @Path("id") id: String
    ):  Call<AnimeClass>

    @GET("animedetail/{id}")
    fun retrievenimeID():Call<List<AnimeClass>>

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

    @FormUrlEncoded
    @PUT("anime/{id}")
    fun updateAnime(
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

    @DELETE("anime/{id}")
    fun deleteAnime(
        @Path("id") id: Int
    ): Call<AnimeClass>

    companion object {
        fun create(): AnimeAPI {
            val AnimeClient: AnimeAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AnimeAPI::class.java)
            return AnimeClient
        }
    }



}