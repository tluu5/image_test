package com.example.image_test

import android.app.Application
import android.content.Context

class MovieApplication: Application() {
    /**
     * Initialize Firebase, Logger, Analytics
     * Dependency Injection.
     */
    override fun onCreate() {
        super.onCreate()
        movieApplication = applicationContext
    }
    companion object{
        lateinit var movieApplication: Context
    }
}