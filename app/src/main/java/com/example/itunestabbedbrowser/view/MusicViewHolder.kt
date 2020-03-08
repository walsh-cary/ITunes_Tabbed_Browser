package com.example.itunestabbedbrowser.view

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itunestabbedbrowser.R
import com.example.itunestabbedbrowser.model.MusicPoko
import com.example.itunestabbedbrowser.model.MusicResponse

class MusicViewHolder (itemView: View)
    : RecyclerView.ViewHolder(itemView) {
    val tvArtistName: TextView = itemView.findViewById(R.id.tv_artist_name)
    val tvCollectionName: TextView = itemView.findViewById(R.id.tv_collection_name)
    val ivCover: ImageView = itemView.findViewById(R.id.iv_cover)
    val tvTrackPrice: TextView = itemView.findViewById(R.id.tv_price)
    val tvTrackName: TextView = itemView.findViewById(R.id.tv_track_name)

    fun onBind(item: MusicPoko) {
        tvArtistName.text = item.artistName
        tvCollectionName.text = item.collectionName
        tvTrackPrice.text = item.trackPrice.toString()
        Glide.with(itemView).load(item.artworkUrl60).into(ivCover)
        tvTrackName.text = item.trackName

        itemView.setOnClickListener(
            object : View.OnClickListener{
                override fun onClick(v: View?) {
                    val intent : Intent = Intent(android.content.Intent.ACTION_VIEW)
                    intent.setDataAndType(Uri.parse(item.previewUrl), "audio/*")
                    itemView.context.startActivity(intent)
                }
            }
        )
    }
}