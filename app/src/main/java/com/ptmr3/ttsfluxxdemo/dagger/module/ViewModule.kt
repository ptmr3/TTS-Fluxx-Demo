package com.ptmr3.ttsfluxxdemo.dagger.module

import com.ptmr3.ttsfluxxdemo.service.LogService
import com.ptmr3.ttsfluxxdemo.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeLogServiceInjector(): LogService
}