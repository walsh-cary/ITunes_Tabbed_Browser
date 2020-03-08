package com.example.itunestabbedbrowser.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

interface DaoMusic {
    @Dao
    interface DaoMovies {
        @Insert
        fun saveDataIntoCache(data: List<MusicResponse>)

        @Query("SELECT * FROM music_table WHERE music_genre = 'rock'")
        fun getRockMusic() : List<MusicResponse>

        @Query("SELECT * FROM music_table WHERE music_genre = 'classic'")
        fun getClassicMusic() : List<MusicResponse>

        @Query("SELECT * FROM music_table WHERE music_genre = 'pop'")
        fun getPopMusic() : List<MusicResponse>
    }
}
