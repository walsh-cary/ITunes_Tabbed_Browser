package com.example.itunestabbedbrowser.model

import retrofit2.Call
import retrofit2.http.GET

interface MusicApi {
    @GET("search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    fun getRock(): Call<MusicResponse>

    @GET("search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    fun getPop(): Call<MusicResponse>

    @GET("search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    fun getClassic(): Call<MusicResponse>
}