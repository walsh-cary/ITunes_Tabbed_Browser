package com.example.itunestabbedbrowser.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "music_table")
class MusicEntity (
    @ColumnInfo(name = "music_title")
    val title : String,

    @ColumnInfo(name = "music_artist")
    val artist : String,

    @ColumnInfo(name = "music_genre")
    val genre : String,

    @ColumnInfo(name = "music_album_cover_url")
    val albumCover : String,

    @ColumnInfo(name = "music_preview_url")
    val musicPreview : String,

    @ColumnInfo(name = "music_price")
    val price : Float
) {

}