package com.ptmr3.ttsfluxxdemo.dagger.module

import com.ptmr3.ttsfluxxdemo.action.creator.TtsActionsCreator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActionsCreatorModule {
    @Singleton
    @Provides
    fun providesAppActionCreator() = TtsActionsCreator()
}