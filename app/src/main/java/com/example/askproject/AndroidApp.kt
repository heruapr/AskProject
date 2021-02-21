package com.example.askproject

import android.app.Application
import android.util.Log

/** 2021-02-21 0:22 Created by: Heru Apr */
class AndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("I am invoked", "Hey I am invoked!")
    }
}