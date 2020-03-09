package com.example.itunestabbedbrowser.model

import android.content.Context
import android.net.ConnectivityManager
import com.example.itunestabbedbrowser.view.CustomApplication
import com.example.itunestabbedbrowser.viewmodel.MusicViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient

class MusicRepository (private val musicViewModel: MusicViewModel) {
    val cacheMax = (5 * 1024 * 1024).toLong()
    lateinit var cache: Cache

    fun isOnline(): Boolean {
        val connectivityManager: ConnectivityManager =
            CustomApplication.getCustomApp().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }

    fun getMusic(baseUrl: String, context: Context) {
        cache = Cache(context.cacheDir, cacheMax)
        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (isOnline())
                    request.newBuilder().header(
                        "MusicAppPlayer-Data-Cache",
                        "public, max-age=" + 5
                    ).build()
                else
                    request.newBuilder().header(
                        "MusicAppPlayer-Data-Cache",
                        "public, only-if-cached, max-stale=" + 60 * 10
                    ).build()
                chain.proceed(request)
            }.build()
        val network = Network(musicViewModel)
        network.initRetrofit(baseUrl, okHttpClient)
    }
}