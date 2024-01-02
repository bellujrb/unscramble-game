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
import kotlin.random.Random

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    var uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    var listLetters: MutableList<String> = mutableListOf()

    init {
        randomWord()
    }

    fun randomWord() {
        viewModelScope.launch {
            delay(1000)

            val indexRandom = Random.nextInt(allWords.size)
            _uiState.value = uiState.value.copy(
                index = indexRandom,
                word = allWords[_uiState.value.index]
            )
            shuffleWord()
        }
    }

    private fun shuffleWord() {
        listLetters.clear()
        allWords[_uiState.value.index].forEach { letter ->
            listLetters.add(letter.toString())
        }
        listLetters.shuffle()
        _uiState.value = uiState.value.copy(scrambled = listLetters.joinToString(""))
    }

}