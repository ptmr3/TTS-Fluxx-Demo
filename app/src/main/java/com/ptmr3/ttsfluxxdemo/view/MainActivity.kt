package com.ptmr3.ttsfluxxdemo.view

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.ptmr3.fluxx.FluxxReaction
import com.ptmr3.fluxx.FluxxReactionSubscriber
import com.ptmr3.fluxx.annotation.Reaction
import com.ptmr3.ttsfluxxdemo.R
import com.ptmr3.ttsfluxxdemo.action.ActionDataKeys.ACTION_SUCCESS_KEY
import com.ptmr3.ttsfluxxdemo.action.TtsActions.Companion.INITIALIZE_TTS
import com.ptmr3.ttsfluxxdemo.action.TtsActions.Companion.SPEAK_TEXT
import com.ptmr3.ttsfluxxdemo.action.creator.TtsActionsCreator
import com.ptmr3.ttsfluxxdemo.service.LogService
import com.ptmr3.ttsfluxxdemo.store.TtsStore
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), FluxxReactionSubscriber {
    val ttsActionsCreator: TtsActionsCreator by inject()
    // Instantiating Store here... need a better solution
    val ttsStore: TtsStore by inject()
    val ttsEngine : TextToSpeech by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // needed to activate instances until new version of koin comes out
        ttsActionsCreator.toString()
        ttsStore.toString()
        ttsEngine.toString()
        registerReactionSubscriber(this)
        setContentView(R.layout.activity_main)
        startService(Intent(this, LogService::class.java))
        speakButton.setOnClickListener {
            val textToSpeak = stringToSpeakInput.text.toString()
            if (textToSpeak.isNotEmpty()) {
                ttsActionsCreator.speakText(textToSpeak)
            } else {
                Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        unregisterReactionSubscriber(this)
        super.onDestroy()
    }

    @Reaction(INITIALIZE_TTS)
    fun enableButtonUponInitialization(reaction: FluxxReaction) {
        when (reaction.get<Boolean>(ACTION_SUCCESS_KEY)) {
            true -> {
                speakButton.isEnabled = true
                speakButton.text = resources.getString(R.string.speak_button_text)
            }
        }
    }

    @Reaction(SPEAK_TEXT)
    fun logSuccess() {
        Log.i(TAG, "speak: " + Thread.currentThread().name)
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}