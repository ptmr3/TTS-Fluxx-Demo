package com.ptmr3.ttsfluxxdemo.dagger.module

import android.speech.tts.TextToSpeech
import com.ptmr3.ttsfluxxdemo.store.TtsStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StoreModule {
    @Provides
    @Singleton
    fun providesAppStore(textToSpeech: TextToSpeech) = TtsStore(textToSpeech)
}