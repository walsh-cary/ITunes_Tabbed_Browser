package com.example.itunestabbedbrowser.model

data class MusicResponse(var resultCount: Int = 0, var results: List<MusicPoko>? = null)

data class MusicPoko (
    var artistName: String = "no data",
    var collectionName: String = "no data",
    var artworkUrl60: String = "no data",
    var trackPrice: Float = 0.0f,
    var previewUrl: String = "no data",
    var trackName: String = "no data"
)
