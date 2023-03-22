package com.example.dootuuk3

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*

interface UserAPI {
    @GET("login/{username}/{password}")
    fun userLogin(
        @Path("username") username:String,
        @Path("email") email:String,
        @Path("password") password:String
    ):Call<LoginUserClass>

    @FormUrlEncoded
    @POST("user")
    fun InsertUser(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String):Call<UserClass>

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
