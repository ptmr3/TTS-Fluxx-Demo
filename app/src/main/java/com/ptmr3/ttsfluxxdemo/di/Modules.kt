package com.ptmr3.ttsfluxxdemo.di

import android.content.Context
import android.speech.tts.TextToSpeech
import com.ptmr3.ttsfluxxdemo.action.creator.TtsActionsCreator
import com.ptmr3.ttsfluxxdemo.store.TtsStore
import org.koin.dsl.module.module

class Modules(context: Context) {
    private val actionsCreatorsModule = module {
        single(createOnStart = true) { TtsActionsCreator() } bind (TextToSpeech.OnInitListener::class)
    }

    private val appModule = module {
        single(createOnStart = true) { TextToSpeech(context, get()) }
    }

    private val storeModule = module {
        single(createOnStart = true) { TtsStore(get()) }
    }

    val modules = listOf(actionsCreatorsModule, appModule, storeModule)
}