package com.example.itunestabbedbrowser.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itunestabbedbrowser.model.MusicApi
import com.example.itunestabbedbrowser.model.MusicRepository
import com.example.itunestabbedbrowser.model.MusicResponse
import com.example.itunestabbedbrowser.model.Network
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MusicViewModel (val baseUrl: String) : ViewModel() {
    private val TAG = MusicViewModel::class.java.simpleName

    private val rockDataSet : MutableLiveData<MusicResponse> = MutableLiveData()
    private val classicDataSet : MutableLiveData<MusicResponse> = MutableLiveData()
    private val popDataSet : MutableLiveData<MusicResponse> = MutableLiveData()

    fun getRockDataSet() : LiveData<MusicResponse>{
        Log.d(TAG, "getRockDataSet() executed: " + rockDataSet)
        return rockDataSet
    }

    fun getClassicDataset() : LiveData<MusicResponse>{
        return classicDataSet
    }

    fun getPopDataSet() : LiveData<MusicResponse>{
        return popDataSet
    }

    fun getMusic(context: Context){
        Log.d(TAG, "getMusic() executed")
        val musicRepository = MusicRepository(this)
        musicRepository.getMusic(baseUrl, context)
    }

    fun getRockMusicData(dataSet : MusicResponse){
        this.rockDataSet.value = dataSet
        Log.d(TAG, "getRockMusicData() executed: " + dataSet)
    }

    fun getClassicMusicData(dataSet: MusicResponse){
        this.classicDataSet.value = dataSet
    }

    fun getPopMusicData(dataSet: MusicResponse){
        this.popDataSet.value = dataSet
    }
}