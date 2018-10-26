package com.example.tkontsevich.smack.Controller

import android.app.Application
import com.example.tkontsevich.smack.Untilities.SharedPrefs

class App : Application() {

    companion object {
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}