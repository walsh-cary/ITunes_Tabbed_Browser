package com.example.itunestabbedbrowser.model

import android.util.Log
import com.example.itunestabbedbrowser.viewmodel.MusicViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network(val viewModel : MusicViewModel){
    private val TAG = Network::class.java.simpleName
    fun initRetrofit(baseUrl: String) {
        Log.d(TAG, "initRetrofit() executed")
        getApi(baseUrl).getRock()
            .enqueue(
                object : Callback<MusicResponse>{
                    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                        //to do failure
                    }
                    override fun onResponse(
                        call: Call<MusicResponse>,
                        response: Response<MusicResponse>
                    ) {
                        response.body()?.let { viewModel.getRockMusicData(it) }
                        Log.d(TAG, "response.body() executed: " + response)
                    }
                }
            )
        getApi(baseUrl).getClassic()
            .enqueue(
                object : Callback<MusicResponse>{
                    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                        //to do failure
                    }
                    override fun onResponse(
                        call: Call<MusicResponse>,
                        response: Response<MusicResponse>
                    ) {
                        response.body()?.let { viewModel.getClassicMusicData(it) }
                        Log.d(TAG, "response.body() executed: " + response)
                    }
                }
            )

        getApi(baseUrl).getPop()
            .enqueue(
                object : Callback<MusicResponse>{
                    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                        //to do failure
                    }
                    override fun onResponse(
                        call: Call<MusicResponse>,
                        response: Response<MusicResponse>
                    ) {
                        response.body()?.let { viewModel.getPopMusicData(it) }
                    }
                }
            )
    }

    fun getApi(url: String) : MusicApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(MusicApi::class.java)
    }
}