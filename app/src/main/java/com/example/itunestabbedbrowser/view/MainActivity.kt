package com.example.itunestabbedbrowser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.itunestabbedbrowser.R
import com.example.itunestabbedbrowser.model.MusicResponse
import com.example.itunestabbedbrowser.viewmodel.MusicViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private val TAG = MainActivity::class.java.simpleName

    val musicAdapter : MusicAdapter by lazy { MusicAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = musicAdapter

        val musicViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return MusicViewModel("https://itunes.apple.com/") as T
                }
            }
        ).get(MusicViewModel::class.java)

        tab_genres.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) { }
            override fun onTabUnselected(p0: TabLayout.Tab?) { }
            override fun onTabSelected(p0: TabLayout.Tab?) {
                when(p0!!.position){
                    0 -> populateRockMusic(musicViewModel)
                    1 -> populateClassicMusic(musicViewModel)
                    2 -> populatePopMusic(musicViewModel)
                    else->{ }
                }
            }
        })

        populateRockMusic(musicViewModel)
        musicAdapter.dataSet = musicViewModel.getClassicDataset().value
        musicViewModel.getMusic()
    }

    private fun populateRockMusic(musicViewModel : MusicViewModel){
        Log.d(TAG, "popuateRockMusic() executed")
        musicViewModel.getRockDataSet().observe(
            this@MainActivity,
            object : Observer<MusicResponse> {
                override fun onChanged(t: MusicResponse?) {
                    musicAdapter.dataSet = t
                }
            })
    }

    private fun populateClassicMusic(musicViewModel: MusicViewModel){
        musicViewModel.getClassicDataset().observe(
            this@MainActivity,
            object : Observer<MusicResponse> {
                override fun onChanged(t: MusicResponse?) {
                    musicAdapter.dataSet = t
                }
            })
    }

    private fun populatePopMusic(musicViewModel: MusicViewModel){
        musicViewModel.getPopDataSet().observe(
            this@MainActivity,
            object : Observer<MusicResponse> {
                override fun onChanged(t: MusicResponse?) {
                    musicAdapter.dataSet = t
                }
            })
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }


}
