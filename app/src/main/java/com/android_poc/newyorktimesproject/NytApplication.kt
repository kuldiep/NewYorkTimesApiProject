package com.android_poc.newyorktimesproject

import android.app.Application

class NytApplication: Application() {

    companion object {
        private lateinit var nytAppInstance: NytApplication
        fun getApplicationInstance(): NytApplication {
            return nytAppInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        nytAppInstance = this

    }
}