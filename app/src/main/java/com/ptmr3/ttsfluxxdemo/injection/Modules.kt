package com.ptmr3.ttsfluxxdemo.injection

import android.app.Application
import android.content.Context
import android.speech.tts.TextToSpeech
import com.ptmr3.fluxx.FluxxLog
import com.ptmr3.ttsfluxxdemo.BuildConfig
import com.ptmr3.ttsfluxxdemo.action.creator.TtsActionsCreator
import com.ptmr3.ttsfluxxdemo.store.TtsStore
import org.koin.dsl.module.module

/**
 * All dependency injection modules are held here to create class instances using Koin
 *
 * If there is amy requirement of class initialization at runtime, use
 * "single(createOnStart = true)", otherwise use "single" to create the classes
 * as singletons, or in rare circumstances, "factory" can be used to create a new instance of the class
 * at every usage.
 */
class Modules(context: Context) {
    init { sInstance = this }

    /**
     * All actions creators are to be held here.
     */
    private val mActionsCreatorsModule = module {
        single(createOnStart = true) { TtsActionsCreator() } bind (TextToSpeech.OnInitListener::class)
    }

    /**
     * All generic dependencies are to be held here.
     */
    private val mAppModule = module {
        if (BuildConfig.DEBUG) { single(createOnStart = true) { FluxxLog.instance.setDebug() } }
        single(createOnStart = true) { TextToSpeech(context, get()) }
    }

    /**
     * All stores are to be held here.
     */
    private val mStoreModule = module {
        single(createOnStart = true) { TtsStore(get()) }
    }

    val modules = listOf(mActionsCreatorsModule, mAppModule, mStoreModule)

    companion object {
        private var sInstance: Modules? = null
        /**
         * Returns instance of class if not null, or creates new
         */
        fun instance(application: Application) = sInstance?.let { it } ?: run { Modules(application) }
    }
}