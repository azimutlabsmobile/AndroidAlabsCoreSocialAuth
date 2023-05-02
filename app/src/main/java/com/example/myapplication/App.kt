package com.example.myapplication

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initFacebookSDK()
    }

    private fun initFacebookSDK() {
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}