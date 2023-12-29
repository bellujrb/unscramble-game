package dev.bellu.unscramblegame.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.bellu.unscramblegame.presentation.theme.Colors
import dev.bellu.unscramblegame.presentation.theme.Typography

@Composable
fun ScrambledWordView(word: String){
    val controller = ScrambledWordController()
    var scrambled by remember { mutableStateOf("") }

    LaunchedEffect(key1 = null){
        word.forEach { letter ->
            controller.listLetters.add(letter.toString())
        }
        controller.listLetters.shuffle()
        scrambled = controller.listLetters.joinToString("")
        print(scrambled)
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(95.dp)
        .background(
            color = Colors.secondary,
            shape = RoundedCornerShape(size = 59.dp)
        )
    ){
        Text(
            scrambled,
            style = Typography.labelMedium
        )
    }
}

class ScrambledWordController{
    val listLetters: MutableList<String> = mutableListOf()
}