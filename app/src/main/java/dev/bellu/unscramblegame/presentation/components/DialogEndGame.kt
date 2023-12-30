package dev.bellu.unscramblegame.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.bellu.unscramblegame.presentation.theme.Colors
import dev.bellu.unscramblegame.presentation.theme.Typography

@Composable
fun DialogEndGame(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Dialog(
        content = {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                .width(344.dp)
                .height(259.dp)
                .background(color = Colors.tertiary)
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Congratulations", style = Typography.headlineLarge)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("You Scored: ", style = Typography.titleSmall)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("750/1000 pts", style = Typography.headlineMedium)
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(95.dp)
                                .height(36.dp)
                                .background(color = Colors.quaternary, shape = RoundedCornerShape(42.dp))
                                .clickable { onConfirmation() }
                        ) {
                            Text("EXIT")
                        }
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(135.dp)
                                .height(36.dp)
                                .background(color = Colors.primary, shape = RoundedCornerShape(42.dp))
                                .clickable { onConfirmation() }
                        ) {
                            Text("TRY AGAIN")
                        }
                    }
                }
            }
        },
        onDismissRequest = { onDismissRequest() },
    )
}
