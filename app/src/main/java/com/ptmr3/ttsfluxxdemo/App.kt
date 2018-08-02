package com.ptmr3.ttsfluxxdemo

import android.app.Application
import android.speech.tts.TextToSpeech
import com.ptmr3.ttsfluxxdemo.action.creator.TtsActionsCreator
import com.ptmr3.ttsfluxxdemo.store.TtsStore
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

class App : Application() {
    private val actionsCreatorsModule : Module = applicationContext {
        bean { TtsActionsCreator() } bind (TextToSpeech.OnInitListener::class)
    }

    private val appModule : Module = org.koin.dsl.module.applicationContext {
        bean { TextToSpeech(applicationContext, get()) }
    }

    private val storeModule : Module = applicationContext {
        bean { TtsStore(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(actionsCreatorsModule, appModule, storeModule))
    }
}