package com.ptmr3.ttsfluxxdemo.store

import android.speech.tts.TextToSpeech
import com.ptmr3.fluxx.Fluxx
import com.ptmr3.fluxx.FluxxAction
import com.ptmr3.fluxx.FluxxStore
import com.ptmr3.fluxxap.Action
import com.ptmr3.ttsfluxxdemo.action.ActionDataKeys.ACTION_SUCCESS_KEY
import com.ptmr3.ttsfluxxdemo.action.ActionDataKeys.STRING_TO_SPEAK_KEY
import com.ptmr3.ttsfluxxdemo.action.ActionDataKeys.TTS_INIT_STATUS_KEY
import com.ptmr3.ttsfluxxdemo.action.TtsActions.Companion.INITIALIZE_TTS
import com.ptmr3.ttsfluxxdemo.action.TtsActions.Companion.SPEAK_TEXT
import java.util.*
import javax.inject.Inject

class TtsStore
@Inject
constructor(fluxx: Fluxx, private val mTextToSpeech: TextToSpeech) : FluxxStore(fluxx) {

    @Action(actionType = INITIALIZE_TTS)
    fun onInit(action: FluxxAction) {
        val status: Int = action[TTS_INIT_STATUS_KEY]
        if (status == TextToSpeech.SUCCESS) {
            mTextToSpeech.language = Locale.getDefault()
            publishReaction(action.type, ACTION_SUCCESS_KEY, true)
        } else if (status == TextToSpeech.ERROR) {
            publishReaction(action.type, ACTION_SUCCESS_KEY, false)
        }
    }

    @Action(actionType = SPEAK_TEXT)
    fun speakText(action: FluxxAction) {
        mTextToSpeech.speak(action[STRING_TO_SPEAK_KEY], TextToSpeech.QUEUE_FLUSH, null, null)
        publishReaction(SPEAK_TEXT)
    }
}