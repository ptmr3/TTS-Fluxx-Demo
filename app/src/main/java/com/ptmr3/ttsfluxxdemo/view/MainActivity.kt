package com.ptmr3.ttsfluxxdemo.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ptmr3.fluxx.FluxxReaction
import com.ptmr3.fluxx.FluxxActionCaller
import com.ptmr3.fluxx.annotation.Reaction
import com.ptmr3.ttsfluxxdemo.R
import com.ptmr3.ttsfluxxdemo.action.ActionDataKeys.ACTION_SUCCESS_KEY
import com.ptmr3.ttsfluxxdemo.action.TtsActions.Companion.INITIALIZE_TTS
import com.ptmr3.ttsfluxxdemo.action.TtsActions.Companion.SPEAK_TEXT
import com.ptmr3.ttsfluxxdemo.action.creator.TtsActionsCreator
import com.ptmr3.ttsfluxxdemo.store.TtsStore
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), FluxxActionCaller {
    @Inject lateinit var ttsActionsCreator: TtsActionsCreator
    @Inject lateinit var ttsStore: TtsStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        speakButton.setOnClickListener {
            val textToSpeak = stringToSpeakInput.text.toString()
            if (textToSpeak.isNotEmpty()) {
                ttsActionsCreator.speakText(textToSpeak)
            } else {
                Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun registerStore() {
        ttsStore.register()
    }

    @Reaction(INITIALIZE_TTS)
    fun enableButtonUponInitialization(reaction: FluxxReaction) {
        when (reaction.get<Boolean>(ACTION_SUCCESS_KEY)) {
            true -> speakButton.isEnabled = true
        }
    }

    @Reaction(SPEAK_TEXT)
    fun logSuccess(reaction: FluxxReaction) {
        Log.i(TAG, reaction.type)
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}