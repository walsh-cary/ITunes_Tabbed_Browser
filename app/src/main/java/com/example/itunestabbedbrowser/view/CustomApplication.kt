package com.example.itunestabbedbrowser.view

import android.app.Application

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        customApp = applicationContext as CustomApplication

    }

    companion object {
        private var customApp: CustomApplication? = null
        fun getCustomApp(): CustomApplication {
            return customApp!!

        }
    }

}