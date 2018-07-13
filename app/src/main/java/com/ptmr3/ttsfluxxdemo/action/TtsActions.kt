package com.ptmr3.ttsfluxxdemo.action

interface TtsActions {
    fun speakText(string: String)

    companion object {
        const val INITIALIZE_TTS = "initializeTts"
        const val SPEAK_TEXT = "speakText"
    }
}