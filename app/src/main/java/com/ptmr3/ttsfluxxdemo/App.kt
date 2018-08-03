package com.ptmr3.ttsfluxxdemo

import android.app.Application
import com.ptmr3.ttsfluxxdemo.di.Modules
import org.koin.android.ext.android.startKoin

class App : Application() {
    private val mModules = Modules(applicationContext)

    override fun onCreate() {
        super.onCreate()
        startKoin(this, mModules.modules)
    }
}