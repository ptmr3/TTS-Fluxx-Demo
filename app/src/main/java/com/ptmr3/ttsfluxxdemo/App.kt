package com.ptmr3.ttsfluxxdemo

import com.ptmr3.ttsfluxxdemo.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<App> {
        return DaggerAppComponent.builder().create(this) as DaggerAppComponent
    }
}