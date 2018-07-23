package com.ptmr3.ttsfluxxdemo.action.creator

import android.speech.tts.TextToSpeech
import com.ptmr3.fluxx.Fluxx
import com.ptmr3.fluxx.FluxxActionCreator
import com.ptmr3.ttsfluxxdemo.action.ActionDataKeys.STRING_TO_SPEAK_KEY
import com.ptmr3.ttsfluxxdemo.action.ActionDataKeys.TTS_INIT_STATUS_KEY
import com.ptmr3.ttsfluxxdemo.action.TtsActions
import com.ptmr3.ttsfluxxdemo.action.TtsActions.Companion.INITIALIZE_TTS
import com.ptmr3.ttsfluxxdemo.action.TtsActions.Companion.SPEAK_TEXT
import javax.inject.Inject

class TtsActionsCreator
@Inject
constructor(): FluxxActionCreator(), TtsActions, TextToSpeech.OnInitListener {
    override fun speakText(string: String) {
        publishAction(SPEAK_TEXT, STRING_TO_SPEAK_KEY, string)
    }

    override fun onInit(status: Int) {
        publishAction(INITIALIZE_TTS, TTS_INIT_STATUS_KEY, status)
    }
}