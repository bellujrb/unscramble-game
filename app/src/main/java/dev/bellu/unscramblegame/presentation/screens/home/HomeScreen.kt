package dev.bellu.unscramblegame.presentation.screens.home

import InputWord
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.bellu.unscramblegame.R
import dev.bellu.unscramblegame.data.allWords
import dev.bellu.unscramblegame.presentation.components.InputLetter
import dev.bellu.unscramblegame.presentation.components.ScrambledWordView
import dev.bellu.unscramblegame.presentation.theme.Colors
import dev.bellu.unscramblegame.presentation.theme.Typography
import dev.bellu.unscramblegame.presentation.theme.UnscrambleGameTheme

@Composable
fun HomeScreen() {

    val controller = HomeController()
    var scrambled by remember { mutableStateOf("") }
    val word = allWords.first()

    LaunchedEffect(key1 = null){
        word.forEach { letter ->
            controller.listLetters.add(letter.toString())
        }
        controller.listLetters.shuffle()
        scrambled = controller.listLetters.joinToString("")
        print(scrambled)
    }

    var textMock by remember{ mutableStateOf("") }
    val listMock: List<String> = listOf(
        "I",
        "A",
        "L",
        "N",
        "M",
        "A"
    )


    UnscrambleGameTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Colors.primary),
        ) {
            Column {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight(0.2f)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = Typography.displayMedium
                    )
                }
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        color = Colors.tertiary, shape = RoundedCornerShape(
                            topEnd = 50.dp, topStart = 50.dp
                        )
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(24.dp)
                                .width(40.9.dp)
                                .height(40.9.dp)
                                .shadow(
                                    elevation = 2.9.dp,
                                    spotColor = Colors.onTertiary,
                                    ambientColor = Colors.onTertiary
                                )
                                .background(color = Colors.tertiary, shape = RoundedCornerShape(size = 36.5.dp))
                        ){
                            Text(
                                "?",
                                style = Typography.displaySmall,
                            )
                        }
                        ScrambledWordView(word = word, scrambled = scrambled)
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(24.dp)){
                            Text(
                                stringResource(R.string.app_caption),
                                style = Typography.titleSmall,
                            )
                        }
                        InputWord(
                            label = "",
                            value = textMock,
                            onValueChange = {
                                textMock = it
                            }
                        )
                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )
                        LazyColumn {
                            item {
                                LazyRow {
                                    items(minOf(listMock.size, 5)) { index ->
                                        InputLetter(
                                            listMock[index],
                                            index = index,
                                            onClick = {
                                                textMock += listMock[index]
                                            }
                                        )
                                    }
                                }
                            }
                            item {
                                LazyRow {
                                    items((listMock.size - 5).coerceAtLeast(0)) { index ->
                                        InputLetter(
                                            listMock[index + 5],
                                            index = index,
                                            onClick = {
                                                textMock += listMock[index + 5]
                                            }
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

