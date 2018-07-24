package com.ptmr3.ttsfluxxdemo.service

import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.ptmr3.fluxx.FluxxReactionSubscriber
import com.ptmr3.fluxx.annotation.Reaction
import com.ptmr3.ttsfluxxdemo.action.TtsActions
import dagger.android.DaggerService

class LogService : DaggerService(), FluxxReactionSubscriber {

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        registerReactionSubscriber(this)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        unregisterReactionSubscriber(this)
        super.onDestroy()
    }

    @Reaction(TtsActions.INITIALIZE_TTS)
    fun enableButtonUponInitialization() {
        Log.i("SERVICE", "INITIALIZE_TTS")
    }

    @Reaction(TtsActions.SPEAK_TEXT)
    fun logSuccess() {
        Log.i("SERVICE", "SPEAK_TEXT")
    }
}