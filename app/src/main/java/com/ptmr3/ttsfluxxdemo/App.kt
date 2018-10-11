package com.ptmr3.ttsfluxxdemo

import android.app.Application
import com.ptmr3.ttsfluxxdemo.injection.Modules
import org.koin.android.ext.android.startKoin

/**
 * This class is the first stop in the application lifecycle. This class handles global, one-time
 * setup
 */
class App : Application() {
    /**
     * * Initializes Koin D.I using the list of modules from Modules class
     */
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, Modules.instance(this).modules)
    }
}