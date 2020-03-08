package com.example.itunestabbedbrowser.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itunestabbedbrowser.R
import com.example.itunestabbedbrowser.model.MusicResponse

class MusicAdapter : RecyclerView.Adapter<MusicViewHolder>() {
    private val TAG = MusicAdapter::class.java.simpleName

    var dataSet: MusicResponse? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder =
        MusicViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.music_card_view,
                    parent,
                    false)
        )

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount() executed: " + dataSet?.data?.size)
        return dataSet?.data?.size ?: 0
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder() executed: " + dataSet?.data?.get(position))
        dataSet?.data?.get(position)?.let {
            holder.onBind(it)
        }
    }
}