package dev.bellu.unscramblegame.presentation.screens.home

import android.util.Log
import dev.bellu.unscramblegame.data.allWords

class HomeController {
    val listLetters: MutableList<String> = mutableListOf()
    private var resultGame: String = ""

    fun playWord(word: String, index: Int) {
        resultGame = if (word == allWords[index]) {
            "Accept"
        } else {
            "Reject"
        }
        Log.d("resultGame", resultGame)
    }

}