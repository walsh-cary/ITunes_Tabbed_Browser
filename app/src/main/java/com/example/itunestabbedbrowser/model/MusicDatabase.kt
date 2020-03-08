package com.example.itunestabbedbrowser.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DaoMusic::class], version = 1)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun getDao(): DaoMusic

    companion object{
        @Volatile
        private var musicInstance : MusicDatabase? = null

        fun getInstance(context: Context) : MusicDatabase{
            val tempInstance = musicInstance
            if (tempInstance != null)
                return tempInstance
            synchronized(this){
                val instanceRoom = Room.databaseBuilder(
                    context.applicationContext,
                    MusicDatabase::class.java,
                    "movies_db"
                ).build()
                musicInstance = instanceRoom
                return instanceRoom
            }
        }
    }
}