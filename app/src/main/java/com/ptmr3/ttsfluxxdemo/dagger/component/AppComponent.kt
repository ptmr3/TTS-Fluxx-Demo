package com.ptmr3.ttsfluxxdemo.dagger.component

import com.ptmr3.ttsfluxxdemo.App
import com.ptmr3.ttsfluxxdemo.dagger.module.ActionsCreatorModule
import com.ptmr3.ttsfluxxdemo.dagger.module.AppModule
import com.ptmr3.ttsfluxxdemo.dagger.module.StoreModule
import com.ptmr3.ttsfluxxdemo.dagger.module.ViewModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (ActionsCreatorModule::class), (AppModule::class), (StoreModule::class), (ViewModule::class)])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}