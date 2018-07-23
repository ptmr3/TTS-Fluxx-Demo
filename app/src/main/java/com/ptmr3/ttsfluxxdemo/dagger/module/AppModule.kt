package com.ptmr3.ttsfluxxdemo.dagger.module

import android.content.Context
import android.speech.tts.TextToSpeech
import com.ptmr3.ttsfluxxdemo.App
import com.ptmr3.ttsfluxxdemo.action.creator.TtsActionsCreator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApplicationContext(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesTextToSpeech(context: Context, ttsActionsCreator: TtsActionsCreator) = TextToSpeech(context, ttsActionsCreator)
}