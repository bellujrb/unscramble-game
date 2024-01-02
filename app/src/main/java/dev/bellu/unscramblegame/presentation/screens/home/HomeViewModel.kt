package dev.bellu.unscramblegame.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.bellu.unscramblegame.data.allWords
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    var uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    var listLetters: MutableList<String> = mutableListOf()

    private var resultGame = ""

    init {
        randomWord()
        _uiState.value = uiState.value.copy(scrambled = generateLetters())
        viewModelScope.launch {
            uiState.collect { state ->
                Log.e(
                    "Debugg",
                    "${state.word}," +
                            " ${state.scrambled}, " +
                            "$listLetters"
                )
            }
        }
    }

    private fun randomWord() {
        viewModelScope.launch {
            delay(1000)
            _uiState.value = uiState.value.copy(word = allWords[_uiState.value.index])
        }
    }

    private fun generateLetters(): String {
        viewModelScope.launch {
            _uiState.value.word.forEach { letter ->
                listLetters.add(letter.toString())
            }
        }
        listLetters.shuffle()
        return listLetters.joinToString("")
    }

    fun playWord(word: String, index: Int, newWord: String) {
        val isSuccess = word == allWords.getOrNull(index)

        resultGame = if (isSuccess) {
            randomWord()
            generateLetters()
            //this.index = allWords.indices.random()
            "Accept"
        } else {
            "Reject"
        }

        Log.d(
            "resultGame",
            "word: $word, " +
                    "index: $index, " +
                    "newWord: $newWord, " +
                    "resultGame: $resultGame"
        )
    }
}